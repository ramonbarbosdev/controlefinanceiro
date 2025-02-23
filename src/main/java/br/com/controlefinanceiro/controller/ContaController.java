package br.com.controlefinanceiro.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import br.com.controlefinanceiro.MensagemException;
import br.com.controlefinanceiro.DTO.CategoriaDTO;
import br.com.controlefinanceiro.DTO.ContaDTO;
import br.com.controlefinanceiro.DTO.Item_LancamentoDTO;
import br.com.controlefinanceiro.config.RelacionamentoConfig;
import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Item_Lancamento;
import br.com.controlefinanceiro.model.Lancamento;
import br.com.controlefinanceiro.model.Metodo_Pagamento;
import br.com.controlefinanceiro.model.Tipo_Categoria;
import br.com.controlefinanceiro.model.Tipo_Conta;
import br.com.controlefinanceiro.model.Tipo_Operacao;
import br.com.controlefinanceiro.repository.ContaRepository;
import br.com.controlefinanceiro.repository.TipoCategoriaRepository;
import br.com.controlefinanceiro.repository.TipoContaRepository;
import br.com.controlefinanceiro.service.SequenciaService;
import br.com.controlefinanceiro.service.Utils;
import br.com.controlefinanceiro.service.ValidacaoService;
import ch.qos.logback.core.model.Model;
import jakarta.annotation.PostConstruct;

import java.util.stream.Collectors;



@RestController 
@RequestMapping(value = "/conta", produces = "application/json")
public class ContaController extends BaseController<Conta, ContaDTO, Long>
{

	private static final String ID_ENTIDADE = "id_conta";
	private static final Class<Conta> ENTIDADECLASS = Conta.class;
	private static final Class<ContaDTO> ENTIDADECLASSDTO = ContaDTO.class;

	@Autowired
	private TipoContaRepository tipoContaRepository;

	public ContaController(ContaRepository repository)
	{
		super(repository, ENTIDADECLASS, ENTIDADECLASSDTO, ID_ENTIDADE, Map.of());

	}

	@PostConstruct
    public void inicializarRelacionamentos()
	{
		Map<String, RelacionamentoConfig> relacionamentos = new HashMap<>();

		relacionamentos.put("id_tipoconta", new RelacionamentoConfig(tipoContaRepository, "setTipoConta", Tipo_Conta.class));

		setRelacionamentos(relacionamentos);
    }
	
}
