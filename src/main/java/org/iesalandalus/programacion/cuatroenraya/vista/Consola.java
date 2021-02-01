package org.iesalandalus.programacion.cuatroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private Consola() {

	}

	public static String leerNombre() {

        String nombre;

        do {
            System.out.println("Introduce el nombre del jugador: ");
            nombre = Entrada.cadena();
        } while(nombre.matches ("") || nombre.matches ("\s+") || nombre == null);

        return nombre;
    }

	public static Ficha elegirColorFichas() {

        int opcion;

        do {
            System.out.println("Elige el color de tus fichas: (0-AZUL, 1-VERDE): ");
            opcion = Entrada.entero();
        } while (opcion!=0 && opcion!=1);

        Ficha colorFichas;

        if (opcion == 0) {
            colorFichas = Ficha.AZUL;
        } else {
            colorFichas = Ficha.VERDE;
        }

        return colorFichas;
    }
	
	public static Jugador leerJugador() {

        System.out.println("Introduce los datos del PRIMER jugador ");
        String primerNombre = leerNombre();
        Ficha primeraFicha = elegirColorFichas();
        Jugador primerJugador = new Jugador(primerNombre, primeraFicha);

        return primerJugador;
    }
	
	public static Jugador leerJugador (Ficha ficha) {

        System.out.println("Introduce los datos del SEGUNDO jugador ");
        String segundoNombre = leerNombre();
        Jugador segundoJugador = new Jugador(segundoNombre, ficha);

        return segundoJugador;
    }
	
	public static int leerColumna(Jugador jugador) {

        int columna;

        do {
            System.out.printf("%s, introduce la columna en la que deseas introducir la ficha (0 - 6): ", jugador.getNombre());
            columna = Entrada.entero();
        } while (columna<0 || columna>6);

        return columna;
    }
		
	

}

