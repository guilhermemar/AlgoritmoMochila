package ulbra.ia2.mochila.ag;


public class Item
{
	
	private Float valor;
	private Float peso;
	
	public Item(Float peso, Float valor){
		this.peso = peso;
		this.valor = valor;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}
	
	public Item clone ()
	{
		return new Item(
			this.peso,
			this.valor
		);
	}

}
