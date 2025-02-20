package br.com.controlefinanceiro.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import br.com.controlefinanceiro.repository.SequencialRepository;

@Service
public class SequenciaService {


	public <T, ID, R extends CrudRepository<T, ID> & SequencialRepository> Long gerarProximaSequencia(R repository, String campo) {
	    Long ultimoNumero = repository.obterSequencial(campo);
	    return (ultimoNumero == null) ? 1L : ultimoNumero + 1;
	}


}
