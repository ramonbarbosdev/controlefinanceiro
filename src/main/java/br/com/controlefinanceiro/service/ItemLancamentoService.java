package br.com.controlefinanceiro.service;

import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controlefinanceiro.model.Item_Lancamento;
import br.com.controlefinanceiro.repository.CategoriaRepository;
import br.com.controlefinanceiro.repository.ItemLancamentoRepository;

@Service
public class ItemLancamentoService {
    
    @Autowired
    private ItemLancamentoRepository itemLancamentoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private Utils utils;

    public void validacaoCadastrar(Item_Lancamento item, List<Item_Lancamento> listaItens,  Long id_lancamento) throws RuntimeException
    {
        
        validarTipoOperacao(item, listaItens, id_lancamento);
        validarCategoria(item, listaItens, id_lancamento);
        validarValorMovimento(item, listaItens, id_lancamento);
        validarMetodoPagamento(item, listaItens, id_lancamento);

    }

    public void validarMetodoPagamento(Item_Lancamento item, List<Item_Lancamento> listaItens, Long id_lancamento) throws RuntimeException
    {
        Long id_metodoPagamento = item.getId_metodopagamento();
        int[] metodoPagamentoPermitido = {1,2,3};

        if(!utils.inArray(metodoPagamentoPermitido, id_metodoPagamento))
        {
            throw new RuntimeException("Método de pagamento não permitido");
        }
    }

    public void validarValorMovimento(Item_Lancamento item, List<Item_Lancamento> listaItens, Long id_lancamento) throws RuntimeException
    {
        Double vl_movimento = item.getVl_movimento();

        if(vl_movimento == null || vl_movimento == 0.0)
        {
            throw new RuntimeException("Valor do movimento não pode ser nulo ou zero");
        }
    }

    public void validarCategoria(Item_Lancamento item, List<Item_Lancamento> listaItens, Long id_lancamento) throws RuntimeException
    {
        Long id_categoria = item.getId_categoria();
        Long id_tipooperacao = item.getId_tipooperacao();

        if(id_tipooperacao != 1) //diferente de saldo inicial
        {
            Boolean fl_existeCategoria = categoriaRepository.existeCategoria(id_categoria);

            if(fl_existeCategoria == null || !fl_existeCategoria)
            {
                throw new RuntimeException("Categoria não encontrada");
            }
        }
    }

    public void validarTipoOperacao(Item_Lancamento item, List<Item_Lancamento> listaItens, Long id_lancamento) throws RuntimeException
    {
        Long id_itemlancamento =  item.getId_itemlancamento();
        Long id_tipooperacao = item.getId_tipooperacao();
        Long id_categoria = item.getId_categoria();
        Long tipoSaldoPermitido = (long) 1;
        int[] tipoOperacaoPermitido = {1,2,3};
        int[] tipoMovimentoPermitido = {2,3};

        
        //verificar se o tipo de operacao é permitido
        if(!utils.inArray(tipoOperacaoPermitido, id_tipooperacao) )
        {
            throw new RuntimeException("Tipo de operação não permitido");
        }

        if(utils.inArray(tipoMovimentoPermitido, id_tipooperacao) && id_categoria == null)
        {
            throw new RuntimeException("Categoria é obrigatória para este tipo de operação");
        }

        if( id_tipooperacao.equals(tipoSaldoPermitido) && id_categoria != null )
        {
            throw new RuntimeException("Não é permitido informar Categoria com tipo de operação 'Saldo inicial'");
        }

        if (!id_tipooperacao.equals(tipoSaldoPermitido) && id_categoria == null)
        {
            throw new RuntimeException("Categoria é obrigatória para este tipo de operação");
        }

        Boolean fl_existeTipoSaldoInicial = itemLancamentoRepository.verificarSaldoInicial(id_lancamento, id_tipooperacao);
        int contadorTipoSaldoInicial = 0;

        //verificar se o saldo inicial é o primeiro item do lancamento
        if(!listaItens.get(0).getId_tipooperacao().equals(tipoSaldoPermitido))
        {
            throw new RuntimeException("O tipo de operacao 'Saldo inicial' deve ser o primeiro item do lançamento");
        }
        
        //verificando se ja existe saldo inicial na lista
        for(Item_Lancamento i : listaItens)
        {
            if(i.getId_tipooperacao().equals(tipoSaldoPermitido) /*&&  i.getId_lancamento().equals(id_lancamento)*/)
            {
                contadorTipoSaldoInicial++;
            }
        }

        if(fl_existeTipoSaldoInicial != null && fl_existeTipoSaldoInicial && id_itemlancamento == null)
        {
            throw new RuntimeException("Já existe um saldo inicial cadastrado para este tipo de operação");
        }

        if(contadorTipoSaldoInicial > 1)
        {
            throw new RuntimeException("Já existe um saldo inicial cadastrado para este tipo de operação");
        }

    }

  
}
