package br.com.controlefinanceiro.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        String entityName = entity.getClass().getSimpleName();
    
        //convertendo a primeira letra para minúscula
        String repositoryBeanName = 
            entityName.substring(0, 1).toLowerCase() + entityName.substring(1) + "Repository";
        
        return (CrudRepository) applicationContext.getBean(repositoryBeanName);
    }

    public Class<?> obterClasseDtoEntidade(Class<?> entityClass) throws Exception
    {
        String entityClassName = entityClass.getSimpleName();
    
        String basePackage = "br.com.controlefinanceiro.DTO";  
    
        String fullClassName = basePackage + "." + entityClassName + "DTO";
    
        return Class.forName(fullClassName);
    }
    
    public  Object obterObjetoDtoEntidade(Class<?> model) throws Exception
    {
        Class<?> dtoClass = obterClasseDtoEntidade(model);
        Object objetoDTO = dtoClass.getDeclaredConstructor().newInstance();
        
        return objetoDTO;
    }



    public <T> T mapEntityToDto(Object entity, Class<T> dtoClass) throws Exception {
        // Cria uma nova instância do DTO
        T dto = dtoClass.getDeclaredConstructor().newInstance();
        
        // Percorre as propriedades da entidade
        for (Field entityField : entity.getClass().getDeclaredFields()) {
            entityField.setAccessible(true); // Permite acesso a campos privados
            
            // Verifica se o campo da entidade tem um campo correspondente no DTO
            try {
                Field dtoField = dtoClass.getDeclaredField(entityField.getName());
                dtoField.setAccessible(true);  // Permite acesso ao campo do DTO
                
                Object value = entityField.get(entity); // Obtém o valor do campo da entidade
                
                // Se o valor for uma entidade relacionada, mapeia de forma recursiva
                if (value != null && !isPrimitiveOrWrapper(value.getClass())) {
                    // Aqui você pode chamar um método recursivo para mapear objetos relacionados
                    dtoField.set(dto, mapEntityToDto(value, dtoField.getType()));
                } else {
                    // Caso contrário, apenas mapeia o valor simples
                    dtoField.set(dto, value);
                }
            } catch (Exception e) {
                // Se o campo não existir no DTO, ignora (ou loga o erro, se necessário)
                continue;
            }
        }
        
        return dto;
    }
    
    private boolean isPrimitiveOrWrapper(Class<?> clazz) {
        return clazz.isPrimitive() || clazz == String.class || 
               clazz == Integer.class || clazz == Long.class || 
               clazz == Double.class || clazz == Boolean.class;
    }
    


}
