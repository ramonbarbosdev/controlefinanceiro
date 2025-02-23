package br.com.controlefinanceiro.DTO;


import br.com.controlefinanceiro.model.Item_Lancamento;

public class Item_LancamentoDTO {

	private Long id_itemlancamento;
	private Long id_lancamento;
	private Long id_tipooperacao;
	private Long id_categoria;
	private Double vl_movimento;
	private Long id_metodopagamento;
	private Long lancamento; // Este campo deve existir para a associação funcionar corretamente

    // Getter para o campo lancamento
    public Long getLancamento() {
        return lancamento;
    }

    
    public Item_LancamentoDTO() {} // Construtor necessário para serialização

	public Item_LancamentoDTO(Item_Lancamento objeto)
	{
		this.id_itemlancamento = objeto.getId_itemlancamento();
		this.id_lancamento = objeto.getLancamento().getId_lancamento();
		this.id_tipooperacao = objeto.getTipoOperacao().getId_tipooperacao();
		this.id_categoria = objeto.getCategoria().getId_categoria();
		this.vl_movimento = objeto.getVl_movimento();
		this.id_metodopagamento = objeto.getMetodoPagamento().getId_metodopagamento();
	}

	public Long getId_itemlancamento() {
		return id_itemlancamento;
	}
	
	public void setId_itemlancamento(Long id_itemlancamento) {
		this.id_itemlancamento = id_itemlancamento;
	}

	public Long getId_lancamento() {
		return id_lancamento;
	}

	public void setId_lancamento(Long id_lancamento) {
		this.id_lancamento = id_lancamento;
	}

	public Long getId_tipooperacao() {
		return id_tipooperacao;
	}

	public void setId_tipooperacao(Long id_tipooperacao) {
		this.id_tipooperacao = id_tipooperacao;
	}

	public Long getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Long id_categoria) {
		this.id_categoria = id_categoria;
	}

	public Double getVl_movimento() {
		return vl_movimento;
	}

	public void setVl_movimento(Double vl_movimento) {
		this.vl_movimento = vl_movimento;
	}

	public Long getId_metodopagamento() {
		return id_metodopagamento;
	}

	public void setId_metodopagamento(Long id_metodopagamento) {
		this.id_metodopagamento = id_metodopagamento;
	}

	    
}
