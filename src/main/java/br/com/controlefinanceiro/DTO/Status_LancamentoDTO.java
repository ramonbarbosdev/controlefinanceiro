package br.com.controlefinanceiro.DTO;

import br.com.controlefinanceiro.model.Status_Lancamento;

public class Status_LancamentoDTO {

	 private Long id_statuslancamento;
    private String ds_statuslancamento;
    
    public Status_LancamentoDTO() {} // Construtor necessário para serialização

	public Status_LancamentoDTO(Status_Lancamento objeto) {
		this.id_statuslancamento = objeto.getId_status_lancamento();
		this.ds_statuslancamento = objeto.getDs_statuslancamento();
	}

	public Long getId_statuslancamento() {
		return id_statuslancamento;
	}	

	public void setId_statuslancamento(Long id_statuslancamento) {
		this.id_statuslancamento = id_statuslancamento;
	}	

	public String getDs_statuslancamento() {
		return ds_statuslancamento;
	}	

	public void setDs_statuslancamento(String ds_statuslancamento) {
		this.ds_statuslancamento = ds_statuslancamento;
	}


    
}
