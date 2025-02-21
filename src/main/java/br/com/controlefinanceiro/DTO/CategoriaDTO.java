package br.com.controlefinanceiro.DTO;

import java.io.Serializable;

import br.com.controlefinanceiro.model.Categoria;

public class CategoriaDTO   implements Serializable{

	private Long id_categoria;
    private Long id_tipocategoria;
    private String nm_categoria;

	public CategoriaDTO(Categoria categoria) 
	{
		this.id_categoria = categoria.getId_categoria();
		this.id_tipocategoria = categoria.getTipoCategoria().getId_tipocategoria();
		this.nm_categoria = categoria.getNm_categoria();
	}
	
    
    
	public Long getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(Long id_categoria) {
		this.id_categoria = id_categoria;
	}
	public Long getId_tipocategoria() {
		return id_tipocategoria;
	}
	public void setId_tipocategoria(Long id_tipocategoria) {
		this.id_tipocategoria = id_tipocategoria;
	}
	public String getNm_categoria() {
		return nm_categoria;
	}
	public void setNm_categoria(String nm_categoria) {
		this.nm_categoria = nm_categoria;
	}
    
    
	
    
}
