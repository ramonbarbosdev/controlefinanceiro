package br.com.controlefinanceiro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controlefinanceiro.MensagemException;
import br.com.controlefinanceiro.DTO.Item_LancamentoDTO;
import br.com.controlefinanceiro.DTO.LancamentoDTO;

import br.com.controlefinanceiro.config.RelacionamentoConfig;

import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Item_Lancamento;
import br.com.controlefinanceiro.model.Lancamento;
import br.com.controlefinanceiro.model.Status_Lancamento;
import br.com.controlefinanceiro.repository.ContaRepository;
import br.com.controlefinanceiro.repository.LancamentoRepository;
import br.com.controlefinanceiro.repository.StatusLancamentoRepository;
import br.com.controlefinanceiro.service.Utils;
import jakarta.annotation.PostConstruct;



@RestController 
@RequestMapping(value = "/lancamento", produces = "application/json")
public class LancamentoController  extends BaseController<Lancamento, LancamentoDTO, Long>
{

	
	private static final String ID_ENTIDADE = "id_lancamento";
	private static final Class<Lancamento> ENTIDADECLASS = Lancamento.class;
	private static final Class<LancamentoDTO> ENTIDADECLASSDTO = LancamentoDTO.class;

	//repositorios
	@Autowired
	private StatusLancamentoRepository statusLancamentoRepository;
	
	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private Utils utils;

	@Autowired
	public LancamentoController(LancamentoRepository repository) {

		super(repository, ENTIDADECLASS, ENTIDADECLASSDTO, ID_ENTIDADE, Map.of());
	}
	
	@PostConstruct
    public void inicializarRelacionamentos() 
	{
		Map<String, RelacionamentoConfig> relacionamentos = new HashMap<>();

		relacionamentos.put("id_conta", new RelacionamentoConfig(contaRepository, "setConta", Conta.class));

		relacionamentos.put("id_statuslancamento", new RelacionamentoConfig(statusLancamentoRepository, "setStatusLancamento", Status_Lancamento.class));
		
		setRelacionamentos(relacionamentos);
	}

	@PostMapping(value = "/cadastrar/", produces = "application/json")
	public ResponseEntity<?> cadastrar(@RequestBody LancamentoDTO dto) throws Exception {
		
		//criar lancamento
		Object objeto =  new  Lancamento();
		modelMapper.map(dto, objeto);
		Lancamento objetoTipado = (Lancamento) objeto;
		
		//definir valor padrão
		objetoTipado.setVl_lancamento(0.0);

		//aplicar relacionamentos
		objeto  = utils.aplicarRelacionamentos(objeto , dto, relacionamentos);
		modelMapper.map(objetoTipado, objeto);

		Double vl_lancamento = 0.0;
		

		//associar os relacionamentos nos itens
		for ( Item_LancamentoDTO itemDTO : dto.getItens_lancamento())
		{
			Item_Lancamento item = new Item_Lancamento();
			modelMapper.map(itemDTO, item); 
			
			vl_lancamento += item.getVl_movimento();
	
			item.setLancamento(objetoTipado);
			System.out.println(item);
			
			// objeto.getItens_lancamento().add(item);
		}

		System.out.println("Valor do lançamento: " + vl_lancamento);
        
		
		return new ResponseEntity<>(objeto , HttpStatus.CREATED);
	}

	
	
	
}
