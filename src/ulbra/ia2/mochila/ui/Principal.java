package ulbra.ia2.mochila.ui;

import java.util.ArrayList;
import java.util.Iterator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Control;
import ulbra.ia2.mochila.ag.ControleGeracoes;
import ulbra.ia2.mochila.ag.Item;

public class Principal {
	private static Display display = Display.getDefault();
	private static Shell   shell = new Shell();
	
	private static Text text_peso_suportado;
	private static Text text_populacao;
	private static Text text_tx_crossover;
	private static Text text_tx_mutacao;
	private static Text text_iteracoes;
	private static Text text_valor_minimo;
	private static Text text_valor_maximo;
	private static Text text_peso_minimo;
	private static Text text_peso_maximo;
	
	private static Button btnIniciar;
	
	private static Label lblTotalIteracoes;
	private static Label lblResultado;
	
	private static Composite composite_resultado;
	private static ScrolledComposite scrolledComposite;
	private static Composite composite;
	
	/**
	 * Faz a parte feia de montar a interface
	 * @param shell
	 */
	private static void makeShell()
	{
		shell.setSize(499, 595);
		shell.setText("Algoritmo da mochila");
		
		Label lblAlgoritmoD = new Label(shell, SWT.NONE);
		lblAlgoritmoD.setFont(SWTResourceManager.getFont("Sans Serif", 16, SWT.NORMAL));
		lblAlgoritmoD.setBounds(116, 10, 225, 25);
		lblAlgoritmoD.setText("Algoritmo da mochila");
		
		Label lblAlgoritmo = new Label(shell, SWT.NONE);
		lblAlgoritmo.setText("Algoritmo");
		lblAlgoritmo.setFont(SWTResourceManager.getFont("Sans Serif", 14, SWT.NORMAL));
		lblAlgoritmo.setBounds(73, 53, 89, 22);
		
		Label lblMochila = new Label(shell, SWT.NONE);
		lblMochila.setText("Mochila");
		lblMochila.setFont(SWTResourceManager.getFont("Sans Serif", 14, SWT.NORMAL));
		lblMochila.setBounds(320, 53, 69, 22);
		
		Label lblItens = new Label(shell, SWT.NONE);
		lblItens.setText("Itens");
		lblItens.setFont(SWTResourceManager.getFont("Sans Serif", 14, SWT.NORMAL));
		lblItens.setBounds(320, 130, 46, 22);
		
		text_peso_suportado = new Text(shell, SWT.BORDER);
		text_peso_suportado.setToolTipText("Peso supotado pela mochila");
		text_peso_suportado.setBounds(383, 99, 73, 23);
		
		Label lblPopulao = new Label(shell, SWT.NONE);
		lblPopulao.setBounds(43, 99, 65, 15);
		lblPopulao.setText("População");
		
		Label lblTxCrossover = new Label(shell, SWT.NONE);
		lblTxCrossover.setText("Tx. Crossover");
		lblTxCrossover.setBounds(22, 137, 86, 15);
		
		Label lblTxMutao = new Label(shell, SWT.NONE);
		lblTxMutao.setText("Tx. Mutação");
		lblTxMutao.setBounds(31, 174, 77, 15);
		
		Label lblIteraes = new Label(shell, SWT.NONE);
		lblIteraes.setText("Iterações");
		lblIteraes.setBounds(49, 206, 59, 15);
		
		Label lblPesoSuportado = new Label(shell, SWT.NONE);
		lblPesoSuportado.setText("Peso Suportado");
		lblPesoSuportado.setBounds(269, 100, 102, 15);
		
		Label lblValorMnimo = new Label(shell, SWT.NONE);
		lblValorMnimo.setText("Valor Mínimo*");
		lblValorMnimo.setBounds(289, 174, 89, 15);
		
		Label lblValorMximo = new Label(shell, SWT.NONE);
		lblValorMximo.setText("Valor Máximo*");
		lblValorMximo.setBounds(285, 206, 93, 15);
		
		Label lblPesoMnimo = new Label(shell, SWT.NONE);
		lblPesoMnimo.setText("Peso Mínimo*");
		lblPesoMnimo.setBounds(290, 240, 88, 15);
		
		Label lblPesoMximo = new Label(shell, SWT.NONE);
		lblPesoMximo.setText("Peso Máximo*");
		lblPesoMximo.setBounds(289, 272, 92, 15);
		
		text_populacao = new Text(shell, SWT.BORDER);
		text_populacao.setToolTipText("Quantos indivíduos serão gerados para a procura do melhor");
		text_populacao.setBounds(125, 100, 73, 23);
		
		text_tx_crossover = new Text(shell, SWT.BORDER);
		text_tx_crossover.setBounds(125, 137, 73, 23);
		
		text_tx_mutacao = new Text(shell, SWT.BORDER);
		text_tx_mutacao.setBounds(125, 173, 73, 23);
		
		text_iteracoes = new Text(shell, SWT.BORDER);
		text_iteracoes.setToolTipText("Quantas iterações máximas serão feitas");
		text_iteracoes.setBounds(125, 206, 73, 23);
		
		text_valor_minimo = new Text(shell, SWT.BORDER);
		text_valor_minimo.setToolTipText("Valor mínimo que um item da mochila pode possuír");
		text_valor_minimo.setBounds(383, 174, 73, 23);
		
		text_valor_maximo = new Text(shell, SWT.BORDER);
		text_valor_maximo.setToolTipText("Valor máximo que um item da mochila pode possuír");
		text_valor_maximo.setBounds(383, 206, 73, 23);
		
		text_peso_minimo = new Text(shell, SWT.BORDER);
		text_peso_minimo.setToolTipText("Peso mínimo que um item da mochila pode possuír");
		text_peso_minimo.setBounds(383, 240, 73, 23);
		
		text_peso_maximo = new Text(shell, SWT.BORDER);
		text_peso_maximo.setToolTipText("Peso máximo que um item da mochila pode possuír");
		text_peso_maximo.setBounds(383, 272, 73, 23);
		
		btnIniciar = new Button(shell, SWT.NONE);
		btnIniciar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
								
				runAlgoritm();
				
			}
		});
	
		
		btnIniciar.setBounds(81, 272, 88, 25);
		btnIniciar.setText("Iniciar");
		
		
		lblResultado = new Label(shell, SWT.NONE);
		lblResultado.setText("Não calculado");
		lblResultado.setFont(SWTResourceManager.getFont("Sans Serif", 14, SWT.NORMAL));
		lblResultado.setBounds(61, 340, 359, 22);
		
		composite_resultado = new Composite(shell, SWT.NONE);
		composite_resultado.setBounds(10, 368, 473, 184);
		composite_resultado.setVisible(false);
		
		Label lblAMelhor = new Label(composite_resultado, SWT.NONE);
		lblAMelhor.setBounds(24, 42, 188, 15);
		lblAMelhor.setText("A melhor solução encontrada");
		
		Label lblFoiUmIndivduo = new Label(composite_resultado, SWT.NONE);
		lblFoiUmIndivduo.setText("foi um indivíduo com os");
		lblFoiUmIndivduo.setBounds(24, 64, 151, 15);
		
		Label lblComOsSeguintes = new Label(composite_resultado, SWT.NONE);
		lblComOsSeguintes.setBounds(24, 85, 101, 15);
		lblComOsSeguintes.setText("seguintes itens:");
		
		scrolledComposite = new ScrolledComposite(composite_resultado, SWT.V_SCROLL);
		scrolledComposite.setBounds(243, 27, 220, 100);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Label lblNewLabel_2 = new Label(composite_resultado, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Sans Serif", 10, SWT.BOLD));
		lblNewLabel_2.setBounds(244, 10, 65, 15);
		lblNewLabel_2.setText("Peso");
		
		Label lblValor = new Label(composite_resultado, SWT.NONE);
		lblValor.setFont(SWTResourceManager.getFont("Sans Serif", 10, SWT.BOLD));
		lblValor.setBounds(364, 10, 37, 15);
		lblValor.setText("Valor");
		
		lblTotalIteracoes = new Label(composite_resultado, SWT.NONE);
		lblTotalIteracoes.setBounds(24, 156, 427, 15);
		
		/*
		 * Definindo valores defaults
		 */
		text_populacao.setText("100");
		text_tx_crossover.setText("75");
		text_tx_mutacao.setText("2");
		text_iteracoes.setText("500");
		text_peso_suportado.setText("50");
		text_peso_minimo.setText("1");
		text_peso_maximo.setText("50");
		text_valor_maximo.setText("20");
		text_valor_minimo.setText("1");
		
		Label lblParaPopulaoInicial = new Label(shell, SWT.NONE);
		lblParaPopulaoInicial.setBounds(308, 301, 148, 15);
		lblParaPopulaoInicial.setText("* Para população inicial");
		
		shell.setTabList(new Control[]{text_populacao, text_tx_crossover, text_tx_mutacao, text_iteracoes, text_peso_suportado, text_valor_minimo, text_valor_maximo, text_peso_minimo, text_peso_maximo, btnIniciar});
	}
	
	private static int getPopulacao() throws Exception
	{
		try {
			
			if (text_populacao.getText().trim().equals("")) {
				throw new Exception("População é obrigatório");
			}
			
			int populacao = Integer.valueOf(text_populacao.getText().trim());
			/*
			 * Deve ser informado uma população de no mínimo 10
			 */
			if (populacao < 10) {
				throw new Exception("População deve ter valor mínimo de 10");
			}
			
			return populacao;
			
		} catch (NullPointerException e) {
			
			throw new Exception("População é obrigatório");
			
		} catch (NumberFormatException e) {
			
			throw new Exception("População deve receber somente números inteiros");
		}
	}
	
	private static Float getTxCrossover() throws Exception
	{
		try {
			
			if (text_tx_crossover.getText().trim().equals("")) {
				throw new Exception("Tx. crossover é obrigatório");
			}
			
			/*
			 * pegando a população para calcular o número máximo possível da taxa de crossover
			 */
			int populacao = getPopulacao();
			Float crossover;
			
			/*
			 * calculando máximo possível
			 */
			populacao = 75 * populacao / 100;
			crossover = Float.valueOf(text_tx_crossover.getText().trim());
			
			if (crossover > populacao) {
				throw new Exception("Tx. crossover não pode ser maior que 75% da população");
			}
			
			return crossover;
			
			
		} catch (NullPointerException e) {
			
			throw new Exception("Tx. crossover é obrigatório");
			
		} catch (NumberFormatException e) {
			
			throw new Exception("Tx. crossover deve receber somente números inteiros");
		}
	}
	
	private static Float getTxMutacao() throws Exception
	{
		try {
			
			if (text_tx_mutacao.getText().trim().equals("")) {
				throw new Exception("Tx. mutação é obrigatório");
			}
			
			return Float.valueOf(text_tx_mutacao.getText());
			
		} catch (NullPointerException e) {
			
			throw new Exception("Tx. mutação é obrigatório");
			
		} catch (NumberFormatException e) {
			
			throw new Exception("Tx. mutação deve receber somente números inteiros");
		}
	}
	
	private static int getIteracoes() throws Exception
	{
		try {
			
			if (text_iteracoes.getText().trim().equals("")) {
				throw new Exception("Iterações é obrigatório");
			}
			
			int iteracoes = Integer.valueOf(text_iteracoes.getText().trim());
			/*
			 * Deve ser informado um numero mínimo de 10 iterações
			 */
			if (iteracoes < 10) {
				throw new Exception("Iterações não deve ser um número menor que 10");
			}
			
			return iteracoes;
			
		} catch (NullPointerException e) {
			
			throw new Exception("Iterações é obrigatório");
			
		} catch (NumberFormatException e) {
			
			throw new Exception("Iterações deve receber somente números inteiros");
		}
	}
	
	private static float getPesoSuportado() throws Exception
	{
		try {
			
			if (text_peso_suportado.getText().trim().equals("")) {
				throw new Exception("Peso suportado é obrigatório");
			}
			
			float suportado = Float.valueOf(text_peso_suportado.getText().trim());
			/*
			 * Peso máximo suportado pela mochila não pode ser menor que 10
			 */
			if (suportado < 10) {
				throw new Exception("Peso suportado pela mochila não pode ser menor que 10");
			}
			
			return suportado;
			
		} catch (NullPointerException e) {
			
			throw new Exception("Peso suportado é obrigatório");
			
		} catch (NumberFormatException e) {
			
			throw new Exception("Peso suportado deve receber somente números inteiros");
		}
	}
	
	private static float getValorMinimo() throws Exception
	{
		try {
			
			if (text_valor_minimo.getText().trim().equals("")) {
				throw new Exception("Valor mínimo é obrigatório");
			}
			
			float valor = Float.valueOf(text_valor_minimo.getText().trim());
			/*
			 * Valor mínimo não pode ser menor que 1
			 */
			if (valor < 1) {
				throw new Exception("Valor mínimo não pode ser menor que 1");
			}
			
			return valor;
			
		} catch (NullPointerException e) {
			
			throw new Exception("Valor mínimo é obrigatório");
			
		} catch (NumberFormatException e) {
			
			throw new Exception("Valor mínimo deve receber somente números inteiros");
		}
	}
	
	private static float getValorMaximo() throws Exception
	{
		try {
			
			if (text_valor_maximo.getText().trim().equals("")) {
				throw new Exception("Valor máximo é obrigatório");
			}
			
			return Float.valueOf(text_valor_maximo.getText());
			
		} catch (NullPointerException e) {
			
			throw new Exception("Valor máximo é obrigatório");
			
		} catch (NumberFormatException e) {
			
			throw new Exception("Valor máximo deve receber somente números inteiros");
		}
	}
	
	private static float getPesoMinimo() throws Exception
	{
		try {
			
			if (text_peso_minimo.getText().trim().equals("")) {
				throw new Exception("Peso mínimo é obrigatório");
			}
			
			float peso = Float.valueOf(text_peso_minimo.getText().trim());
			/*
			 * Peso mínimo não pode ser menor que 1
			 */
			if (peso < 1) {
				throw new Exception("Peso mínimo não pode ser menor que 1");
			}
			
			return peso;
			
		} catch (NullPointerException e) {
			
			throw new Exception("Peso mínimo é obrigatório");
			
		} catch (NumberFormatException e) {
			
			throw new Exception("Peso mínimo deve receber somente números inteiros");
		}
	}
	
	private static float getPesoMaximo() throws Exception
	{
		try {
			
			if (text_peso_maximo.getText().trim().equals("")) {
				throw new Exception("Peso máximo é obrigatório");
			}
			
			/*
			 * pegando capacidade da mochila
			 */
			float mochila = getPesoSuportado();
			float peso = Float.valueOf(text_peso_maximo.getText().trim());
			/*
			 * Verificar e o peso máximo do item não é maior que a mochila suporta
			 */
			if (peso > mochila) {
				throw new Exception("Peso máximo não pode ser maior que a capacidade da mochila");
			}
			
			return peso;
			
		} catch (NullPointerException e) {
			
			throw new Exception("Peso máximo é obrigatório");
			
		} catch (NumberFormatException e) {
			
			throw new Exception("Peso máximo deve receber somente números inteiros");
		}
	}
	
	/**
	 * Formata para exibir uma mensagem de erro amigável
	 * @param message
	 */
	private static void showError (String message) 
	{
		String finalMessage = "AVISO\n\n";
		
		if (message == null){
			message = "Erro desconhecido";
		}
		
		finalMessage += message;
		
		lblResultado.setText("Erros encontrados");
		
		MessageBox aviso = new MessageBox(shell);
		aviso.setMessage(finalMessage);
		aviso.open();
	}
	
	/**
	 * Executa o algoritmo da mochila
	 */
	private static void runAlgoritm ()
	{
		ControleGeracoes conGe = null;
		
		lblResultado.setText("Calculando");
		composite_resultado.setVisible(false);
		
		try {
			
			conGe = new ControleGeracoes(
				getPopulacao(),
				getTxCrossover(),
				getTxMutacao(),
				getIteracoes(),
				getPesoSuportado(),
				getValorMinimo(),
				getValorMaximo(),
				getPesoMinimo(),
				getPesoMaximo()
			);
			
			conGe.doTheEvolution();	
			
			showAlgoritmResult(
				conGe.getMelhorElemento().getItens(),
				conGe.totalIteracoes()
			);
			
		}catch(Exception e){
			/*
			 * corrigindo mensagem
			 */
			showError(e.getMessage());
		}
		
		conGe = null;
		
	}
	
	
	
	/**
	 * Joga na interface o conteúdo recebido
	 */
	private static void showAlgoritmResult (
		ArrayList <Item> itens,
		int iteracoes
	){
		Item item;
		int pos_y = 0;
		Iterator<Item> i = itens.iterator();
		
		composite = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	
		while (i.hasNext()) {
			item = i.next();
			
			Label lblNewLabel = new Label(composite, SWT.NONE);
			lblNewLabel.setBounds(0, pos_y, 65, 15);
			lblNewLabel.setText(Double.toString(item.getPeso()) + " Kg");
			
			Label lblNewLabel_1 = new Label(composite, SWT.NONE);
			lblNewLabel_1.setBounds(116, pos_y, 65, 15);
			lblNewLabel_1.setText("R$ " + Double.toString(item.getValor()));
			
			pos_y += 20;
		}
		
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		lblTotalIteracoes.setText("Foi necessário um total de " + Integer.toString(iteracoes) + " iterações");
		
		lblResultado.setText("Resultado");
		composite_resultado.setVisible(true);
		
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main (String[] args)
	{		
		makeShell();
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
