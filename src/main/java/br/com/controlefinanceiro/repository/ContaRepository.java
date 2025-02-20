package br.com.controlefinanceiro.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.controlefinanceiro.model.Conta;

public interface ContaRepository  extends CrudRepository<Conta, Long> {

}
