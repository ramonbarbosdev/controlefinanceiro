package br.com.controlefinanceiro.service;

import java.lang.reflect.Field;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoService 
{
    
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
      
        Object idValue = obterCampo(objeto, primaryKey);

        if(idValue != null)
        {
            throw new Exception("O identificador não pode ser informado no cadastro!");
        }
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


   public <T> void validaExistenciaRegistro(Object objeto, String primaryKey, CrudRepository<T, Long> repository) throws Exception 
    {
        // Obtendo o valor do campo que é a chave primária
        Object valorRelacionado = obterCampo(objeto, primaryKey);

        // Usando o repository para buscar o registro
        Optional<T> resultado = repository.findById((Long) valorRelacionado);

        // Se o resultado for vazio, significa que o registro não foi encontrado
        if (resultado.isEmpty()) {
            throw new Exception("Registro não encontrado para o valor " + valorRelacionado);
        }

  
    }

    

}
