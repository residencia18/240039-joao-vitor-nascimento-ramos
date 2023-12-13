package MODEL;

public class FalhaGeracao extends Falha {
	
	private Falha falha;
	
	public FalhaGeracao(Falha falha) {
		super();
		this.falha = falha;
	}

	public FalhaGeracao() {
		super();
	}
	
	public Falha getFalha() {
		return falha;
	}
	
	public void setFalha(Falha falha) {
		this.falha = falha;
	}
	
}
