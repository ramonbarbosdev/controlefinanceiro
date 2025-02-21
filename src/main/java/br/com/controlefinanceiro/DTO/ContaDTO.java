package br.com.controlefinanceiro.DTO;

public class ContaDTO {

	 private Long id_conta;
    private String cd_conta;
    private String nm_conta;
    private Long id_tipoconta;
    
    
	public Long getId_conta() {
		return id_conta;
	}
	public void setId_conta(Long id_conta) {
		this.id_conta = id_conta;
	}
	public String getCd_conta() {
		return cd_conta;
	}
	public void setCd_conta(String cd_conta) {
		this.cd_conta = cd_conta;
	}
	public String getNm_conta() {
		return nm_conta;
	}
	public void setNm_conta(String nm_conta) {
		this.nm_conta = nm_conta;
	}
	public Long getId_tipoconta() {
		return id_tipoconta;
	}
	public void setId_tipoconta(Long id_tipoconta) {
		this.id_tipoconta = id_tipoconta;
	}
    
    
}
