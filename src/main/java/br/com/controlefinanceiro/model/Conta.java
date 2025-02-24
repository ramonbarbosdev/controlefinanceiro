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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "categoria_seq")
	private Long id_conta;
	

	@NotBlank(message = "A Codigo da conta é obrigatorio!")
	@Column(unique = true, nullable = false)
	private String cd_conta;
	
	@NotBlank(message = "A Nome da conta é obrigatorio!")
	private String nm_conta;
	
	@ManyToOne()
    @JoinColumn(name = "id_tipoconta", insertable = false, updatable = false)
    private Tipo_Conta tipoconta; // Agora corretamente mapeado para a entidade TipoConta
	
	@Column(name = "id_tipoconta")
	private Long id_tipoconta;


	private Boolean fl_ativo;

    // Getters e Setters
	public Boolean getFl_ativo() {
		return fl_ativo;
	}

	public void setFl_ativo(Boolean fl_ativo) {
		this.fl_ativo = fl_ativo;
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
