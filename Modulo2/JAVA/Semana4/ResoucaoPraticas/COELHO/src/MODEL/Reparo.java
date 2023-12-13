package MODEL;

import java.util.Date;

public class Reparo {
	private int id;
    private String descricao;
    private Date previsao;
    private Date dataInicio;
    private Date dataFim;
    private boolean resolvido;
    private Reparo reparoAnterior;
    private Falha falha;
	
    public Reparo(int id, String descricao, Date previsao, Date dataInicio, Date dataFim, boolean resolvido,
			Reparo reparoAnterior, Falha falha) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.previsao = previsao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.resolvido = resolvido;
		this.reparoAnterior = reparoAnterior;
		this.falha = falha;
	}
    
	public Reparo() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getPrevisao() {
		return previsao;
	}

	public void setPrevisao(Date previsao) {
		this.previsao = previsao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public boolean isResolvido() {
		return resolvido;
	}

	public void setResolvido(boolean resolvido) {
		this.resolvido = resolvido;
	}

	public Reparo getReparoAnterior() {
		return reparoAnterior;
	}

	public void setReparoAnterior(Reparo reparoAnterior) {
		this.reparoAnterior = reparoAnterior;
	}

	public Falha getFalha() {
		return falha;
	}

	public void setFalha(Falha falha) {
		this.falha = falha;
	}
	
	  
    
}
