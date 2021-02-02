package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

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

	// metodo estaVacio

	public boolean estaVacio() {
		boolean vacio = true;
		for (int i = 0; i < COLUMNAS && vacio; i++) {
			vacio = columnaVacia(i);
		}
		return vacio;
	}

	// metodo columnaLlena

	private boolean columnaLlena(int columna) {
		return casillas[FILAS - 1][columna].estaOcupada();
	}

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
			throw new IllegalArgumentException("ERROR: Columna incorrecta.");
		}

	}

	// metodo getPrimeraFilaVacia

	private int getPrimeraFilaVacia(int columna) {
		int fila = 0;
		for (int i = 0; i < FILAS; i++) {
			if (!(casillas[i][columna].estaOcupada())) {
				fila = i;
				break;
			}
		}
		return fila;
	}

	// metodo objetivoAlcanzado

	private boolean objetivoAlcanzado(int fichasConsecutivas) {
		if (fichasConsecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
			return true;
		} else {
			return false;
		}
	}

	// metodo comprobarHorizontal

	private boolean comprobarHorizontal(int fila, Ficha ficha) {
		int contadorFicha = 0;
		boolean horizontalConsecutiva = false;
		for (int i = 0; i < COLUMNAS; i++) {
			if (casillas[fila][i].getFicha() == ficha) {
				contadorFicha++;
				if (objetivoAlcanzado(contadorFicha))// == true)
					horizontalConsecutiva = true;

			} else {
				contadorFicha = 0;
			}
		}

		return horizontalConsecutiva;

	}

	// metodo comprobarVertical

	private boolean comprobarVertical(int columna, Ficha ficha) {

		int contadorFicha = 0;
		boolean verticalConsecutiva = false;
		for (int i = 0; i < FILAS; i++) {
			if (casillas[i][columna].getFicha() == ficha) {
				contadorFicha++;
				if (objetivoAlcanzado(contadorFicha))// == true)
					verticalConsecutiva = true;

			} else {
				contadorFicha = 0;
			}
		}

		return verticalConsecutiva;

	}

	// metodo menor

	private int menor(int num1, int num2) {
		return Math.min(num1, num2);
	}

	// metodo comprobarDiagonalNE

	private boolean comprobarDiagonalNE(int fila, int columna, Ficha ficha) {

		int desplazamientoInicial = menor(fila, columna);
		int filaInicial = fila - desplazamientoInicial;
		int columnaInicial = columna - desplazamientoInicial;
		int contadorFicha = 0;
		boolean diagonalNeConseguida = false;

		while (filaInicial < FILAS && columnaInicial < COLUMNAS) {
			if (casillas[filaInicial][columnaInicial].getFicha() == ficha) {
				contadorFicha++;
				if (objetivoAlcanzado(contadorFicha)) {
					diagonalNeConseguida = true;
					return diagonalNeConseguida;
				}
			} else {
				contadorFicha = 0;
			}
			filaInicial++;
			columnaInicial++;
		}

		return diagonalNeConseguida;

	}

	private boolean comprobarDiagonalNO(int fila, int columna, Ficha ficha) {

		int desplazamientoInicial = menor(fila, COLUMNAS - 1 - columna);
		int filaInicial = fila - desplazamientoInicial;
		int columnaInicial = columna + desplazamientoInicial;
		int contadorFicha = 0;
		boolean diagonalNoConseguida = false;

		while (filaInicial < FILAS && columnaInicial >= 0) {
			if (casillas[filaInicial][columnaInicial].getFicha() == ficha) {
				contadorFicha++;
				if (objetivoAlcanzado(contadorFicha)) {
					diagonalNoConseguida = true;
					return diagonalNoConseguida;
				}
			} else {
				contadorFicha = 0;
			}
			filaInicial++;
			columnaInicial--;
		}

		return diagonalNoConseguida;

	}

	private boolean comprobarTirada(int fila, int columna) {
		if (comprobarVertical(columna, casillas[fila][columna].getFicha())
				|| comprobarHorizontal(fila, casillas[fila][columna].getFicha())
				|| comprobarDiagonalNE(fila, columna, casillas[fila][columna].getFicha())
				|| comprobarDiagonalNO(fila, columna, casillas[fila][columna].getFicha())) {
			return true;
		} else {
			return false;
		}
	}

	// metodo introducirFicha

	public boolean introducirFicha(int columna, Ficha ficha) throws OperationNotSupportedException {
		comprobarFicha(ficha);
		comprobarColumna(columna);
		if (columnaLlena(columna) == true) {
			throw new OperationNotSupportedException("ERROR: Columna llena.");
		} else {
			int fila = getPrimeraFilaVacia(columna);
			casillas[fila][columna].setFicha(ficha);
			return comprobarTirada(fila, columna);
		}
	}

	// metodo toString

	public String toString() {

		StringBuilder tablero = new StringBuilder();

		for (int i = FILAS - 1; i >= 0; i--) {
			tablero.append("|");

			for (int j = 0; j < COLUMNAS; j++) {
				tablero.append(casillas[i][j]);
			}
			tablero.append("|\n");
		}
		tablero.append(" -------\n");

		return tablero.toString();
	}

}
