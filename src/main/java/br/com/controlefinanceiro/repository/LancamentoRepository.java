package br.com.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Lancamento;
import br.com.controlefinanceiro.model.Status_Lancamento;

@Repository
public interface LancamentoRepository  extends CrudRepository<Lancamento, Long>  {
	
    @Query(value = "SELECT CASE WHEN MAX(c.cd_lancamento) IS NULL THEN '0' ELSE MAX(c.cd_lancamento) END FROM Lancamento c", nativeQuery = true)
    Long obterSequencial();

}
