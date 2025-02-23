package br.com.controlefinanceiro.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Status_Lancamento {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "statuslancamento_seq")
    private Long id_statuslancamento;

    @NotNull(message = "Descricao é obrigatório!")
    @Column( nullable = false)
    private String ds_statuslancamento;

    public Long getId_status_lancamento() {
        return id_statuslancamento;
    }

    public void setId_status_lancamento(Long id_statuslancamento) {
        this.id_statuslancamento = id_statuslancamento;
    }

    public String getDs_statuslancamento() {
        return ds_statuslancamento;
    }

    public void setDs_statuslancamento(String ds_statuslancamento) {
        this.ds_statuslancamento = ds_statuslancamento;
    }

}
