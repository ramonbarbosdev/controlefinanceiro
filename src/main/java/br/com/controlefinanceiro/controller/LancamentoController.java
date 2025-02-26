package br.com.controlefinanceiro.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.controlefinanceiro.MensagemException;
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
import br.com.controlefinanceiro.service.LancamentoService;
import br.com.controlefinanceiro.service.Utils;
import jakarta.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lancamento")
public class LancamentoController extends BaseController<Lancamento, LancamentoDTO,Long> {

    @Autowired
    private LancamentoRepository objetoReporitory;

    @Autowired
    private ItemLancamentoRepository itemLancamentoRepository;

    @Autowired
    private LancamentoService lancamentoService;
    
    public LancamentoController(CrudRepository<Lancamento, Long> repository) {
        super(repository);
        //TODO Auto-generated constructor stub
    }

  

    @PostMapping(value = "/cadastrar/", produces = "application/json")
    public ResponseEntity<Lancamento> criarLancamento(@RequestBody Lancamento objeto)
	{
        try
        {
            objeto.setVl_lancamento(0.0);

            objeto = objetoReporitory.save(objeto);

            Double vl_lancamento = 0.0;

            //criar uma funcao de salvar os itens do lancamento
            List<Item_Lancamento> itens = objeto.getItenslancamento();
            if(itens != null)
            {
                for(Item_Lancamento item : itens)
                {
                    item.setId_lancamento(objeto.getId_lancamento());

                    lancamentoService.validacaoCadastrar(item, itens, objeto.getId_lancamento());

                    item = itemLancamentoRepository.save(item);
                    vl_lancamento += item.getVl_movimento();
                }
            }
            objeto.setVl_lancamento(vl_lancamento);
            objeto = objetoReporitory.save(objeto);

            return ResponseEntity.status(HttpStatus.CREATED).body(objeto);
        }
        catch(Exception e)
        {
           throw new MensagemException( e.getMessage());
        }
    }

  
}
