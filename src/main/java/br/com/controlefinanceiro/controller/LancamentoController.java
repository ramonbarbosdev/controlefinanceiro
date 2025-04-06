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
import br.com.controlefinanceiro.excecoes.MensagemException;
import br.com.controlefinanceiro.model.Categoria;
import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Item_Lancamento;
import br.com.controlefinanceiro.model.Lancamento;
import br.com.controlefinanceiro.model.Metodo_Pagamento;
import br.com.controlefinanceiro.model.Status_Lancamento;
import br.com.controlefinanceiro.model.Tipo_Operacao;
import br.com.controlefinanceiro.repository.ItemLancamentoRepository;
import br.com.controlefinanceiro.repository.LancamentoRepository;
import br.com.controlefinanceiro.service.ItemLancamentoService;
import br.com.controlefinanceiro.service.LancamentoService;
import br.com.controlefinanceiro.service.Utils;
import jakarta.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lancamento")
public class LancamentoController extends BaseController<Lancamento, LancamentoDTO,Long> {

    @Autowired
    private LancamentoRepository objetoRepository;

    @Autowired
    private ItemLancamentoRepository itemLancamentoRepository;

    @Autowired
    private ItemLancamentoService itemLancamentoService;

    @Autowired
    private LancamentoService lancamentoService;
    
    public LancamentoController(CrudRepository<Lancamento, Long> repository) {
        super(repository);
        //TODO Auto-generated constructor stub
    }

  

    @PostMapping(value = "/cadastrar/", produces = "application/json")
    public ResponseEntity<Lancamento> criarLancamento(@RequestBody Lancamento objeto)
	{
        objeto.setVl_lancamento(0.0);
        lancamentoService.validacaoCadastrar(objeto);
        objeto = objetoRepository.save(objeto);

        Double vl_lancamento = 0.0;

        //criar uma funcao de salvar os itens do lancamento
        List<Item_Lancamento> itens = objeto.getItenslancamento();
        if(itens != null)
        {
            for(Item_Lancamento item : itens)
            {
                item.setId_lancamento(objeto.getId_lancamento());

                itemLancamentoService.validacaoCadastrar(item, itens, objeto.getId_lancamento());

                item = itemLancamentoRepository.save(item);
                vl_lancamento += item.getVl_movimento();
            }
        }

        objeto.setVl_lancamento(vl_lancamento);
        lancamentoService.validarValorTotalItem(itens, vl_lancamento);
        objeto = objetoRepository.save(objeto);

        return ResponseEntity.status(HttpStatus.CREATED).body(objeto);
    }

   @PutMapping(value = "/atualizar/", produces = "application/json")
    public ResponseEntity<Lancamento> atualizarLancamento(@RequestBody Lancamento objeto)
    {
        lancamentoService.validacaoCadastrar(objeto);
        
        List<Item_Lancamento> itensExistentes = (List<Item_Lancamento>) itemLancamentoRepository.findByLancamentoId(objeto.getId_lancamento());

        Set<Long> idsNovos = objeto.getItenslancamento().stream()
            .map(Item_Lancamento::getId_itemlancamento) 
            .collect(Collectors.toSet());

        
        for (Item_Lancamento itemExistente : itensExistentes)
        {
            if (!idsNovos.contains(itemExistente.getId_itemlancamento()))
            {
                itemLancamentoRepository.delete(itemExistente);
            }
        }

        Double vl_lancamento = 0.0;

        List<Item_Lancamento> itens = objeto.getItenslancamento();
        if (itens != null)
        {
            for (Item_Lancamento item : itens)
            {
                item.setId_lancamento(objeto.getId_lancamento());
                itemLancamentoService.validacaoCadastrar(item, itens, objeto.getId_lancamento());
                itemLancamentoRepository.save(item);

                vl_lancamento += item.getVl_movimento();
            }
        }

        objeto.setVl_lancamento(vl_lancamento);
        lancamentoService.validarValorTotalItem(itens, vl_lancamento);
        objetoRepository.save(objeto);

        return ResponseEntity.status(HttpStatus.CREATED).body(objeto);
    }

    @DeleteMapping(value = "/{id}", produces = "application/text" )
	public ResponseEntity<?> delete (@PathVariable("id") Long id) throws Exception
	{
		try 
		{
            itemLancamentoRepository.deleteByIdLancamento( id);

            objetoRepository.deleteById(id);
			
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Registro deletado!.\"}");

		} 
		catch (Exception e)
		{	
			throw new MensagemException( e.getMessage());
		}
	}

    @GetMapping(value = "/sequencia", produces = "application/json")
    public ResponseEntity<?> obterSequencia()
	{
		Long ultima_sequencia = objetoRepository.obterSequencial() ;
	
		Long sq_sequencia = ultima_sequencia + 1;

        return new ResponseEntity<>( sq_sequencia, HttpStatus.OK);
    }
  
}
