package br.com.controlefinanceiro.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class Utils
{
    
     public <T> Object obterObjetoRelacionamento(Object objeto, Object dto, String primaryKey, CrudRepository<T, Long> repository, String methodName, Class<?> parameterType) throws Exception
    {
      
        Object field = obterCampo(dto, primaryKey);

        if (field == null) {
            throw new RuntimeException("O valor para o campo " + primaryKey + " não pode ser nulo");
        }
    
        T resultado = repository.findById((Long) field)
            .orElseThrow(() -> new RuntimeException(primaryKey + " não encontrada"));
    
        Method setMethod = objeto.getClass().getMethod(methodName, parameterType);
        setMethod.invoke(objeto, resultado);
    
        return objeto;
    }

     /**
     * Utiliza reflexão para acessar dinamicamente o valor de um campo de um objeto. 
     * 
     * @param objeto O objeto do qual o campo será acessado.
     * @param campo O nome do campo no objeto.
     * @return O valor do campo solicitado.
     * @throws Exception Lança exceções se o campo não existir ou se o acesso for ilegal.
     */
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
