package br.com.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.controlefinanceiro.model.Conta;
import br.com.controlefinanceiro.model.Tipo_Categoria;
import br.com.controlefinanceiro.model.Tipo_Conta;

@Repository
public interface TipoCategoriaRepository  extends CrudRepository<Tipo_Categoria, Long>  {
	

}
