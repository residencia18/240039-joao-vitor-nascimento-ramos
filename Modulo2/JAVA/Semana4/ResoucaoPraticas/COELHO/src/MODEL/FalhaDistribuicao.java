package MODEL;

public class FalhaDistribuicao extends Falha {
	private Falha falha;

	public FalhaDistribuicao() {
		super();
	}
	
	public FalhaDistribuicao(Falha falha) {
		super();
		this.falha = falha;
	}

	public Falha getFalha() {
		return falha;
	}

	public void setFalha(Falha falha) {
		this.falha = falha;
	}
	
	
	
}
