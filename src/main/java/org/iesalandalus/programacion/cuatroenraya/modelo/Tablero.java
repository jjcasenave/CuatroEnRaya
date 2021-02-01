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

	public boolean estaLleno() {
		boolean lleno = true;
		for (int i = 0; i < COLUMNAS && lleno; i++) {
			lleno &= columnaLlena(i);
		}
		return lleno;
	}

	private void comprobarFicha(Ficha ficha) {

		if (!(ficha == ficha.AZUL || ficha == ficha.VERDE)) {
			throw new IllegalArgumentException("ERROR: La ficha introducida no es correcta.");
		}

	}

	private void comprobarColumna(int columna) {
		// si la columna no es correcta, lanza excepcion

		if (!(columna < 0 || columna > 6))
			throw new IllegalArgumentException("ERROR: La columna introducida no es correcta.");

	}

	private int getPrimeraFilaVacia(int columna) {
		int fila = 0;
		for (int i = 0; i < FILAS; i++) {
			if (estaVacio()) {
				fila = i;
			}
		}
		return fila;
	}

	private int menor(int num1, int num2) {
		return Math.min(num1, num2);
	}

	public boolean introducirFicha(int leerColumna, Ficha colorFichas) {
		// TODO Auto-generated method stub
		return false;
	}

}

/*
 * 
 * Crea el método objetivoAlcanzado que aceptará como parámetro el número de
 * fichas consecutivas que llevamos en una de las comprobaciones (horizontal,
 * vertical, diagonalNE o diagonalNO) y devolverá true o false si el parámetro
 * pasado es mayor o igual que la constante definida en la clase para la fichas
 * conscutivas necesarias.
 * 
 * Crea el método comprobarHorizontal que aceptará la fila y la ficha para la
 * que queremos comprobar si hay cuatro fichas del mismo color seguidas en
 * horizontal.
 * 
 * Crea el método comprobarVertical que aceptará la columna y la ficha para la
 * que queremos comprobar si hay cuatro fichas del mismo color seguidas en
 * vertical.
 * 
 * 
 * Crea el método comprobarDiagonalNE que recibirá como parámetros la fila, la
 * columna y la ficha para la que queremos comprobar si hay cuatro fichas del
 * mismo color seguidas en la diagonal que va desde abajo a la izquierda hasta
 * arriba a la derecha pasando por la casilla indicada por la fila y la columna.
 * Para ayudarte, te indico que debes calcular el desplazamiento inicial como el
 * mínimo entre la fila y la columna pasadas. Luego calcularás la fila inicial
 * como la fila pasada menos el desplazamiento inicial y la columna inicial como
 * la columna pasada menos el desplazamiento inicial. Luego realizarás un bucle
 * que empezando en la fila inicial y la columna inicial y terminando cuando
 * lleguemos al límite de las filas o de las columnas, incremente en uno la fila
 * y la columna (las variables de control del bucle) y cuente cuántas fichas de
 * ese color hay seguidas. Devolverá true si las fichas seguidas contadas es
 * igual a 4.
 * 
 * Crea el método comprobarDiagonalNO que recibirá como parámetros la fila, la
 * columna y la ficha para la que queremos comprobar si hay cuatro fichas del
 * mismo color seguidas en la diagonal que va desde abajo a la derecha hasta
 * arriba a la izquierda pasando por la casilla indicada por la fila y la
 * columna. Para ayudarte, te indico que debes calcular el desplazamiento
 * inicial como el mínimo entre la fila y, el número total de columnas menos 1
 * menos la columna pasada. Luego calcularás la fila inicial como la fila pasada
 * menos el desplazamiento inicial y la columna inicial como la columna pasada
 * más el desplazamiento inicial. Luego realizarás un bucle que empezando en la
 * fila inicial y la columna inicial y terminando cuando lleguemos al límite de
 * las filas o 0 para las columnas, incremente en uno la fila y decremente en
 * uno la columna (las variables de control del bucle) y cuente cuántas fichas
 * de ese color hay seguidas. Devolverá true si las fichas seguidas contadas es
 * igual a 4.
 * 
 * Crea el método comprobarTirada que aceptará como parámetros la fila, la
 * columna y la ficha de la última tirada para comprobar si es ganadora o no.
 * Este método hará uso de los métodos comprobarXXX anteriormente definidos.
 * 
 * Crea el método introducirFicha que aceptará como parámetros la columna en la
 * que queremos introducir la ficha y la ficha a introducir y devolverá true o
 * false dependiendo de si la jugada ha sido ganadora o no (hemos conseguido
 * cuatro en raya o no). Este método deberá comprobar la validez de los
 * parámetros pasados o lanzar la excepción correspondiente. También podrá
 * lanzar una excepción indicando que la fila en la que queremos introducir la
 * ficha está llena. Para ello este método hará uso de los métodos anteriormente
 * creados.
 * 
 * Crea el método toString que haciendo uso de la clase StringBuilder devuelva
 * la representación del tablero que has visto en las imágenes anteriores.
 */
