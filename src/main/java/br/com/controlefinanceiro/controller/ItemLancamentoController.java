package br.com.controlefinanceiro.controller;

import java.util.HashMap;

import java.util.Map;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controlefinanceiro.DTO.Item_LancamentoDTO;
import br.com.controlefinanceiro.DTO.LancamentoDTO;

import br.com.controlefinanceiro.config.RelacionamentoConfig;

import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Item_Lancamento;
import br.com.controlefinanceiro.model.Lancamento;
import br.com.controlefinanceiro.model.Metodo_Pagamento;
import br.com.controlefinanceiro.model.Status_Lancamento;
import br.com.controlefinanceiro.model.Tipo_Conta;
import br.com.controlefinanceiro.model.Tipo_Operacao;
import br.com.controlefinanceiro.repository.ContaRepository;
import br.com.controlefinanceiro.repository.ItemLancamentoRepository;
import br.com.controlefinanceiro.repository.LancamentoRepository;
import br.com.controlefinanceiro.repository.MetodoPagamentoRepository;
import br.com.controlefinanceiro.repository.StatusLancamentoRepository;
import br.com.controlefinanceiro.repository.TipoOperacaoRepository;
import jakarta.annotation.PostConstruct;


@RestController 
@RequestMapping(value = "/itemlancamento", produces = "application/json")
public class ItemLancamentoController  extends BaseController<Item_Lancamento, Item_LancamentoDTO, Long>
{

	private static final String ID_ENTIDADE = "id_itemlancamento";
	private static final Class<Item_Lancamento> ENTIDADECLASS = Item_Lancamento.class;
	private static final Class<Item_LancamentoDTO> ENTIDADECLASSDTO = Item_LancamentoDTO.class;

	// private Map<String, RelacionamentoConfig> relacionamentos;

	//repositorios
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private TipoOperacaoRepository tipoOperacaoRepository;

	@Autowired
	private MetodoPagamentoRepository	metodoPagamentoRepository;

	@Autowired
    public ItemLancamentoController(ItemLancamentoRepository repository)
	{
        super(repository, ENTIDADECLASS, ENTIDADECLASSDTO, ID_ENTIDADE, Map.of());
    }
	
	@PostConstruct
    public void inicializarRelacionamentos()
	{
		Map<String, RelacionamentoConfig> relacionamentos = new HashMap<>();

        // relacionamentos = new HashMap<>();

        relacionamentos.put("id_lancamento", new RelacionamentoConfig(lancamentoRepository, "setLancamento", Lancamento.class));

        relacionamentos.put("id_tipooperacao", new RelacionamentoConfig(tipoOperacaoRepository, "setTipoOperacao", Tipo_Operacao.class));

        relacionamentos.put("id_metodopagamento", new RelacionamentoConfig(metodoPagamentoRepository, "setMetodoPagamento", Metodo_Pagamento.class));

		setRelacionamentos(relacionamentos);
    }
	
	
}

	
	

