package br.com.controlefinanceiro.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.controlefinanceiro.MensagemException;
import br.com.controlefinanceiro.config.RelacionamentoConfig;
import br.com.controlefinanceiro.service.Utils;
import br.com.controlefinanceiro.service.ValidacaoService;

public abstract  class BaseController<T,D,ID> {
    
    protected CrudRepository<T, ID> repository;

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    private Utils utils;

    @Autowired
    private ValidacaoService validacaoService;

    private final Class<T> entidadeClass;
    private final Class<D> entidadeDtoClass;
    private final String idEntidade;
    
    private Map<String, RelacionamentoConfig> relacionamentos;

    public BaseController(CrudRepository<T, ID> repository, Class<T> entidadeClass, Class<D> entidadeDtoClass, String idEntidade, Map<String, RelacionamentoConfig> relacionamentos) {
        this.repository = repository;
        this.entidadeClass = entidadeClass;
        this.entidadeDtoClass = entidadeDtoClass;
        this.idEntidade = idEntidade;
        this.relacionamentos = relacionamentos;
    }

    public void setRelacionamentos(Map<String, RelacionamentoConfig> relacionamentos)
    {
        this.relacionamentos = relacionamentos;
    }

    public Map<String, RelacionamentoConfig> getRelacionamentos()
    {
        return relacionamentos;
    }
   
    

     // ✅ Buscar todos os registros
     @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<?>> obterTodos() throws Exception
    {
        try
        {
            List<T> entidades = (List<T>) repository.findAll();

            if (entidades.isEmpty())
            {
                throw new MensagemException("Nenhum reistro encontrada!");
            }
     
     
            List<Object> listDTO = entidades.stream()
										.map(entidade -> utils.converterDTO(entidade, entidadeDtoClass))
										.collect(Collectors.toList());

			return new ResponseEntity<>(listDTO, HttpStatus.OK);

        } catch (Exception e)
        {
            throw new MensagemException( e.getMessage());
        }
       
    }

     // ✅ Buscar por ID
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> obterPorId(@PathVariable ID id) {
        try
		{
            Optional<T> entidade = repository.findById(id);

            if (entidade.isEmpty())
            {
                throw new MensagemException("Nenhuma conta encontrada!");
            }
     
    
        	Object dto = utils.converterDTO(entidade.get(), entidadeDtoClass);

			return new ResponseEntity<Object>( dto, HttpStatus.OK);
		}
		catch (Exception e)
		{
				throw new MensagemException( e.getMessage());
		}
    }

     // ✅ Criar novo registro
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> cadastrar(@RequestBody D dto) throws Exception
    {
        try
        {

            D dtoInstancia = entidadeDtoClass.getDeclaredConstructor().newInstance();
            T entidadeInstancia = entidadeClass.getDeclaredConstructor().newInstance();

            modelMapper.map(dto, dtoInstancia);
            modelMapper.map(dtoInstancia, entidadeInstancia);
            
            entidadeInstancia = aplicarRelacionamentos(entidadeInstancia, dtoInstancia);

            validacaoService.validarCadastroGeral(entidadeInstancia, this.idEntidade);

            CrudRepository genericoRepository = utils.obterRepositoryEntidade(entidadeInstancia);
    
            Object salvo = genericoRepository.save(entidadeInstancia);

            return new ResponseEntity<>(salvo, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            throw new MensagemException( e.getMessage());
        }
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> atualizar(@RequestBody D dto) throws Exception
    {
        try
        {

            D dtoInstancia = entidadeDtoClass.getDeclaredConstructor().newInstance();
            T entidadeInstancia = entidadeClass.getDeclaredConstructor().newInstance();

            modelMapper.map(dto, dtoInstancia);
            modelMapper.map(dtoInstancia, entidadeInstancia);
            
            entidadeInstancia = aplicarRelacionamentos(entidadeInstancia, dtoInstancia);


            CrudRepository genericoRepository = utils.obterRepositoryEntidade(entidadeInstancia);
    
            Object salvo = genericoRepository.save(entidadeInstancia);

            return new ResponseEntity<>(salvo, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            throw new MensagemException( e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id_conta}", produces = "application/text" )
	public String delete (@PathVariable("id_conta") Long id) throws Exception
	{
		try 
		{
			Object objetoModel = this.entidadeClass.getDeclaredConstructor().newInstance();
			
			CrudRepository repository = utils.obterRepositoryEntidade(objetoModel);

			repository.deleteById(id);
			
			return "Registro deletado!";

		} 
		catch (Exception e)
		{	
			throw new MensagemException( e.getMessage());
		}
	}

    private T aplicarRelacionamentos(T entidadeInstancia, D dtoInstancia) throws Exception {

        if (entidadeInstancia == null || dtoInstancia == null || relacionamentos == null)
        {
            throw new IllegalArgumentException("Entidade, DTO ou relacionamentos não podem ser nulos.");
        }
    
        for (Map.Entry<String, RelacionamentoConfig> entry : relacionamentos.entrySet())
        {
            String nomeCampo = entry.getKey();
            RelacionamentoConfig config = entry.getValue();
    
            if (config == null)
            {
                throw new IllegalStateException("Configuração de relacionamento não pode ser nula para o campo: " + nomeCampo);
            }
    
            utils.obterObjetoRelacionamento(
                    entidadeInstancia,                  // objeto onde o método será invocado
                    dtoInstancia,                       // DTO que contém os dados
                    nomeCampo,                          // nome do campo a ser utilizado
                    config.getRepository(),             // repositório para buscar a entidade
                    config.getSetter(),                 // nome do método setter
                    config.getEntidadeRelacionada()     // tipo do parâmetro que o setter espera
                );
          

        }
        return entidadeInstancia;
    }



}
