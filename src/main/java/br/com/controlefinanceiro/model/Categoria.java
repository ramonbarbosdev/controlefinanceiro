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
public class Categoria {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id_categoria;
	
	@NotBlank(message = "Coluna id_tipocategoria é obrigatorio!")
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipocategoria", referencedColumnName = "id_tipocategoria")
	private Tipo_Categoria tipoCategoria;
	
	@NotBlank(message = "Coluna nm_categoria é obrigatorio!")
	@Column(unique = true, nullable = false)
	private String nm_categoria;

	public Long getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Long id_categoria) {
		this.id_categoria = id_categoria;
	}

	

	public Tipo_Categoria getTipoCategoria() {
		return tipoCategoria;
	}

	public void setTipoCategoria(Tipo_Categoria tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

	public String getNm_categoria() {
		return nm_categoria;
	}

	public void setNm_categoria(String nm_categoria) {
		this.nm_categoria = nm_categoria;
	}

	
	
	
	
}
