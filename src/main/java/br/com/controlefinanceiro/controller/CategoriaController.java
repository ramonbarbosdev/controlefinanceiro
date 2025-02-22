package br.com.controlefinanceiro.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import br.com.controlefinanceiro.DTO.UsuarioDTO;
import br.com.controlefinanceiro.DTO.CategoriaDTO;
import br.com.controlefinanceiro.model.Categoria;
import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Tipo_Categoria;
import br.com.controlefinanceiro.model.Tipo_Conta;
import br.com.controlefinanceiro.model.Usuario;
import br.com.controlefinanceiro.repository.CategoriaRepository;
import br.com.controlefinanceiro.repository.ContaRepository;
import br.com.controlefinanceiro.repository.TipoCategoriaRepository;
import br.com.controlefinanceiro.repository.TipoContaRepository;
import br.com.controlefinanceiro.service.SequenciaService;
import br.com.controlefinanceiro.service.Utils;
import br.com.controlefinanceiro.service.ValidacaoService;
import ch.qos.logback.core.model.Model;
import java.util.stream.Collectors;



@RestController 
@RequestMapping(value = "/categoria", produces = "application/json")
public class CategoriaController {

	@Autowired
	private CategoriaRepository objetoRepository;
	
	@Autowired
	private TipoCategoriaRepository tipoCategoriaRepository;

	@Autowired
	private Utils utils;

	@Autowired
	private ValidacaoService validacaoService ;

	//atributos
	private Class<?> model = Categoria.class;
	private String primaryKey = "id_categoria";
	private ModelMapper modelMapper = new ModelMapper();  
	

	@GetMapping(value = "/{id_categoria}")
	public ResponseEntity<?> obterId(@PathVariable(value ="id_categoria") Long id) throws Exception
	{
		Object objetoModel = this.model.getDeclaredConstructor().newInstance();
		Class<?> objetoDTO = utils.obterClasseDtoEntidade(this.model);

		CrudRepository repository = utils.obterRepositoryEntidade(objetoModel);

		Optional<?> objeto = repository.findById(id);
		
		if(objeto.isEmpty())
		{
			throw new MensagemException("Registro não encontrado!");
		}
		
		Object dto = utils.converterDTO(objeto.get(), objetoDTO);

		return new ResponseEntity<Object>( dto, HttpStatus.OK);
		
	}

		
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<?>> obterTodos() throws Exception {
		
		Object objetoModel = this.model.getDeclaredConstructor().newInstance();
		
		Class<?> objetoDTO = utils.obterClasseDtoEntidade(this.model);

		CrudRepository repository = utils.obterRepositoryEntidade(objetoModel);

		List<Object> objetos = (List<Object>) repository.findAll();

	    if (objetos.isEmpty())
	    {
	        throw new MensagemException("Nenhuma conta encontrada!");
	    }

		List<?> listDTO = objetos.stream()
									.map(objeto -> utils.converterDTO(objeto, objetoDTO))
									.collect(Collectors.toList());

	    return new ResponseEntity<>(listDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<?> cadastro(@RequestBody Object dto) throws Exception
	{
		try
		{
			Object objetoModel = this.model.getDeclaredConstructor().newInstance();
		
			Object objetoDTO = utils.obterObjetoDtoEntidade(this.model);

			modelMapper.map(dto, objetoDTO);
			modelMapper.map(objetoDTO, objetoModel);

			//modificavel
			utils.obterObjetoRelacionamento(objetoModel,objetoDTO,"id_tipocategoria", tipoCategoriaRepository, "setTipoCategoria", Tipo_Categoria.class);

			validacaoService.validarCadastroGeral(objetoModel, this.primaryKey);

			CrudRepository repository = utils.obterRepositoryEntidade(objetoModel);
			Object objetoSalvo = repository.save(objetoModel);

			return new ResponseEntity<>(objetoSalvo, HttpStatus.CREATED);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	

	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<?> atualizar(@RequestBody Object dto)  throws Exception
	{
		try 
		{
			Object objetoModel = this.model.getDeclaredConstructor().newInstance();
		
			Object objetoDTO = utils.obterObjetoDtoEntidade(this.model);

			CrudRepository repository = utils.obterRepositoryEntidade(objetoModel);

			modelMapper.map(dto, objetoDTO);
			modelMapper.map(objetoDTO, objetoModel);

			//modificavel
			utils.obterObjetoRelacionamento(objetoModel,objetoDTO,"id_tipocategoria", tipoCategoriaRepository, "setTipoCategoria", Tipo_Categoria.class);
			
			Object objetoSalvo = repository.save(objetoModel);

			return new ResponseEntity<Object>(objetoSalvo, HttpStatus.OK);
		} 
		catch (Exception e)
		{	
			 throw new MensagemException( e.getMessage());
		}

	}
	
	
	@DeleteMapping(value = "/{id_categoria}", produces = "application/text" )
	public String delete (@PathVariable("id_categoria") Long id) throws Exception
	{
		Object objetoModel = this.model.getDeclaredConstructor().newInstance();
		
		CrudRepository repository = utils.obterRepositoryEntidade(objetoModel);

		repository.deleteById(id);

		return "Registro deletado!";
	
	}


	
	
}
