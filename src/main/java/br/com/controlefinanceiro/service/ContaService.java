package br.com.controlefinanceiro.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.controlefinanceiro.model.Lancamento;
import br.com.controlefinanceiro.repository.LancamentoRepository;


@Service
public class ContaService {

    @Autowired
	private LancamentoRepository lancamentoRepository;

     public ResponseEntity validarCadastro(Long id_conta, Long id_status) 
     {
        if(id_conta != null)
		{
			if( id_status == 2)
			{
				Object	lancamento = lancamentoRepository.findById(id_conta);
				if(lancamento != null)
				{
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("erro","Conta não pode ser desabilitada, já está em uso!"));
				}
			}
			
		}

        return null;
     }

}
