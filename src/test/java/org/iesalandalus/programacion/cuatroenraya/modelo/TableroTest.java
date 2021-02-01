package org.iesalandalus.programacion.cuatroenraya.modelo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import javax.naming.OperationNotSupportedException;

import org.junit.Test;

public class TableroTest {
	
	private static final String TABLERO_DEBERIA_ESTAR_VACIO = "El tablero debería estar vacío.";
	private static final String TABLERO_NO_DEBERIA_ESTAR_VACIO = "El tablero no debería estar vacío.";
	private static final String TABLERO_DEBERIA_ESTAR_LLENO = "El tablero debería estar lleno.";
	private static final String TABLERO_NO_DEBERIA_ESTAR_LLENO = "El tablero no debería estar lleno.";
	private static final String OBJETIVO_NO_ALCANZADO = "No debería haber indicado que hemos alcanzado el objetivo.";
	private static final String OBJETIVO_ALCANZADO = "Debería haber indicado que hemos alcanzado el objetivo.";
	private static final String CADENA_NO_ESPERADA = "La cadena que representa el tablero no es el esperado.";

	private static final String MENSAJE_EXCEPCION_NO_ESPERADO = "El mensaje de la excepción no es el esperado.";
	private static final String NO_EXCEPCION = "No debería haber saltado este tipo de excepción.";
	private static final String EXCEPCION_ESPERADA = "Debería haber saltado la excepción.";
	private static final String ERROR_COLUMNA_INCORRECTA = "ERROR: Columna incorrecta.";
	private static final String ERROR_COLUMNA_LLENA = "ERROR: Columna llena.";
	private static final String ERROR_FICHA_NULA = "ERROR: La ficha no puede ser nula.";

	@Test
	public void constructorCreaTableroVacio() {
		Tablero tablero = new Tablero();
		assertThat(TABLERO_DEBERIA_ESTAR_VACIO, tablero.estaVacio(), is(true));
	}
	
	@Test
	public void estaVacioCompruebaCorrectamente() {
		Tablero tablero = new Tablero();
		assertThat(TABLERO_DEBERIA_ESTAR_VACIO, tablero.estaVacio(), is(true));
		try {
			tablero.introducirFicha(4, Ficha.AZUL);
			assertThat(TABLERO_NO_DEBERIA_ESTAR_VACIO, tablero.estaVacio(), is(false));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
	}
	
	@Test
	public void estaLlenoCompruebaCorrectamente() {
		Tablero tablero = new Tablero();
		assertThat(TABLERO_NO_DEBERIA_ESTAR_LLENO, tablero.estaLleno(), is(false));
		try {
			for (int columna = 0; columna < Tablero.COLUMNAS; columna++) {
				llenarColumna(tablero, columna, Ficha.AZUL);
			}
			assertThat(TABLERO_DEBERIA_ESTAR_LLENO, tablero.estaLleno(), is(true));
		} catch (Exception e) {
			System.out.println(e);
			fail(NO_EXCEPCION);
		}
	}
	
	private void llenarColumna(Tablero tablero, int columna, Ficha ficha) throws OperationNotSupportedException {
		for (int veces = 0; veces < Tablero.FILAS; veces++) {
			tablero.introducirFicha(columna, ficha);
		}
	}
	
	@Test
	public void introducirFichaColumnaValidaFichaValidaCompruebaObjetivoCorrectamente() {
		comprobarHorizontalFilaValidaDetectaObjetivoHorizontal();
		comprobarVerticalDetectaObjetivoVertical();
		comprobarDiagonalNEDetectaObjetivoDiagonalNE();
		comprobarDiagonalNODetectaObjetivoDiagonalNO();
	}
	
	@Test
	public void introducirFichaColumnaNoValidaLanzaExcepcion() {
		Tablero tablero = new Tablero();
		try {
			tablero.introducirFicha(-1, Ficha.AZUL);
			fail(EXCEPCION_ESPERADA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_EXCEPCION_NO_ESPERADO, e.getMessage(), is(ERROR_COLUMNA_INCORRECTA));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
		try {
			tablero.introducirFicha(Tablero.COLUMNAS, Ficha.AZUL);
			fail(EXCEPCION_ESPERADA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_EXCEPCION_NO_ESPERADO, e.getMessage(), is(ERROR_COLUMNA_INCORRECTA));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
	}
	
	@Test
	public void introducirFichaColumnaLlenaLanzaExcepcion() {
		Tablero tablero = new Tablero();
		try {
			llenarColumna(tablero, 4, Ficha.AZUL);
			tablero.introducirFicha(4, Ficha.AZUL);
			fail(EXCEPCION_ESPERADA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_EXCEPCION_NO_ESPERADO, e.getMessage(), is(ERROR_COLUMNA_LLENA));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
		try {
			tablero.introducirFicha(4, Ficha.VERDE);
			fail(EXCEPCION_ESPERADA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_EXCEPCION_NO_ESPERADO, e.getMessage(), is(ERROR_COLUMNA_LLENA));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
	}
	
	@Test
	public void introducirFichaNulaLanzaExcepcion() {
		Tablero tablero = new Tablero();
		try {
			tablero.introducirFicha(4, null);
			fail(EXCEPCION_ESPERADA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_EXCEPCION_NO_ESPERADO, e.getMessage(), is(ERROR_FICHA_NULA));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
	}
	
	private void comprobarHorizontalFilaValidaDetectaObjetivoHorizontal() {
		Tablero tablero = new Tablero();
		try {
			tablero.introducirFicha(4, Ficha.AZUL);
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(3, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(2, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(6, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_ALCANZADO, tablero.introducirFicha(5, Ficha.AZUL), is(true));
		} catch (OperationNotSupportedException e) {
			fail(NO_EXCEPCION);
		}
		tablero = new Tablero();
		try {
			tablero.introducirFicha(4, Ficha.AZUL);
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(3, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(2, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(6, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_ALCANZADO, tablero.introducirFicha(5, Ficha.VERDE), is(false));
		} catch (OperationNotSupportedException e) {
			fail(NO_EXCEPCION);
		}
	}
	
	private void comprobarVerticalDetectaObjetivoVertical() {
		Tablero tablero = new Tablero();
		try {
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_ALCANZADO, tablero.introducirFicha(4, Ficha.AZUL), is(true));
		} catch (OperationNotSupportedException e) {
			fail(NO_EXCEPCION);
		}
		tablero = new Tablero();
		try {
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_ALCANZADO, tablero.introducirFicha(4, Ficha.AZUL), is(true));
		} catch (OperationNotSupportedException e) {
			fail(NO_EXCEPCION);
		}
	}
	
	private void comprobarDiagonalNEDetectaObjetivoDiagonalNE() {
		Tablero tablero = new Tablero();
		try {
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(3, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(5, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(5, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(5, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(6, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(6, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(6, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_ALCANZADO, tablero.introducirFicha(6, Ficha.AZUL), is(true));
		} catch (OperationNotSupportedException e) {
			fail(NO_EXCEPCION);
		}
		tablero = new Tablero();
		try {
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(0, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(1, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(1, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(2, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(2, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(2, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(3, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(3, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(3, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(3, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(5, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(5, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(5, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(5, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(5, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_ALCANZADO, tablero.introducirFicha(5, Ficha.AZUL), is(true));
		} catch (OperationNotSupportedException e) {
			fail(NO_EXCEPCION);
		}
	}
	
	private void comprobarDiagonalNODetectaObjetivoDiagonalNO() {
		Tablero tablero = new Tablero();
		try {
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(3, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(2, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(2, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(1, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(1, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(1, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(0, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(0, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(0, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_ALCANZADO, tablero.introducirFicha(0, Ficha.AZUL), is(true));
		} catch (OperationNotSupportedException e) {
			fail(NO_EXCEPCION);
		}
		tablero = new Tablero();
		try {
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(5, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(4, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(3, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(3, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(3, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(2, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(2, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(2, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(2, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(1, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(1, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(1, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(1, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(1, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(0, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(0, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(0, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(0, Ficha.VERDE), is(false));
			assertThat(OBJETIVO_NO_ALCANZADO, tablero.introducirFicha(0, Ficha.AZUL), is(false));
			assertThat(OBJETIVO_ALCANZADO, tablero.introducirFicha(0, Ficha.AZUL), is(true));
		} catch (OperationNotSupportedException e) {
			fail(NO_EXCEPCION);
		}
	}
	
	@Test
	public void toStringRepresentaCorrectamenteTablero() {
		String tableroString = "|       |\n|       |\n|       |\n|       |\n|       |\n|       |\n -------\n";
		Tablero tablero = new Tablero();
		assertThat(CADENA_NO_ESPERADA, tablero.toString(), is(tableroString));
		tableroString = "|       |\n|       |\n|       |\n|       |\n|      V|\n|     AV|\n -------\n";
		tablero = new Tablero();
		try {
			tablero.introducirFicha(5, Ficha.AZUL);
			tablero.introducirFicha(6, Ficha.VERDE);
			tablero.introducirFicha(6, Ficha.VERDE);
			assertThat(CADENA_NO_ESPERADA, tablero.toString(), is(tableroString));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
		tableroString = "|AAAAAAA|\n|AAAAAAA|\n|AAAAAAA|\n|AAAAAAA|\n|AAAAAAA|\n|AAAAAAA|\n -------\n";
		tablero = new Tablero();
		try {
			for (int columna = 0; columna < Tablero.COLUMNAS; columna++) {
				llenarColumna(tablero, columna, Ficha.AZUL);
			}
			assertThat(CADENA_NO_ESPERADA, tablero.toString(), is(tableroString));
		} catch (Exception e) {
			fail(NO_EXCEPCION);
		}
	}
	
}
