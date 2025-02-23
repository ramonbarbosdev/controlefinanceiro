package br.com.controlefinanceiro.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Metodo_Pagamento {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "metodopagamento_seq")
    private Long id_metodopagamento;

    @NotNull(message = "Descricao é obrigatório!")
    @Column( nullable = false)
    private String ds_metodopagamento;

    public Long getId_metodopagamento() {
        return id_metodopagamento;
    }

    public void setId_metodopagamento(Long id_metodopagamento) {
        this.id_metodopagamento = id_metodopagamento;
    }

    public String getDs_metodopagamento() {
        return ds_metodopagamento;
    }

    public void setDs_metodopagamento(String ds_metodopagamento) {
        this.ds_metodopagamento = ds_metodopagamento;
    }
    
    

}
