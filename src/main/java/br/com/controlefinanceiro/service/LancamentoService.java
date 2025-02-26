package br.com.controlefinanceiro.service;

import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controlefinanceiro.model.Item_Lancamento;
import br.com.controlefinanceiro.repository.ItemLancamentoRepository;

@Service
public class LancamentoService {
    
    @Autowired
    private ItemLancamentoRepository itemLancamentoRepository;

    @Autowired
    private Utils utils;

    public void validacaoCadastrar(Item_Lancamento item, List<Item_Lancamento> listaItens,  Long id_lancamento) throws RuntimeException
    {
        
        validarTipoOperacao(item, listaItens, id_lancamento);

    }

    public void validarTipoOperacao(Item_Lancamento item, List<Item_Lancamento> listaItens, Long id_lancamento) throws RuntimeException
    {
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
        
        if (!id_tipooperacao.equals(tipoSaldoPermitido) && id_categoria == null) {
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

        if(fl_existeTipoSaldoInicial != null && fl_existeTipoSaldoInicial)
        {
            throw new RuntimeException("Já existe um saldo inicial cadastrado para este tipo de operação");
        }
        if(contadorTipoSaldoInicial > 1)
        {
            throw new RuntimeException("Já existe um saldo inicial cadastrado para este tipo de operação");
        }

        
      
        

    }

}
