package br.com.controlefinanceiro.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Item_Lancamento {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "item_lancamento_seq")
    private Long id_itemlancamento;

    @NotNull(message = "Lançamento é obrigatório!")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_lancamento", referencedColumnName = "id_lancamento", nullable = false)
    private Lancamento lancamento;

    @NotNull(message = "Tipo Operacao é obrigatório!")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipooperacao", referencedColumnName = "id_tipooperacao", nullable = false)
    private Tipo_Operacao tipoOperacao;

    @NotNull(message = "Categoria é obrigatório!")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria", nullable = false)
    private Categoria categoria;

    @NotNull(message = "Valor do movimento é obrigatório!")
    @Column( nullable = false)
    private Double vl_movimento;

    @NotNull(message = "Metodo do pagamento é obrigatório!")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_metodopagamento", referencedColumnName = "id_metodopagamento", nullable = false)
    private Metodo_Pagamento metodoPagamento;

    public Long getId_itemlancamento() {
        return id_itemlancamento;
    }

    public void setId_itemlancamento(Long id_itemlancamento) {
        this.id_itemlancamento = id_itemlancamento;
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public Tipo_Operacao getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(Tipo_Operacao tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getVl_movimento() {
        return vl_movimento;
    }

    public void setVl_movimento(Double vl_movimento) {
        this.vl_movimento = vl_movimento;
    }

    public Metodo_Pagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(Metodo_Pagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }



}
