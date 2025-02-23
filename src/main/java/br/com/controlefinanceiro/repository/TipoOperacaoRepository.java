package br.com.controlefinanceiro.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import br.com.controlefinanceiro.model.Tipo_Operacao;

@Repository
public interface TipoOperacaoRepository  extends CrudRepository<Tipo_Operacao, Long>  {
	

}
