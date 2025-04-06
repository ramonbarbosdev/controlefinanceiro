package br.com.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Tipo_Categoria;
import br.com.controlefinanceiro.model.Tipo_Conta;

@Repository
public interface TipoContaRepository  extends CrudRepository<Tipo_Conta, Long>  {
	
    @Query(value = "SELECT CASE WHEN MAX(c.cd_tipoconta) IS NULL THEN '0' ELSE MAX(c.cd_tipoconta) END FROM Tipo_Conta c", nativeQuery = true)
    Long obterSequencial();

}
