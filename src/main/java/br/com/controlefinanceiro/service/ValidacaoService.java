package br.com.controlefinanceiro.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import br.com.controlefinanceiro.model.Tipo_Conta;

@Service
public class ValidacaoService 
{
    
   	@Autowired
	private Utils utils;


   /**
     * Valida o objeto de forma genérica, 
     * 
     * @param objeto O objeto que será validado. Pode ser qualquer entidade ou objeto que possua uma chave primária.
     * @param primaryKey O nome do campo que representa a chave primária no objeto (por exemplo, "id_conta", "id_categoria").
     * @throws Exception Lança uma exceção caso o campo de chave primária esteja informado no cadastro.
     */ 
    public void validarCadastroGeral(Object objeto,String primaryKey)  throws Exception
    {
        validaIdNaoInformado(objeto, primaryKey);
    }

    /**
     * Verifica se o campo identificado como chave primária no objeto contém algum valor. 
     * Se o valor for diferente de nulo, uma exceção será lançada indicando que o ID 
     * não pode ser informado durante o cadastro.
     * 
     * @param objeto O objeto a ser validado.
     * @param primaryKey O nome do campo que representa a chave primária no objeto.
     * @throws Exception Lança uma exceção caso o campo de chave primária esteja informado no cadastro.
     */
    public void validaIdNaoInformado(Object objeto, String primaryKey) throws Exception
    {
      
        Object idValue = utils.obterCampo(objeto, primaryKey);

        if(idValue != null)
        {
            throw new Exception("O identificador não pode ser informado no cadastro!");
        }
    }

 
}
