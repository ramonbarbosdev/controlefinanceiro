package br.com.controlefinanceiro.model;

import java.util.Optional;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Conta {

	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "conta_seq")
	private Long id_conta;
	

	@NotBlank(message = "A Codigo da conta é obrigatorio!")
	@Column(unique = true, nullable = false)
	private String cd_conta;
	
	@NotBlank(message = "A Nome da conta é obrigatorio!")
	private String nm_conta;
	
	@ManyToOne()
    @JoinColumn(name = "id_tipoconta", insertable = false, updatable = false)
    private Tipo_Conta tipoconta; 
	
	@Column(name = "id_tipoconta")
	private Long id_tipoconta;

	@ManyToOne()
    @JoinColumn(name = "id_statusconta", insertable = false, updatable = false)
	private Status_Conta statusconta;

	@Column(name = "id_statusconta")
	private Long id_statusconta;

    // Getters e Setters
	public Long getId_statusconta() {
		return id_statusconta;
	}

	public void setId_statusconta(Long id_statusconta) {
		this.id_statusconta = id_statusconta;
	}

	public Long getId_tipoconta() {
		return id_tipoconta;
	}

	public void setId_tipoconta(Long id_tipoconta) {
		this.id_tipoconta = id_tipoconta;
	}

	public Long getId_conta() {
		return id_conta;
	}

	public void setId_conta(Long id_conta) {
		this.id_conta = id_conta;
	}

	public String getCd_conta() {
		return cd_conta;
	}

	public void setCd_conta(String cd_conta) {
		this.cd_conta = cd_conta;
	}

	public String getNm_conta() {
		return nm_conta;
	}

	public void setNm_conta(String nm_conta) {
		this.nm_conta = nm_conta;
	}
	
	
	
}
