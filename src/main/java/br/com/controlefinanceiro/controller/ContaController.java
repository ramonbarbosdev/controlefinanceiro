package br.com.controlefinanceiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controlefinanceiro.repository.ContaRepository;

@RestController 
@RequestMapping(value = "/conta")
public class ContaController {

	@Autowired
	private ContaRepository contaRepository;
	
}
