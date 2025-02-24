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

    @ManyToOne()
    @JoinColumn(name = "id_lancamento", insertable = false, updatable = false)
    private Lancamento lancamento;

    @NotNull(message = "Lançamento é obrigatório!")
    @Column(name = "id_lancamento")
    private Long id_lancamento;

    @ManyToOne()
    @JoinColumn(name = "id_tipooperacao", insertable = false, updatable = false)
    private Tipo_Operacao tipoOperacao;

    @NotNull(message = "Tipo Operacao é obrigatório!")
    @Column(name = "id_tipooperacao")
    private Long id_tipooperacao;

    @ManyToOne()
    @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
    private Categoria categoria;

    @NotNull(message = "Categoria é obrigatório!")
    @Column(name = "id_categoria")
    private Long id_categoria;

    @NotNull(message = "Valor do movimento é obrigatório!")
    @Column( nullable = false)
    private Double vl_movimento;

    @ManyToOne()
    @JoinColumn(name = "id_metodopagamento", insertable = false, updatable = false)
    private Metodo_Pagamento metodoPagamento;

    @NotNull(message = "Metodo do pagamento é obrigatório!")
    @Column(name = "id_metodopagamento")
    private Long id_metodopagamento;

    public Long getId_itemlancamento() {
        return id_itemlancamento;
    }

    public void setId_itemlancamento(Long id_itemlancamento) {
        this.id_itemlancamento = id_itemlancamento;
    }

   public Long getId_lancamento() {
        return id_lancamento;
    }

    public void setId_lancamento(Long id_lancamento) {
        this.id_lancamento = id_lancamento;
    }


    public Long getId_tipooperacao() {
        return id_tipooperacao;
    }

    public void setId_tipooperacao(Long id_tipooperacao) {
        this.id_tipooperacao = id_tipooperacao;
    }


    public Long getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
    }

    public Double getVl_movimento() {
        return vl_movimento;
    }

    public void setVl_movimento(Double vl_movimento) {
        this.vl_movimento = vl_movimento;
    }

    public Long getId_metodopagamento() {
        return id_metodopagamento;
    }

    public void setId_metodopagamento(Long id_metodopagamento) {
        this.id_metodopagamento = id_metodopagamento;
    }



}
