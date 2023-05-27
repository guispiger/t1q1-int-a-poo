package intpo.t1.q1.aviao;

import java.math.BigDecimal;

public class Aviao {
	private BigDecimal capacPrincipal = new BigDecimal("833.00");
	private BigDecimal capacAuxiliar = new BigDecimal("222.00");
	private BigDecimal capacPrecioso = new BigDecimal("21.00");
	private BigDecimal minKgDecolagem = new BigDecimal("500.00");
	private Caixa[] vetorCaixas = new Caixa[1];
	private int index = 0;
	
	public Boolean autorizaDecolar() {
		Boolean decolar = false;
		BigDecimal pesoTotal = pesoCarregado();
		
		if(pesoTotal.compareTo(this.minKgDecolagem) >= 0) {
			decolar = true;
		}
		return decolar;
	}
	
	public BigDecimal pesoCarregado () {
		BigDecimal pesoCarga = new BigDecimal("0");
		for (int i = 0; i < this.vetorCaixas.length; i++) {
			if(this.vetorCaixas[i] != null) {
				BigDecimal peso = this.vetorCaixas[i].getPeso();
				pesoCarga = pesoCarga.add(peso);
			}
			
		}
		return pesoCarga;
	}
	
	
	public void armazenaCaixaCompartimento(Caixa caixa, int compartimento) {
			carregaCaixa(caixa);
			atualizaCapacidade(compartimento, caixa);
	}
	
	public int temEspaco(BigDecimal peso, String tipo) {
		int espaco = 0;
				
		if (tipo.equals("simples")) {
			if (verificaCapacidade(capacPrincipal, peso)) {
				espaco = 1;
			} else if (verificaCapacidade(capacAuxiliar, peso)) {
				espaco = 2;
			}
		} else if (tipo.equals("preciosa")) {
			if (verificaCapacidade(capacPrecioso, peso)) {
				espaco = 3;
			}
		}
		return espaco;
	}
	
	
	private boolean verificaCapacidade(BigDecimal capacidade, BigDecimal peso) {
		if(capacidade.compareTo(peso) >= 0) {
			return true;
		}else {
			return false;
		}
	}
	
	private void carregaCaixa(Caixa caixa) {
		if(vetorCaixas.length == index) {
			duplicarTamanho();
		}
		this.vetorCaixas[index] = caixa;
		index++;
	}
	
	private void duplicarTamanho(){
		Caixa[] novoVetor = new Caixa[(vetorCaixas.length*2)];
		for (int i = 0; i < vetorCaixas.length; i++) {
			novoVetor[i] = vetorCaixas[i];
		}
		this.vetorCaixas = novoVetor;
	}
	
	
	
	private void atualizaCapacidade(int compartimento, Caixa caixa) {
		if(compartimento == 1) {
			this.capacPrincipal = this.capacPrincipal.subtract(caixa.getPeso());
		}else if (compartimento == 2) {
			this.capacAuxiliar = this.capacAuxiliar.subtract(caixa.getPeso());
		}else if(compartimento == 3) {
			this.capacPrecioso = this.capacPrecioso.subtract(caixa.getPeso());
		}
	}

	
	public BigDecimal getCapacPrincipal() {
		return capacPrincipal;
	}

	public BigDecimal getCapacAuxiliar() {
		return capacAuxiliar;
	}

	public BigDecimal getCapacPrecioso() {
		return capacPrecioso;
	}

	public BigDecimal getMinKgDecolagem() {
		return minKgDecolagem;
	}
	
}
