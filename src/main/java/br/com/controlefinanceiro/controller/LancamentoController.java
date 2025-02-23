package br.com.controlefinanceiro.controller;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.controlefinanceiro.DTO.LancamentoDTO;

import br.com.controlefinanceiro.config.RelacionamentoConfig;

import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Lancamento;
import br.com.controlefinanceiro.model.Status_Lancamento;
import br.com.controlefinanceiro.repository.ContaRepository;
import br.com.controlefinanceiro.repository.LancamentoRepository;
import br.com.controlefinanceiro.repository.StatusLancamentoRepository;



@RestController 
@RequestMapping(value = "/lancamento", produces = "application/json")
public class LancamentoController  extends BaseController<Lancamento, LancamentoDTO, Long>
{

	@Autowired
	public LancamentoController(LancamentoRepository lancamentoRepository, ContaRepository contaRepository, StatusLancamentoRepository statusLancamentoRepository) {

		super(lancamentoRepository,Lancamento.class, LancamentoDTO.class, "id_lancamento",inicializarRelacionamentos(contaRepository, statusLancamentoRepository));
		
	}
	
	private static Map<String, RelacionamentoConfig> inicializarRelacionamentos(ContaRepository contaRepository, StatusLancamentoRepository statusLancamentoRepository)
	{
		Map<String, RelacionamentoConfig> relacionamentos = new HashMap<>();

		relacionamentos.put("id_conta", new RelacionamentoConfig(contaRepository, "setConta", Conta.class));

		relacionamentos.put("id_statuslancamento", new RelacionamentoConfig(statusLancamentoRepository, "setStatusLancamento", Status_Lancamento.class));
		
		return relacionamentos;
	}
	
	
}
