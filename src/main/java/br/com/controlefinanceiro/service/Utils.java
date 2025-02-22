package br.com.controlefinanceiro.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import br.com.controlefinanceiro.DTO.ContaDTO;
import br.com.controlefinanceiro.model.Conta;

@Service
public class Utils
{

    @Autowired
    private ApplicationContext applicationContext;

    
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

    public CrudRepository obterRepositoryEntidade(Object entity)
    {
        String repositoryBeanName = entity.getClass().getSimpleName() + "Repository";
        return (CrudRepository) applicationContext.getBean("contaRepository");
    }

    public  Object obterClasseDtoEntidade(Class<?> model) throws Exception
    {
        String entityClassName = model.getSimpleName();
    
        String basePackage = "br.com.controlefinanceiro.DTO";  // Altere para o pacote real onde seus DTOs estão
    
        String fullClassName = basePackage + "." + entityClassName + "DTO";
    
        Class<?> dtoClass = Class.forName(fullClassName);
        Object objetoDTO = dtoClass.getDeclaredConstructor().newInstance();
        
        return objetoDTO;
    }
    
    
    
    
    // public CrudRepository obterRepositoryEntidade(Object entity) {
    //     String repositoryBeanName = entity.getClass().getSimpleName().toLowerCase() + "Repository";
        
    //     // Log para imprimir todos os beans do tipo ContaRepository
    //     System.out.println("Beans no contexto:");
    //     for (String beanName : applicationContext.getBeanDefinitionNames()) {
    //         System.out.println(beanName);
    //     }
    
    //     try {
    //         return (CrudRepository) applicationContext.getBean(repositoryBeanName);
    //     } catch (Exception e) {
    //         throw new RuntimeException("Repositório não encontrado para a entidade: " + entity.getClass().getSimpleName());
    //     }
    // }
    



}
