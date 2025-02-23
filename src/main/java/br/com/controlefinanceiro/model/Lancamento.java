package br.com.controlefinanceiro.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Lancamento {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "lancamento_seq")
    private Long id_lancamento;

    @NotNull(message = "Conta é obrigatório!")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_conta", referencedColumnName = "id_conta", nullable = false)
    private Conta conta;

    @NotNull(message = "Data é obrigatório!")
    @Column( nullable = false)
    private LocalDate  dt_lancamento;

    private String ds_lancamento;

    @NotNull(message = "Valor Lançamento é obrigatório!")
    @Column( nullable = false)
    private Double vl_lancamento;

    @NotNull(message = "Status é obrigatório!")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_statuslancamento", referencedColumnName = "id_statuslancamento", nullable = false)
    private Status_Lancamento statusLancamento;

    public Long getId_lancamento() {
        return id_lancamento;
    }

    public void setId_lancamento(Long id_lancamento) {
        this.id_lancamento = id_lancamento;
    }   

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
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

    public Status_Lancamento getStatusLancamento() {
        return statusLancamento;
    }

    public void setStatusLancamento(Status_Lancamento statusLancamento) {
        this.statusLancamento = statusLancamento;
    }


}
