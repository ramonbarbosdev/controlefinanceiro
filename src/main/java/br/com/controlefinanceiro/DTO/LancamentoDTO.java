package br.com.controlefinanceiro.DTO;

import java.time.LocalDate;

import br.com.controlefinanceiro.model.Lancamento;

public class LancamentoDTO {

	 private Long id_lancamento;
    private Long id_conta;
    private LocalDate  dt_lancamento;
    private String ds_lancamento;
	private Double vl_lancamento;
	private Long id_statuslancamento;

    
    public LancamentoDTO() {} // Construtor necessário para serialização

	public LancamentoDTO(Lancamento objeto) {
		this.id_lancamento = objeto.getId_lancamento();
		this.id_conta = objeto.getConta().getId_conta();
		this.dt_lancamento = objeto.getDt_lancamento();
		this.ds_lancamento = objeto.getDs_lancamento();
		this.vl_lancamento = objeto.getVl_lancamento();
		this.id_statuslancamento = objeto.getStatusLancamento().getId_status_lancamento();
	}

	public Long getId_lancamento() {
		return id_lancamento;
	}

	public void setId_lancamento(Long id_lancamento) {
		this.id_lancamento = id_lancamento;
	}

	public Long getId_conta() {
		return id_conta;
	}

	public void setId_conta(Long id_conta) {
		this.id_conta = id_conta;
	}

	public LocalDate getDt_lancamento() {
		return dt_lancamento;
	}

	public void setDt_lancamento(LocalDate dt_lancamento) {
		this.dt_lancamento = dt_lancamento;
	}

	public String getDs_lancamento() {
		return ds_lancamento;
	}

	public void setDs_lancamento(String ds_lancamento) {
		this.ds_lancamento = ds_lancamento;
	}

	public Double getVl_lancamento() {
		return vl_lancamento;
	}

	public void setVl_lancamento(Double vl_lancamento) {
		this.vl_lancamento = vl_lancamento;
	}

	public Long getId_statuslancamento() {
		return id_statuslancamento;
	}	

	public void setId_statuslancamento(Long id_statuslancamento) {
		this.id_statuslancamento = id_statuslancamento;
	}	




    
}
