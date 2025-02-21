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
import br.com.controlefinanceiro.DTO.CategoriaDTO;
import br.com.controlefinanceiro.model.Categoria;
import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Tipo_Categoria;
import br.com.controlefinanceiro.model.Tipo_Conta;
import br.com.controlefinanceiro.repository.CategoriaRepository;
import br.com.controlefinanceiro.repository.ContaRepository;
import br.com.controlefinanceiro.repository.TipoCategoriaRepository;
import br.com.controlefinanceiro.repository.TipoContaRepository;
import br.com.controlefinanceiro.service.SequenciaService;
import ch.qos.logback.core.model.Model;
import java.util.stream.Collectors;



@RestController 
@RequestMapping(value = "/categoria", produces = "application/json")
public class CategoriaController {

	@Autowired
	private CategoriaRepository objetoRepository;
	
	@Autowired
	private TipoCategoriaRepository tipoCategoriaRepository;

	private ModelMapper modelMapper = new ModelMapper();  
	

	
	@GetMapping(value = "/{id_categoria}")
	public ResponseEntity<?> obterId(@PathVariable(value ="id_categoria") Long id) throws Exception
	{
		Optional<Categoria> objeto = objetoRepository.findById(id);
		
		if(objeto.isEmpty())
		{
			throw new MensagemException("Objeto não encontrada!");
		}
		
		return new ResponseEntity(objeto.get(), HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<CategoriaDTO>> obterTodos() throws InterruptedException {
		
	    List<Categoria> objetos = (List<Categoria>) objetoRepository.findAll();

	    if (objetos.isEmpty())
	    {
	        throw new MensagemException("Nenhuma conta encontrada!");
	    }

	   
	    List<CategoriaDTO> objetosDTO = objetos.stream()
	            .map(objeto -> {
	            	CategoriaDTO objetoDTO = modelMapper.map(objeto, CategoriaDTO.class);
	            	objetoDTO.setId_tipocategoria(objeto.getTipoCategoria().getId_tipocategoria()); 
	                return objetoDTO;
	            })
	            .collect(Collectors.toList());

	    return new ResponseEntity<>(objetosDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<?> cadastrar(@RequestBody CategoriaDTO objetoDTO) throws Exception
	{
		try 
		{		
			Categoria objeto = modelMapper.map(objetoDTO, Categoria.class);
			
			/*
			if(objetoDTO.getCd_conta().isEmpty() ||  objetoDTO.getCd_conta() == null)
	        {
	        	Long proximoNumero = sequenciaService.gerarProximaSequencia(contaRepository, "cd_conta");
		        String proximoNumeroStr = String.valueOf(proximoNumero); 
	        	objeto.setCd_conta(proximoNumeroStr);
	        }
			*/
	
		    Tipo_Categoria tipo = tipoCategoriaRepository.findById(objetoDTO.getId_tipocategoria())
		                 .orElseThrow(() -> new RuntimeException("Tipo categoria não encontrada"));
		  
            objeto.setTipoCategoria(tipo);

            Categoria objetoSalvo = objetoRepository.save(objeto);
			
			return new ResponseEntity<Categoria>(objetoSalvo, HttpStatus.OK);
		} 
		catch (Exception e)
		{	
			 throw new MensagemException( e.getMessage());
		}

	}
	
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<?> atualizar(@RequestBody Categoria objeto)
	{
		try 
		{
			Categoria objetoSalvo = objetoRepository.save(objeto);
			
			
			return new ResponseEntity<Categoria>(objetoSalvo, HttpStatus.OK);
		} 
		catch (Exception e)
		{	
			 throw new MensagemException( e.getMessage());
		}

	}
	
	
	@DeleteMapping(value = "/{id_categoria}", produces = "application/text" )
	public String delete (@PathVariable("id_categoria") Long id)
	{
		objetoRepository.deleteById(id);
		
		return "Usuario deletado!";
	}
	
	
}
