package ulbra.ia2.mochila.ag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ControleGeracoes {

	private Integer	populacaoTotal;
	private Float	txCrossover;
	private Float	txMutacao;
	private Integer	maxIteracoes;
	
	private Float	pesoMaxMochila;
	private Float	valMinObj;
	private Float	valMaxObj;
	private Float	pesoMinObj;
	private Float	pesoMaxObj;
	
	private ArrayList<ArrayList<Mochila>> iteracoes;
	private Float	otimoTaxa = 0.3f;

	
	public ControleGeracoes(
			Integer	populacaoTotal,
			Float 	txCrossover,
			Float 	txMutacao,
			Integer	maxIteracoes,
			Float 	pesoMaxMochila,
			Float	pesoMinObj,
			Float	pesoMaxObj,
			Float	valMinObj,
			Float	valMaxObj
	){
		this.populacaoTotal	= populacaoTotal;
		this.txCrossover	= txCrossover * (populacaoTotal / 100);
		this.txMutacao		= txMutacao * (populacaoTotal / 100);
		this.maxIteracoes	= maxIteracoes;
		this.pesoMaxMochila	= pesoMaxMochila;
		this.valMinObj = valMinObj;
		this.valMaxObj = valMaxObj;
		this.pesoMinObj	= pesoMinObj;
		this.pesoMaxObj	= pesoMaxObj;
	}
	
	public void doTheEvolution(){
		/*
		 * iniciando Iteracoes
		 */
		this.iteracoes = new ArrayList<ArrayList<Mochila>>();
		/*
		 * PRIMEIRA GERAÇAO É ALEATÓRIA
		 */
		this.iteracoes.add(this.getPopulacaoAleatoria());
		
		for(int i = 0; i < this.maxIteracoes -1; i++){
			ArrayList<Mochila> atual = this.iteracoes.get(0);
			
			/*
			 * VERIFICAR ELEMENTO ÓTIMO
			 */
			if(this.possuiElementoOtimo(atual)){
				break;
			}
			
			/*
			 * Criado nova geração
			 */
			this.iteracoes.add(getNovaGeracao(atual));
		}
		/*
		 * ordenando população atual
		 */
		this.ordenaPopulacao(this.iteracoes.get(this.iteracoes.size() - 1));
	}
	
	/**
	 * Cria populacao aleatória
	 * 
	 * @return
	 */
	private ArrayList<Mochila> getPopulacaoAleatoria()
	{
		ArrayList<Mochila> geracao = new ArrayList<Mochila>();
		
		for(int i=0; i<this.populacaoTotal; i++){
			Mochila mochila = getMochilaAleatoria(); 
			
			if (mochila.getItens().size() > 0) {
				geracao.add(mochila);
			}else{
				i--;
			}
		}	
		
		return geracao;
	}
	
	/**
	 *  Gera mochila aleatória
	 *  
	 * @return
	 */
	private Mochila getMochilaAleatoria (){
		
		Mochila mochila = new Mochila();
		
		while (mochila.getPesoTotal() < this.pesoMaxMochila) {
			
			Item nItem = new Item(
				new Float(Math.ceil(this.pesoMinObj + Math.random() * this.pesoMaxObj)),
				new Float(Math.ceil(this.valMinObj + Math.random() * this.valMaxObj))
			);
			
			if ((mochila.getPesoTotal() + nItem.getPeso()) > this.pesoMaxMochila) {
				break;
			}
			
			mochila.addItem(nItem);
		}
		
		return mochila;
	}
	
	public boolean possuiElementoOtimo(ArrayList<Mochila> gen){
		
		Integer maxItens = 0;
		Mochila otima    = new Mochila();
		Float fitness    = 0f;
		/*
		 * Descobrindo a quantidade maxima de itens em uma mochila nessa geração
		 */
		for(Mochila moc : gen){
			if(moc.getItens().size() > maxItens) {
				maxItens = moc.getItens().size(); 
			}
		}
		/*
		 * Criando mochila ótima
		 */
		for (int i=0; i<maxItens; i++) {
			otima.addItem(new Item(
				this.pesoMinObj,
				this.valMaxObj
			));
		}
		/*
		 * obtendo percentual válido
		 */
		fitness = otima.getFitness() * this.otimoTaxa;
		/*
		 * ordenando população atual
		 */
		this.ordenaPopulacao(gen);
		
		if (gen.get(0).getFitness() >= fitness) {
			return true;
		}
		return false;
	}
	
	public void ordenaPopulacao(ArrayList<Mochila> geracao){
		Collections.sort(geracao, new Comparator<Mochila>() {
			
	        public int compare(Mochila o1, Mochila o2) {
	        	
	        	Float fitness1 = ((Mochila)o1).getFitness();
	        	Float fitness2 = ((Mochila)o2).getFitness();
	        	
	        	return fitness1.compareTo(fitness2) * -1;
	        }
	    });
	}
	
	/**
	 * Cria nova geração apartir da geração pai
	 *
	 * @param geracaoPai
	 * @return geração filha
	 */
	private ArrayList<Mochila> getNovaGeracao(ArrayList<Mochila> geracao){
		
		ArrayList<Mochila> novaGeracao = new ArrayList<Mochila>();
		
		for(int i=0; i<geracao.size()-1; i++){
			novaGeracao.add(geracao.get(i).clone());
		}
		/*
		 * FAZER O CROSSOVER
		 */
		this.doCrossover(novaGeracao);
		/*
		 * FAZER MUTACAO DOS ELEMENTOS
		 */
		this.doMutacao(novaGeracao);
		
		return novaGeracao;
	}
	/**
	 * Faz o crossover na geração.
	 * @param geracao IN/OUT entra geração a ter os elementos cruzados.
	 */
	private void doCrossover(ArrayList<Mochila> geracao){
		
		/*
		 * se for um numero impar, pegar mais um para somar par
		 */
		if (this.txCrossover % 2 != 0) {
			this.txCrossover ++;
		}
		
		for(int i=0; i<this.txCrossover; i+=2){
			ArrayList<Item> itens1 = geracao.get(i).getItens(),
					        itens2 = geracao.get(i+1).getItens();

			int d1 = (int) itens1.size() / 2,
				d2 = (int) itens2.size() / 2;
			
			int p1 = (geracao.size() -1) - i,
				p2 = (geracao.size() -1) - (i + 1);
				
			/*
			 * criando primeiro item com os dois elementos
			 */
			geracao.set(p1, new Mochila());
			for (int j=0; j < d1; j++) {
				geracao.get(p1).addItem(itens1.get(j));
			}
			for (int j=d2; j < itens2.size(); j++) {
				geracao.get(p1).addItem(itens2.get(j));
			}
			
			/*
			 * criando segundo item com os dois elementos
			 */
			geracao.set(p2, new Mochila());
			for (int j=0; j < d2; j++) {
				geracao.get(p2).addItem(itens2.get(j));
			}
			for (int j=d1; j < itens1.size(); j++) {
				geracao.get(p2).addItem(itens1.get(j));
			}
			
		}
	}	
	/**
	 * 
	 * Faz a mutação dentro da geração
	 * 
	 * @param geracao
	 */
	private void doMutacao(ArrayList<Mochila> geracao){
		
		for(int i=0; i < this.txMutacao; i++){
		
			int mt = (int) Math.ceil(Math.random()*(geracao.size()-1));
			geracao.get(mt).mutacao();
		}
				
	}
	
	/**
	 * Retorna ultimo pulo para saber em qual geração parou o processamento
	 * @return
	 */
	public ArrayList<ArrayList<Mochila>> getIteracoes () {
		return this.iteracoes;
	}
	
	public Mochila getMelhorElemento () {
		return this.iteracoes.get(this.iteracoes.size() - 1).get(0);
	}
	
	public int totalIteracoes () {
		return this.iteracoes.size();
	}
	
	public static double randomBetween(double start, double end) {
		return (start + (int)(Math.random() * (end - (start - 1))));
	}
	
}
