package br.com.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Usuario;

@Repository
public interface ContaRepository  extends CrudRepository<Conta, Long>  {
	
	@Query("SELECT MAX(c.cd_conta) as sequenica  FROM Conta c") // Substitua 'campo' pelo nome real do campo
    Long obterSequencial(); // Retorna o valor máximo como Long

}
