package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Casilla {

	// atributos
	private Ficha ficha;

	// metodos
	public boolean estaOcupada() {

		if (getFicha() != null) {
			return true;
		} else {
			return false;
		}

	}

	// setters y getters
	public Ficha getFicha() {
		return this.ficha;
	}

	public void setFicha(Ficha ficha) throws OperationNotSupportedException {
		if (ficha == null) {
			throw new NullPointerException("ERROR: No se puede poner una ficha nula.");
		}
		
		if(this.ficha != null) {
			throw new OperationNotSupportedException("ERROR: Ya contengo una ficha.");
		} else {
			this.ficha = ficha;
		}
	
	}

	// constructor por defecto
	public Casilla() throws IllegalArgumentException {
		this.ficha = null; //es recomendable siempre poner this cuando me refiero al atributo de clase
	}

	@Override
	public String toString() {
		if (this.ficha == null)
			return " ";
		else
			return String.format("%.1s", this.ficha);

	}

}
