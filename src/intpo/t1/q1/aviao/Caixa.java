package intpo.t1.q1.aviao;

import java.math.BigDecimal;

public class Caixa {
	private int codigo = 0;
	private BigDecimal peso;
	private String tipo;
	public static int x = 0;
	
	
	public Caixa(BigDecimal peso, String tipo) {
		super();
		this.peso = peso;
		this.tipo = tipo;
		x = x + 1;
		this.codigo = codigo + x;
	}


	public int getCodigo() {
		return codigo;
	}


	public BigDecimal getPeso() {
		return peso;
	}
	
	public String getTipo() {
		return tipo;
	}
	
}
