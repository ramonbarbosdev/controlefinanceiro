package br.com.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Usuario;

@Repository
public interface ContaRepository  extends CrudRepository<Conta, Long>  {
	
    @Query(value = "SELECT CASE WHEN MAX(c.cd_conta) IS NULL THEN '0' ELSE MAX(c.cd_conta) END FROM Conta c", nativeQuery = true)
    Long obterSequencial();

}
