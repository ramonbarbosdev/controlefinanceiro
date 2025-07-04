package br.com.controlefinanceiro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import br.com.controlefinanceiro.DTO.CategoriaDTO;
import br.com.controlefinanceiro.DTO.Item_LancamentoDTO;
import br.com.controlefinanceiro.DTO.Tipo_CategoriaDTO;
import br.com.controlefinanceiro.DTO.UsuarioDTO;
import br.com.controlefinanceiro.config.RelacionamentoConfig;
import br.com.controlefinanceiro.excecoes.MensagemException;
import br.com.controlefinanceiro.DTO.CategoriaDTO;
import br.com.controlefinanceiro.model.Categoria;
import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Item_Lancamento;
import br.com.controlefinanceiro.model.Lancamento;
import br.com.controlefinanceiro.model.Metodo_Pagamento;
import br.com.controlefinanceiro.model.Tipo_Categoria;
import br.com.controlefinanceiro.model.Tipo_Conta;
import br.com.controlefinanceiro.model.Tipo_Operacao;
import br.com.controlefinanceiro.model.Usuario;
import br.com.controlefinanceiro.repository.CategoriaRepository;
import br.com.controlefinanceiro.repository.ContaRepository;
import br.com.controlefinanceiro.repository.MetodoPagamentoRepository;
import br.com.controlefinanceiro.repository.TipoCategoriaRepository;
import br.com.controlefinanceiro.repository.TipoContaRepository;
import br.com.controlefinanceiro.repository.TipoOperacaoRepository;
import br.com.controlefinanceiro.service.Utils;
import br.com.controlefinanceiro.service.ValidacaoService;
import ch.qos.logback.core.model.Model;
import jakarta.annotation.PostConstruct;

import java.util.stream.Collectors;



@RestController 
@RequestMapping(value = "/tipooperacao", produces = "application/json")
public class TipoOperacaoController extends BaseController<Tipo_Operacao, Tipo_CategoriaDTO, Long>
{
	
	@Autowired
	private TipoOperacaoRepository objetoRepository;

	public TipoOperacaoController(CrudRepository<Tipo_Operacao, Long> repository)
	{
		super(repository);
	}
	
}
