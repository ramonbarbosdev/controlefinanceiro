package br.com.controlefinanceiro.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Status_Conta {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "statusconta_seq")
    private Long id_statusconta;

    @NotNull(message = "Nome é obrigatório!")
    @Column( nullable = false)
    private String nm_statusconta;

    public Long getId_statusconta() {
        return id_statusconta;
    }

    public void setId_statusconta(Long id_statusconta) {
        this.id_statusconta = id_statusconta;
    }

    public String getNm_statusconta() {
        return nm_statusconta;
    }

    public void setNm_statusconta(String nm_statusconta) {
        this.nm_statusconta = nm_statusconta;
    }

}
