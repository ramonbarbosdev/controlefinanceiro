package br.com.controlefinanceiro.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.CrudRepository;
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

import br.com.controlefinanceiro.DTO.CategoriaDTO;
import br.com.controlefinanceiro.DTO.ContaDTO;
import br.com.controlefinanceiro.excecoes.MensagemException;
import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Tipo_Categoria;
import br.com.controlefinanceiro.model.Tipo_Conta;
import br.com.controlefinanceiro.repository.ContaRepository;
import br.com.controlefinanceiro.repository.TipoContaRepository;
import br.com.controlefinanceiro.service.SequenciaService;
import br.com.controlefinanceiro.service.Utils;
import br.com.controlefinanceiro.service.ValidacaoService;
import ch.qos.logback.core.model.Model;
import java.util.stream.Collectors;



@RestController 
// @RequestMapping(value = "/conta", produces = "application/json")
public class AntigoController {

	// @Autowired
	// private ContaRepository objetoRepository;
	
	// @Autowired
	// private TipoContaRepository tipoContaRepository;
	
	// @Autowired
	// private Utils utils;

	// @Autowired
	// private ValidacaoService validacaoService ;
	
	// //atributos
	// private Class<?> model = Conta.class;
	// private String primaryKey = "id_conta";
	// private ModelMapper modelMapper = new ModelMapper();  

	
	// @GetMapping(value = "/{id_conta}")
	// public ResponseEntity<?> obterId(@PathVariable(value ="id_conta") Long id) throws Exception
	// {
	// 	try
	// 	{
	// 		Object objetoModel = this.model.getDeclaredConstructor().newInstance();
	// 		Class<?> objetoDTO = utils.obterClasseDtoEntidade(this.model);

	// 		CrudRepository repository = utils.obterRepositoryEntidade(objetoModel);

	// 		Optional<?> objeto = repository.findById(id);
			
	// 		if(objeto.isEmpty())
	// 		{
	// 			throw new MensagemException("Registro não encontrado!");
	// 		}
			
	// 		Object dto = utils.converterDTO(objeto.get(), objetoDTO);

	// 		return new ResponseEntity<Object>( dto, HttpStatus.OK);
	// 	}
	// 	catch (Exception e)
	// 	{
	// 			throw new MensagemException( e.getMessage());
	// 	}
	// }
	
	// @GetMapping(value = "/", produces = "application/json")
	// @CacheEvict(value = "cacheconta", allEntries = true)
	// @CachePut("cacheconta")
	// public ResponseEntity<List<?>> obterTodos() throws Exception
	// {
	// 	try
	// 	{
	// 		Object objetoModel = this.model.getDeclaredConstructor().newInstance();
			
	// 		Class<?> objetoDTO = utils.obterClasseDtoEntidade(this.model);

	// 		CrudRepository repository = utils.obterRepositoryEntidade(objetoModel);

	// 		List<Object> objetos = (List<Object>) repository.findAll();

	// 		if (objetos.isEmpty())
	// 		{
	// 			throw new MensagemException("Nenhuma conta encontrada!");
	// 		}

	// 		List<?> listDTO = objetos.stream()
	// 									.map(objeto -> utils.converterDTO(objeto, objetoDTO))
	// 									.collect(Collectors.toList());

	// 		return new ResponseEntity<>(listDTO, HttpStatus.OK);
	// 	}
	// 	catch (Exception e)
	// 	{
	// 			throw new MensagemException( e.getMessage());
	// 	}
	// }

	// // @PostMapping(value = "/", produces = "application/json")
	// // public ResponseEntity<?> cadastrar(@RequestBody ContaDTO dto) throws Exception
	// // {
	// // 	try 
	// // 	{		
	// // 		Conta objeto = modelMapper.map(dto, Conta.class);
			
	// // 	    utils.obterObjetoRelacionamento(objeto,dto,"id_tipoconta", tipoContaRepository, "setTipoConta", Tipo_Conta.class);

	// // 		validacaoService.validarCadastroGeral(objeto, primaryKey);
	
	// //         Conta objetoSalvo = objetoRepository.save(objeto);
			
	// // 		return new ResponseEntity<Conta>(objetoSalvo, HttpStatus.OK);
	// // 	} 
	// // 	catch (Exception e)
	// // 	{	
	// // 		 throw new MensagemException( e.getMessage());
	// // 	}

	// // }

	// @PostMapping(value = "/", produces = "application/json")
	// public ResponseEntity<?> cadastro(@RequestBody Object dto) throws Exception
	// {
	// 	try
	// 	{
	// 		Object objetoModel = this.model.getDeclaredConstructor().newInstance();
		
	// 		Object objetoDTO = utils.obterObjetoDtoEntidade(this.model);

	// 		modelMapper.map(dto, objetoDTO);
	// 		modelMapper.map(objetoDTO, objetoModel);

	// 		//modificavel
	// 		utils.obterObjetoRelacionamento(objetoModel,objetoDTO,"id_tipoconta", tipoContaRepository, "setTipoConta", Tipo_Conta.class);

	// 		validacaoService.validarCadastroGeral(objetoModel, this.primaryKey);

	// 		CrudRepository repository = utils.obterRepositoryEntidade(objetoModel);
	// 		Object objetoSalvo = repository.save(objetoModel);

	// 		return new ResponseEntity<>(objetoSalvo, HttpStatus.CREATED);
	// 	}
	// 	catch (Exception e)
	// 	{
	// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	// 	}
	// }
	
	// @PutMapping(value = "/", produces = "application/json")
	// public ResponseEntity<?> atualizar(@RequestBody Object dto) throws Exception
	// {
	// 	try 
	// 	{
	// 		Object objetoModel = this.model.getDeclaredConstructor().newInstance();
		
	// 		Object objetoDTO = utils.obterObjetoDtoEntidade(this.model);

	// 		CrudRepository repository = utils.obterRepositoryEntidade(objetoModel);

	// 		modelMapper.map(dto, objetoDTO);
	// 		modelMapper.map(objetoDTO, objetoModel);

	// 		//modificavel
	// 		utils.obterObjetoRelacionamento(objetoModel,objetoDTO,"id_tipoconta", tipoContaRepository, "setTipoConta", Tipo_Conta.class);

	// 		Object objetoSalvo = repository.save(objetoModel);

	// 		return new ResponseEntity<Object>(objetoSalvo, HttpStatus.OK);
	// 	} 
	// 	catch (Exception e)
	// 	{	
	// 		 throw new MensagemException( e.getMessage());
	// 	}

	// }
	
	
	// @DeleteMapping(value = "/{id_conta}", produces = "application/text" )
	// public String delete (@PathVariable("id_conta") Long id) throws Exception
	// {
	// 	try 
	// 	{

	// 		Object objetoModel = this.model.getDeclaredConstructor().newInstance();
			
	// 		CrudRepository repository = utils.obterRepositoryEntidade(objetoModel);

	// 		repository.deleteById(id);
			
	// 		return "Registro deletado!";

	// 	} 
	// 	catch (Exception e)
	// 	{	
	// 		throw new MensagemException( e.getMessage());
	// 	}
	// }
	

		
	
}
