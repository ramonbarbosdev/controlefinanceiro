package br.com.controlefinanceiro.service;

import java.lang.reflect.Field;

import org.springframework.stereotype.Service;

@Service
public class ValidacaoService 
{
    
    public void validarCadastroGeral(Object objeto,String primaryKey)  throws Exception
    {
        validaIdNaoInformado(objeto, primaryKey);
    }

    public void validaIdNaoInformado(Object objeto, String primaryKey) throws Exception
    {
      
        Object idValue = obterCampo(objeto, primaryKey);

        if(idValue != null)
        {
            throw new Exception("O identificador não pode ser informado no cadastro!");
        }
    }

    public Object obterCampo(Object objeto, String campo) throws Exception
    {
        Field field = objeto.getClass().getDeclaredField(campo);

        field.setAccessible(true);

        Object fieldValue = field.get(objeto);

        // if(fieldValue == null)
        // {
        //     throw new Exception("Campo não informado!");
        // }

        return fieldValue;
    }

}
