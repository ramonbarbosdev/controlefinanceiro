package br.com.controlefinanceiro.model;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Conta {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id_conta;
	

	@NotBlank(message = "A Conta é obrigatorio!")
	@Column(unique = true, nullable = false)
	private String cd_conta;
	
	private String nm_conta;

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
