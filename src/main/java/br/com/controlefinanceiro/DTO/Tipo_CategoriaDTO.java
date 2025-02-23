package br.com.controlefinanceiro.DTO;

import java.io.Serializable;

import br.com.controlefinanceiro.model.Tipo_Categoria;

public class Tipo_CategoriaDTO   implements Serializable{

	private Long id_tipocategoria;
    private String nm_tipocategoria;


	public Tipo_CategoriaDTO() {} // Construtor necessário para serialização

	
	public Tipo_CategoriaDTO(Tipo_Categoria tipocategoria) 
	{
		this.id_tipocategoria = tipocategoria.getId_tipocategoria();
		this.nm_tipocategoria = tipocategoria.getNm_tipocategoria();
	}
	
    public Long getId_tipocategoria() {
		return id_tipocategoria;	
	
	}

	public void setId_tipocategoria(Long id_tipocategoria) {
		this.id_tipocategoria = id_tipocategoria;
	}	

	public String getNm_tipocategoria() {
		return nm_tipocategoria;
	}	

	public void setNm_tipocategoria(String nm_tipocategoria) {
		this.nm_tipocategoria = nm_tipocategoria;
	}	

	  
	
    
}
