package br.com.controlefinanceiro.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Lancamento {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "lancamento_seq")
    private Long id_lancamento;

    @ManyToOne()
    @JoinColumn(name = "id_conta" , insertable = false, updatable = false)
    private Conta conta;

    @NotNull(message = "Conta é obrigatório!")
    @Column(name = "id_conta")
    private Long id_conta;

    @NotNull(message = "Data é obrigatório!")
    @Column( nullable = false)
    private LocalDate  dt_lancamento;

    private String ds_lancamento;

    @NotNull(message = "Valor Lançamento é obrigatório!")
    @Column( nullable = false)
    private Double vl_lancamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_statuslancamento", insertable = false, updatable = false)
    private Status_Lancamento statusLancamento;

    @NotNull(message = "Status é obrigatório!")
    @Column(name = "id_statuslancamento")
    private Long id_statuslancamento;
   
   
    public Long getId_lancamento() {
        return id_lancamento;
    }

    public void setId_lancamento(Long id_lancamento) {
        this.id_lancamento = id_lancamento;
    }   

    public Long getId_conta() {
        return id_conta;
    }

    public void setId_conta(Long id_conta) {
        this.id_conta = id_conta;
    }

    public LocalDate getDt_lancamento() {
        return dt_lancamento;
    }

    public void setDt_lancamento(LocalDate dt_lancamento) {
        this.dt_lancamento = dt_lancamento;
    }

    public String getDs_lancamento() {
        return ds_lancamento;
    }

    public void setDs_lancamento(String ds_lancamento) {
        this.ds_lancamento = ds_lancamento;
    }

    public Double getVl_lancamento() {
        return vl_lancamento;
    }

    public void setVl_lancamento(Double vl_lancamento) {
        this.vl_lancamento = vl_lancamento;
    }

    
    public Long getId_statuslancamento() {
        return id_statuslancamento;
    }

    public void setId_statuslancamento(Long id_statuslancamento) {
        this.id_statuslancamento = id_statuslancamento;
    }


}
