package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Casilla {
	
	//atributos
	private Ficha ficha;

	//metodos
	public boolean estaOcupada() {
		if (ficha==null)
			return false;
		else
			return true;		
	}
	
	//setters y getters
	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) {
		if (ficha==null)
			throw new NullPointerException("ERROR");
		else
			this.ficha = ficha;
	}
	
	//constructor por defecto
	public Casilla() throws IllegalArgumentException
	{
		ficha=null;
	}

	@Override
	public String toString() {
		if (ficha==null) 
			return " ";
	else 
			return String.format("%.1s", ficha);
			
	}
	
	
	
}
