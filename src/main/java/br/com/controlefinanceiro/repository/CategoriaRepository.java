package br.com.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.controlefinanceiro.model.Categoria;
import br.com.controlefinanceiro.model.Conta;

@Repository
public interface CategoriaRepository  extends CrudRepository<Categoria, Long>  {
	
    // @Query(value = "select cast(1 as bool) as fl_existe from item_lancamento il where il.id_tipooperacao = ?2 and id_lancamento = ?1", nativeQuery = true)
    // Boolean verificarSaldoInicial(Long id_lancamento,Long id_tipoopercao);

    @Query(value = "select cast(1 as bool) as fl_existe from categoria c where c.id_categoria = ?1", nativeQuery = true)
    Boolean existeCategoria(Long id_categoria);

}
