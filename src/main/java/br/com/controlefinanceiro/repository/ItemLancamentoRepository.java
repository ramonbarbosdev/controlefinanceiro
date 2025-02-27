package br.com.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Item_Lancamento;
import br.com.controlefinanceiro.model.Lancamento;
import br.com.controlefinanceiro.model.Status_Lancamento;

@Repository
public interface ItemLancamentoRepository  extends CrudRepository<Item_Lancamento, Long> 
{
    @Query(value = "select cast(1 as bool) as fl_existe from item_lancamento il where il.id_tipooperacao = ?2 and id_lancamento = ?1", nativeQuery = true)
    Boolean verificarSaldoInicial(Long id_lancamento,Long id_tipoopercao);

    @org.springframework.transaction.annotation.Transactional
    @Modifying
    @Query("DELETE FROM Item_Lancamento i WHERE i.id_lancamento = :id_lancamento")
    void deleteByIdLancamento(@Param("id_lancamento") Long idLancamento);
	  
}
