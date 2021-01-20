package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Casilla {

	// atributos
	private Ficha ficha;

	// metodos
	public boolean estaOcupada() {

		boolean casillaOcupada = false;

		if (this.ficha == Ficha.AZUL || this.ficha == Ficha.VERDE) {
			casillaOcupada = true;
		}
		return casillaOcupada;
	}

	// setters y getters
	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) throws OperationNotSupportedException {
		if (estaOcupada() == false) {
			if (ficha == null) {
				throw new NullPointerException("ERROR: No se puede poner una ficha nula.");
			} else if (ficha == Ficha.AZUL) {
				this.ficha = ficha.AZUL;
			} else if (ficha == Ficha.VERDE) {
				this.ficha = ficha.VERDE;
			} else {
				throw new IllegalArgumentException("ERROR:el color escogido es incorrecto.");
			}
		} else {
			throw new OperationNotSupportedException("ERROR: Ya contengo una ficha.");
		}
	}

	// constructor por defecto
	public Casilla() throws IllegalArgumentException {
		ficha = null;
	}

	@Override
	public String toString() {
		if (ficha == null)
			return " ";
		else
			return String.format("%.1s", ficha);

	}

}
