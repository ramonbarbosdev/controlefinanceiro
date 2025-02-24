package br.com.controlefinanceiro.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.controlefinanceiro.DTO.Item_LancamentoDTO;
import br.com.controlefinanceiro.DTO.LancamentoDTO;
import br.com.controlefinanceiro.model.Categoria;
import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Item_Lancamento;
import br.com.controlefinanceiro.model.Lancamento;
import br.com.controlefinanceiro.model.Metodo_Pagamento;
import br.com.controlefinanceiro.model.Status_Lancamento;
import br.com.controlefinanceiro.model.Tipo_Operacao;
import br.com.controlefinanceiro.repository.ItemLancamentoRepository;
import br.com.controlefinanceiro.repository.LancamentoRepository;
import br.com.controlefinanceiro.service.Utils;
import jakarta.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lancamento")
public class LancamentoController extends BaseController<Lancamento, LancamentoDTO,Long> {

    public LancamentoController(CrudRepository<Lancamento, Long> repository) {
        super(repository);
        //TODO Auto-generated constructor stub
    }

  

    // @PostMapping(value = "/cadastrar/", produces = "application/json")
    // public ResponseEntity<Lancamento> criarLancamento(@RequestBody LancamentoDTO lancamentoDTO)
	// {
    //     Lancamento lancamento = new Lancamento();
    //     lancamento.setConta(utils.buscarEntidade(Conta.class, lancamentoDTO.getId_conta()));
    //     lancamento.setStatusLancamento(utils.buscarEntidade(Status_Lancamento.class, lancamentoDTO.getId_statuslancamento()));
    //     lancamento.setDs_lancamento(lancamentoDTO.getDs_lancamento());
    //     lancamento.setDt_lancamento(lancamentoDTO.getDt_lancamento());
    //     lancamento.setVl_lancamento(0.0);

    //     lancamento = lancamentoRepository.save(lancamento);

    //     Double valorTotal = 0.0;
    //     for (Item_LancamentoDTO itemDTO : lancamentoDTO.getItens_lancamento())
	// 	{
    //         Item_Lancamento item = new Item_Lancamento();
    //         item.setLancamento(lancamento);

    //         item.setCategoria(utils.buscarEntidade(Categoria.class, itemDTO.getId_categoria()));
    //         item.setTipoOperacao(utils.buscarEntidade(Tipo_Operacao.class, itemDTO.getId_tipooperacao()));
    //         item.setMetodoPagamento(utils.buscarEntidade(Metodo_Pagamento.class, itemDTO.getId_metodopagamento()));

    //         item.setVl_movimento(itemDTO.getVl_movimento());
    //         valorTotal += itemDTO.getVl_movimento();

    //         itemLancamentoRepository.save(item);
    //     }

    //     lancamento.setVl_lancamento(valorTotal);
    //     lancamento = lancamentoRepository.save(lancamento);

    //     return ResponseEntity.status(HttpStatus.CREATED).body(lancamento);
    // }

  
}
