package br.com.controlefinanceiro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Categoria {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_categoria;

    @NotNull(message = "Tipo de categoria é obrigatório!")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipocategoria", referencedColumnName = "id_tipocategoria", nullable = false)
    private Tipo_Categoria tipoCategoria;

    @NotNull(message = "Nome da categoria é obrigatório!")
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
