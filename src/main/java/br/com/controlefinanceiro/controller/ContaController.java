package br.com.controlefinanceiro.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controlefinanceiro.MensagemException;
import br.com.controlefinanceiro.DTO.CategoriaDTO;
import br.com.controlefinanceiro.DTO.ContaDTO;
import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Tipo_Conta;
import br.com.controlefinanceiro.repository.ContaRepository;
import br.com.controlefinanceiro.repository.TipoContaRepository;
import br.com.controlefinanceiro.service.SequenciaService;
import br.com.controlefinanceiro.service.Utils;
import br.com.controlefinanceiro.service.ValidacaoService;
import ch.qos.logback.core.model.Model;
import java.util.stream.Collectors;



@RestController 
@RequestMapping(value = "/conta", produces = "application/json")
public class ContaController {

	@Autowired
	private ContaRepository objetoRepository;
	
	@Autowired
	private TipoContaRepository tipoContaRepository;
	
	@Autowired
	private Utils utils;

	@Autowired
	private ValidacaoService validacaoService ;
	
   
	private ModelMapper modelMapper = new ModelMapper();  

	
	@GetMapping(value = "/{id_conta}")
	public ResponseEntity<?> obterId(@PathVariable(value ="id_conta") Long id) throws Exception
	{
		Optional<Conta> objeto = objetoRepository.findById(id);
		
		if(objeto.isEmpty())
		{
			throw new MensagemException("Conta não encontrada!");
		}
		
		return new ResponseEntity<ContaDTO>(new ContaDTO(objeto.get()), HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/", produces = "application/json")
	@CacheEvict(value = "cacheconta", allEntries = true)
	@CachePut("cacheconta")
	public ResponseEntity<List<?>> obterTodos() throws InterruptedException {
		
	    List<Conta> objetos = (List<Conta>) objetoRepository.findAll();

	    if (objetos.isEmpty())
	    {
	        throw new MensagemException("Nenhuma conta encontrada!");
	    }
   
		List<ContaDTO> objetoDTO = objetos.stream()
												.map(objeto -> new ContaDTO(objeto)) 
												.collect(Collectors.toList()); 

	    return new ResponseEntity<>(objetoDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<?> cadastrar(@RequestBody ContaDTO dto) throws Exception
	{
		try 
		{		
			Conta objeto = modelMapper.map(dto, Conta.class);
			
		    utils.obterObjetoRelacionamento(objeto,dto,"id_tipoconta", tipoContaRepository, "setTipoConta", Tipo_Conta.class);

			validacaoService.validarCadastroGeral(objeto, "id_conta");
	
	        Conta objetoSalvo = objetoRepository.save(objeto);
			
			return new ResponseEntity<Conta>(objetoSalvo, HttpStatus.OK);
		} 
		catch (Exception e)
		{	
			 throw new MensagemException( e.getMessage());
		}

	}
	
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<?> atualizar(@RequestBody ContaDTO dto) throws Exception
	{
		try 
		{
			Conta objeto = modelMapper.map(dto, Conta.class);
	
		    Tipo_Conta tipo = tipoContaRepository.findById(dto.getId_tipoconta())
		                 		.orElseThrow(() -> new RuntimeException("Tipo de conta não encontrada"));

								
		  
            objeto.setTipoConta(tipo);

	        Conta objetoSalvo = objetoRepository.save(objeto);
			
			return new ResponseEntity<Conta>(objetoSalvo, HttpStatus.OK);
		} 
		catch (Exception e)
		{	
			 throw new MensagemException( e.getMessage());
		}

	}
	
	
	@DeleteMapping(value = "/{id_conta}", produces = "application/text" )
	public String delete (@PathVariable("id_conta") Long id)
	{
		objetoRepository.deleteById(id);
		
		return "Usuario deletado!";
	}
	
	
}
