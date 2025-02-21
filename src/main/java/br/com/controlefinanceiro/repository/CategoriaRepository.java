package br.com.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.controlefinanceiro.model.Categoria;
import br.com.controlefinanceiro.model.Conta;

@Repository
public interface CategoriaRepository  extends CrudRepository<Categoria, Long>  {
	
	 

}
