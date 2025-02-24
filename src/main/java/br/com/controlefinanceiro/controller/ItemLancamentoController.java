package br.com.controlefinanceiro.controller;

import java.util.HashMap;

import java.util.Map;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
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

	public ItemLancamentoController(CrudRepository<Item_Lancamento, Long> repository) {
		super(repository);
		//TODO Auto-generated constructor stub
	}

	
}

	
	

