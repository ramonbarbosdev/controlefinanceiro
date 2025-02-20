package br.com.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.controlefinanceiro.model.Conta;

@Repository
public interface ContaRepository  extends CrudRepository<Conta, Long>, SequencialRepository  {
	
	  @Query(value = "SELECT MAX(:campo) FROM conta", nativeQuery = true)
	  Long obterSequencial(String campo);

}
