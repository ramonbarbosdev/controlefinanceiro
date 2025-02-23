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
import br.com.controlefinanceiro.repository.ContaRepository;
import br.com.controlefinanceiro.repository.LancamentoRepository;



@RestController 
@RequestMapping(value = "/lancamento", produces = "application/json")
public class LancamentoController  extends BaseController<Lancamento, LancamentoDTO, Long>
{

	@Autowired
	public LancamentoController(LancamentoRepository lancamentoRepository, ContaRepository contaRepository) {

		super(lancamentoRepository,Lancamento.class, LancamentoDTO.class, "id_lancamento", Map.of());
		
	}
	
	private static Map<String, RelacionamentoConfig> inicializarRelacionamentos(ContaRepository repository)
	{
		Map<String, RelacionamentoConfig> relacionamentos = new HashMap<>();

		relacionamentos.put("id_conta", new RelacionamentoConfig(repository, "setConta", Conta.class));
		
		return relacionamentos;
	}
	
	
}
