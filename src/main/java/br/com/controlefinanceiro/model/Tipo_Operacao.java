package br.com.controlefinanceiro.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Tipo_Operacao {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "tipooperacao_seq")
    private Long id_tipooperacao;

    @NotNull(message = "Descricao é obrigatório!")
    @Column( nullable = false)
    private String ds_tipooperacao;

    public Long getId_tipooperacao() {
        return id_tipooperacao;
    }

    public void setId_tipooperacao(Long id_tipooperacao) {
        this.id_tipooperacao = id_tipooperacao;
    }

    public String getDs_tipooperacao() {
        return ds_tipooperacao;
    }

    public void setDs_tipooperacao(String ds_tipooperacao) {
        this.ds_tipooperacao = ds_tipooperacao;
    }
    

}
