package org.iesalandalus.programacion.cuatroenraya;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

public class MainApp {

	public static void main(String[] args) {

		Jugador jugador1 = Consola.leerJugador();
		Ficha fichaJugador2;
		if (jugador1.getColorFichas() == Ficha.AZUL) {
			fichaJugador2 = Ficha.VERDE;
		} else {
			fichaJugador2 = Ficha.AZUL;
		}
		Jugador jugador2 = Consola.leerJugador(fichaJugador2);
		CuatroEnRaya cuatroEnRaya = new CuatroEnRaya(jugador1, jugador2);
		cuatroEnRaya.jugar();

	}

}
