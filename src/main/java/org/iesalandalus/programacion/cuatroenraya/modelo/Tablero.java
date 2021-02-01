package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Tablero {

	// atributos
	public static final int FILAS = 6;
	public static final int COLUMNAS = 7;
	public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;
	private Casilla[][] casillas;

	// constructor por defecto
	public Tablero() {
		casillas = new Casilla[FILAS][COLUMNAS];
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				casillas[i][j] = new Casilla();
			}
		}
	}

	// metodos

	// metodo columnaVacia
	private boolean columnaVacia(int columna) {
		return !casillas[0][columna].estaOcupada();
	}

	/*
	 * private boolean columnaVacia(int columna) { boolean columnaVacia = true; for
	 * (int i = 0; i < FILAS; i++) { if (casillas[i][columna].estaOcupada())
	 * columnaVacia = false; } return columnaVacia;
	 * 
	 * }
	 */

	// metodo estaVacio

	public boolean estaVacio() {
		boolean tableroVacio = true;
		for (int i = 0; i < COLUMNAS; i++) {
			if (columnaVacia(i) == true) {
				tableroVacio = true;
			} else {
				tableroVacio = false;
			}
		}
		return tableroVacio;
	}

	// metodo columnaLlena

	private boolean columnaLlena(int columna) {
		return casillas[FILAS - 1][columna].estaOcupada();
	}

	/*
	 * private boolean columnaLlena(int columna) { boolean columnaLlena = false; for
	 * (int i = 0; i < FILAS; i++) { if (casillas[i][columna].estaOcupada())
	 * columnaLlena = true; } return columnaLlena;
	 * 
	 * }
	 */

	// metodo estaLleno

	public boolean estaLleno() {
		boolean lleno = true;
		for (int i = 0; i < COLUMNAS && lleno; i++) {
			lleno &= columnaLlena(i);
		}
		return lleno;
	}

	// metodo comprobarFicha

	public void comprobarFicha(Ficha ficha) {

		if (ficha == null) {
			throw new NullPointerException("ERROR: La ficha no puede ser nula.");
		}

	}

	// metodo comprobarColumna

	public void comprobarColumna(int columna) {

		if (columna > COLUMNAS - 1 || columna < 0) {
			throw new NullPointerException("ERROR: Columna incorrecta.");
		}

	}

	// metodo getPrimeraFilaVacia

	private int getPrimeraFilaVacia(int columna) {
		int fila = 0;
		for (int i = 0; i < FILAS; i++) {
			if (estaVacio()) {
				fila = i;
			}
		}
		return fila;
	}

	// metodo menor

	private int menor(int num1, int num2) {
		return Math.min(num1, num2);
	}

	// metodo objetivoAlcanzado

	private boolean objetivoAlcanzado(int fichasConsecutivas) {
		if (fichasConsecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
			return true;
		} else {
			return false;
		}
	}

	public boolean introducirFicha(int leerColumna, Ficha colorFichas) {
		return false;
		// ***************SIN TERMINAR***********************
	}

}
