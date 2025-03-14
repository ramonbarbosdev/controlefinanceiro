package br.com.controlefinanceiro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Categoria {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "categoria_seq")
    private Long id_categoria;

    @ManyToOne()
    @JoinColumn(name = "id_tipocategoria", insertable = false, updatable = false)
    private Tipo_Categoria tipoCategoria;

    @NotNull(message = "Tipo de categoria é obrigatório!")
    @Column(name = "id_tipocategoria")
    private Long id_tipocategoria;

    @NotNull(message = "Nome da categoria é obrigatório!")
    @Column(unique = true, nullable = false)
    private String nm_categoria;

    @NotNull(message = "Codigo é obrigatório!")
    @Column(unique = true, nullable = false)
    private String cd_categoria;

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

    public String getCd_categoria() {
        return cd_categoria;
    }

    public void setCd_categoria(String cd_categoria) {
        this.cd_categoria = cd_categoria;
    }
}
