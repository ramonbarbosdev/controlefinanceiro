package br.com.controlefinanceiro.model;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Tipo_Conta {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id_tipoconta;
	
	@NotBlank(message = "A Codigo do tipo é obrigatorio!")
	@Column(unique = true, nullable = false)
	private String cd_tipoconta;
	
	@NotBlank(message = "A Nome do tipo é obrigatorio!")
	private String nm_tipoconta;

	public Long getId_tipoconta() {
		return id_tipoconta;
	}

	public void setId_tipoconta(Long id_tipoconta) {
		this.id_tipoconta = id_tipoconta;
	}

	public String getCd_tipoconta() {
		return cd_tipoconta;
	}

	public void setCd_tipoconta(String cd_tipoconta) {
		this.cd_tipoconta = cd_tipoconta;
	}

	public String getNm_tipoconta() {
		return nm_tipoconta;
	}

	public void setNm_tipoconta(String nm_tipoconta) {
		this.nm_tipoconta = nm_tipoconta;
	}
	

		
	
	
	
	
	
}
