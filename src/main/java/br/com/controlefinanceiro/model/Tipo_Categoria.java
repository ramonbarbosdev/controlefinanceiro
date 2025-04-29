package br.com.controlefinanceiro.model;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Tipo_Categoria {

	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "categoria_seq")
	private Long id_tipocategoria;

	@NotBlank(message = "Coluna 'cd_tipocategoria' é obrigatorio!")
	@Column(unique = true, nullable = false)
	private String cd_tipocategoria;
	
	@NotBlank(message = "Coluna 'nm_tipocategoria' é obrigatorio!")
	@Column(unique = true, nullable = false)
	private String nm_tipocategoria;

	public Long getId_tipocategoria() {
		return id_tipocategoria;
	}

	public void setId_tipocategoria(Long id_tipocategoria) {
		this.id_tipocategoria = id_tipocategoria;
	}

	public String getCd_tipocategoria() {
		return cd_tipocategoria;
	}
	
	public void setCd_tipocategoria(String cd_tipocategoria) {
		this.cd_tipocategoria = cd_tipocategoria;
	}

	public String getNm_tipocategoria() {
		return nm_tipocategoria;
	}

	public void setNm_tipocategoria(String nm_tipocategoria) {
		this.nm_tipocategoria = nm_tipocategoria;
	}

	
}
