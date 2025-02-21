package br.com.controlefinanceiro.controller;

import java.util.List;
import java.util.Optional;

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
import br.com.controlefinanceiro.DTO.ContaDTO;
import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Tipo_Conta;
import br.com.controlefinanceiro.repository.ContaRepository;
import br.com.controlefinanceiro.repository.TipoContaRepository;
import br.com.controlefinanceiro.service.SequenciaService;

@RestController 
@RequestMapping(value = "/conta", produces = "application/json")
public class ContaController {

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private TipoContaRepository tipoContaRepository;
	
	@Autowired
	private SequenciaService sequenciaService;
	

	
	@GetMapping(value = "/{id_conta}")
	public ResponseEntity<Conta> obterId(@PathVariable(value ="id_conta") Long id) throws Exception
	{
		Optional<Conta> objeto = contaRepository.findById(id);
		
		if(objeto.isEmpty())
		{
			throw new MensagemException("Conta não encontrada!");
		}
		
		return new ResponseEntity(objeto.get(), HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/", produces = "application/json")
	@CacheEvict(value = "cacheconta", allEntries = true)
	@CachePut("cacheconta")
	public ResponseEntity<List<Conta>> obterTodos () throws InterruptedException
	{
		List<Conta> objetos = (List<Conta> ) contaRepository.findAll();
		

		if(objetos.isEmpty())
		{
			throw new MensagemException("Nenhuma conta encontrada!");
		}
		
		return new ResponseEntity<List<Conta>>(objetos, HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<?> cadastrar(@RequestBody ContaDTO objetoDTO)
	{
		try 
		{
			
			Conta objeto = new Conta();
			
			if(objetoDTO.getCd_conta().isEmpty() ||  objetoDTO.getCd_conta() == null)
	        {
	        	Long proximoNumero = sequenciaService.gerarProximaSequencia(contaRepository, "cd_conta");
		        String proximoNumeroStr = String.valueOf(proximoNumero); 
	        	objeto.setCd_conta(proximoNumeroStr);
	        }
			else
			{
			    objeto.setCd_conta(objetoDTO.getCd_conta());
			}
			
	
			objeto.setNm_conta(objetoDTO.getNm_conta());
			
			 Tipo_Conta tipoConta = tipoContaRepository.findById(objetoDTO.getId_tipoconta())
			            .orElseThrow(() -> new RuntimeException("Tipo de conta não encontrada"));
	       
			objeto.setTipoConta(tipoConta);
	        
			Conta objetoSalvo =  contaRepository.save(objeto);
			
			return new ResponseEntity<Conta>(objetoSalvo, HttpStatus.OK);
		} 
		catch (Exception e)
		{	
			 throw new MensagemException( e.getMessage());
		}

	}
	
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<?> atualizar(@RequestBody Conta objeto)
	{
		try 
		{
			Conta objetoSalvo = contaRepository.save(objeto);
			
			
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
		contaRepository.deleteById(id);
		
		return "Usuario deletado!";
	}
	
	
}
