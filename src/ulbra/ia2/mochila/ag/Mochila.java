package ulbra.ia2.mochila.ag;

import java.util.ArrayList;
import java.util.Arrays;

public class Mochila {
	private Float pesoTotal = 0f;
	private Float valorTotal = 0f;
	private Float fitness = null;

	private ArrayList<Item> itens = new ArrayList<Item>();

	public Mochila() {
	}

	public Mochila(Float pesoTotal, Float valorTotal, Float fitness,
			ArrayList<Item> itens) {
		this.pesoTotal = pesoTotal;
		this.valorTotal = valorTotal;
		this.fitness = fitness;
		this.itens = itens;
	}

	public Float getPesoTotal() {
		return pesoTotal;
	}

	public Float getValorTotal() {
		return valorTotal;
	}

	public Float getFitness() {
		if (this.fitness == null) {
			this.calculaFitness();
		}
		return fitness;
	}

	public ArrayList<Item> getItens() {
		return itens;
	}

	public void addItem(Item item) {
		this.pesoTotal += item.getPeso();
		this.valorTotal += item.getValor();

		itens.add(item);
	}

	private void calculaFitness() {
		// TODO quantidade de itens deve entrar no calculo
		this.fitness = this.valorTotal / this.pesoTotal;
	}

	public void mutacao() {
		/*
		 * obtendo quantidade de itens que sofrerão mutação
		 */
		int qtd = (int) Math.ceil(ControleGeracoes.randomBetween(2, this.itens.size() -1));
		
		double name = Math.random();
		
		int[] keys = new int[qtd];
		int aux;
		
		/*
		 * buscando itens que sofrerão mutacao
		 */
		for (int i = 0; i < qtd; i++) {
			/*
			 * obtendo índice
			 */
			aux = (int) Math.ceil(ControleGeracoes.randomBetween(0, this.itens.size() - 1));
			
			if (Arrays.binarySearch(keys, aux) >= 0) {
				i--;
			} else {
				keys[i] = aux;
			}			
		}
		/*
		 * fazendo a mutação
		 */
		Float pesoAux = this.itens.get(keys[0]).getValor();
		for (int i=0; i < qtd -1; i++) {
			this.itens.get(i).setValor(this.itens.get(i+1).getValor());
		}
		this.itens.get(keys[qtd-1]).setValor(pesoAux);

	}

	public Mochila clone() {
		ArrayList<Item> itens = new ArrayList<Item>();

		for (Item i : this.itens) {
			itens.add(i.clone());
		}

		return new Mochila(this.pesoTotal, this.valorTotal, this.fitness, itens);
	}

}
