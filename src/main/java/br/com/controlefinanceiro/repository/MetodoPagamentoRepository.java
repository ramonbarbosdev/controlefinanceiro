package br.com.controlefinanceiro.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.controlefinanceiro.model.Metodo_Pagamento;

@Repository
public interface MetodoPagamentoRepository  extends CrudRepository<Metodo_Pagamento, Long>  {
	

}
