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
		
		return new ResponseEntity<CategoriaDTO>(new CategoriaDTO(objeto.get()), HttpStatus.OK);
		
	}

		
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<?>> obterTodos() throws InterruptedException {
		
	   List<Categoria> objetos = (List<Categoria>) objetoRepository.findAll();

		List<CategoriaDTO> objetoDTO = objetos.stream()
						.map(objeto -> new CategoriaDTO(objeto)) 
						.collect(Collectors.toList()); 
		
		return new ResponseEntity<>(objetoDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<?> cadastrar(@RequestBody CategoriaDTO dto) throws Exception {
		try {
			// Converter DTO para Categoria (mas ainda sem TipoCategoria)
			Categoria categoria = modelMapper.map(dto, Categoria.class);
			
			// Buscar TipoCategoria no banco usando o ID recebido no DTO
			Tipo_Categoria tipo = tipoCategoriaRepository.findById(dto.getId_tipocategoria())
				.orElseThrow(() -> new MensagemException("Tipo de Categoria não encontrado"));
			
			// Definir o TipoCategoria na Categoria
			categoria.setTipoCategoria(tipo);
			
			// Salvar a Categoria no banco
			Categoria objetoSalvo = objetoRepository.save(categoria);
			
			return new ResponseEntity<>(objetoSalvo, HttpStatus.OK);
		} catch (Exception e) {    
			throw new MensagemException(e.getMessage());
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


	//Tipo Categorias
	@GetMapping(value = "/tipocategoria/")
	public ResponseEntity<?> obterTipoCategoria()  throws Exception
	{
		List<Tipo_Categoria> objetos = (List<Tipo_Categoria>) tipoCategoriaRepository.findAll();
		
		
		return new ResponseEntity<>(objetos, HttpStatus.OK);
		
	}

	
	@GetMapping(value = "/tipocategoria/{id_tipocategoria}")
	public ResponseEntity<?> obterTipoCategoriaId(@PathVariable(value ="id_tipocategoria") Long id) throws Exception
	{
		Optional<Tipo_Categoria> objeto = tipoCategoriaRepository.findById(id);
		
		if(objeto.isEmpty())
		{
			throw new MensagemException("Objeto não encontrada!");
		}
		
		return new ResponseEntity<Tipo_Categoria>(objeto.get(), HttpStatus.OK);
		
	}
	

	
	
}
