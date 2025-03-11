package br.com.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Status_Conta;
import br.com.controlefinanceiro.model.Status_Lancamento;

@Repository
public interface StatusContaRepository  extends CrudRepository<Status_Conta, Long>  {
	
	  
}
