package br.com.controlefinanceiro.service;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Item_Lancamento;
import br.com.controlefinanceiro.model.Lancamento;
import br.com.controlefinanceiro.model.Status_Lancamento;
import br.com.controlefinanceiro.repository.CategoriaRepository;
import br.com.controlefinanceiro.repository.ContaRepository;
import br.com.controlefinanceiro.repository.ItemLancamentoRepository;
import br.com.controlefinanceiro.repository.StatusLancamentoRepository;



@Service
public class LancamentoService {
    
    @Autowired
    private Utils utils;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private StatusLancamentoRepository statusLancamentoRepository;;

    public void validacaoCadastrar(Lancamento objeto) throws RuntimeException
    {
        validarConta(objeto);
        validarDataLancamento(objeto);
        validarDescricao(objeto);
        // validarValorLancamento(objeto);
        validarStausLancamento(objeto);
    }

    public void validarStausLancamento(Lancamento objeto) throws RuntimeException
    {

        int [] statusValidos = {1, 2, 3};

        Long id_status = objeto.getId_statuslancamento();

        if(id_status == null)
        {
            throw new RuntimeException("Status do lançamento não pode ser nulo");
        }

        if(!utils.inArray(statusValidos, id_status))
        {
            throw new RuntimeException("Status do lançamento inválido");
        }

    }

    public void validarValorTotalItem(List<Item_Lancamento> listaItens, Double vl_lancamento) throws RuntimeException
    {
        Double vl_total = 0.0;

      
        for(Item_Lancamento item : listaItens)
        {
            vl_total += item.getVl_movimento();
        }

        if(vl_total == null || vl_total == 0.0)
        {
            throw new RuntimeException("Valor do lançamento não pode ser nulo ou zero");
        }
        final double EPSILON = 0.00001; // Ajuste conforme necessário

        if (Math.abs(vl_total - vl_lancamento) > EPSILON)
        {
            throw new RuntimeException("Valor total dos itens não corresponde ao valor do lançamento");
        }
    }


    public void validarValorLancamento(Lancamento objeto) throws RuntimeException
    {
        Double vl_lancamento = objeto.getVl_lancamento();

        if(vl_lancamento == null || vl_lancamento == 0.0)
        {
            throw new RuntimeException("Valor do lançamento não pode ser nulo ou zero");
        }

    }

    public void validarDescricao(Lancamento objeto) throws RuntimeException
    {
        String descricao = objeto.getDs_lancamento();

        if(descricao == null || descricao.isEmpty())
        {
            throw new RuntimeException("Descrição do lançamento não pode ser nula ou vazia");
        }
        if(descricao.length() > 255)
        {
            throw new RuntimeException("Descrição do lançamento não pode ter mais de 100 caracteres");
        }
    }
    public void validarDataLancamento(Lancamento objeto) throws RuntimeException
    {
        LocalDate dt_lancamento = objeto.getDt_lancamento();
        LocalDate dataAtual = LocalDate.now();

        if(dt_lancamento == null)
        {
            throw new RuntimeException("Data de lançamento não pode ser nula");
        }

        if (dt_lancamento.isAfter(dataAtual)) 
        {
            throw new RuntimeException("Data de lançamento não pode ser uma data futura");
        }

       
    }

    public void validarConta(Lancamento objeto) throws RuntimeException
    {
        Long id_conta = objeto.getId_conta();
        Conta conta = contaRepository.findById(id_conta).get();
        Long id_contaativa = conta.getId_statusconta();

        if(id_conta == null)
        {
            throw new RuntimeException("Conta não pode ser nula e deve estar ativa");
        }

        if(id_contaativa == null )
        {
            throw new RuntimeException("Conta deve estar ativa");
        }
    }
}
