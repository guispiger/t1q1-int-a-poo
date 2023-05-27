package intpo.t1.q1.aviao;

import java.math.BigDecimal;

import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {
			Boolean continuar = true;
			Aviao cargueiro = new Aviao();
			
			
			do {
				int operacao = JOptionPane.showConfirmDialog(null,"Deseja carregar uma caixa?");
				if(operacao == 0) {
					
					BigDecimal peso = new BigDecimal(JOptionPane.showInputDialog("Qual o peso da caixa?"));
					String tipo = JOptionPane.showInputDialog("Qual o tipo da carga? (simples|preciosa)");
					
					int compartimento = cargueiro.temEspaco(peso, tipo);
					
					if (compartimento > 0) {
						Caixa caixa = new Caixa(peso, tipo);
						cargueiro.armazenaCaixaCompartimento(caixa, compartimento);
						String resposta = montarResposta(caixa, compartimento, cargueiro);
						JOptionPane.showMessageDialog(null, resposta);
					} else {
						JOptionPane.showMessageDialog(null, "Espaço insuficiente");
					}
	
					
				} else if(operacao == 1 || operacao == 2) {
					continuar = false;
				}
				
			} while (continuar);
			
			
			Boolean autorizacao = cargueiro.autorizaDecolar();
			String mensagem = mensagemFinal(autorizacao, cargueiro);
			JOptionPane.showMessageDialog(null, mensagem);
					
	}
	
	
	private static String montarResposta(Caixa caixa, int compartimento, Aviao cargueiro) {
		int codigo = caixa.getCodigo();
		BigDecimal peso = caixa.getPeso();
		String tipo = caixa.getTipo();
		
		String local = "";
		BigDecimal capacidade = new BigDecimal("0");
		if ( compartimento == 1) {
			local = "Pricipal";
			capacidade = cargueiro.getCapacPrincipal();
		} else if ( compartimento == 2) {
			local = "Auxiliar";
			capacidade = cargueiro.getCapacAuxiliar();
		} else if ( compartimento == 3) {
			local = "Precioso";
			capacidade = cargueiro.getCapacPrecioso();
		}
		
		String resposta = "Caixa Armazenada!!\n" +
				"Compartimento: " + local + ":\n" +
				"Código da caixa: " + codigo + ".\n" +
				"Peso da caixa: " + peso + "kg.\n" +
				"Tipo da caixa: " + tipo + ".\n" + 
				"Espaço restante: " + capacidade;
		return resposta;
	}
	
	public static String mensagemFinal(Boolean autorizacao, Aviao cargueiro) {
		String mensagem = "Não pode decolar, carga insuficiente!! \n" +
							"Peso minimo decolagem: " + cargueiro.getMinKgDecolagem() + "kg.\n" +
							"Peso carregado: " + cargueiro.pesoCarregado() + "kg.\n";
		if(autorizacao) {
			mensagem = "Decoalgem autorizada!! \n" +
					"Peso minimo decolagem: " + cargueiro.getMinKgDecolagem() + "kg.\n" +
					"Peso carregado: " + cargueiro.pesoCarregado() + "kg.";
		}
		return mensagem;
	}
	
}
