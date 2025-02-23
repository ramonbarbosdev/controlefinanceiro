package br.com.controlefinanceiro.controller;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
}
