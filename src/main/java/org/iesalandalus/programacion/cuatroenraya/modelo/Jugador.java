package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Jugador {

	// atributos
	private Ficha colorFichas;
	private String nombre;

	// constructor con parametros

	public Jugador(String nombre, Ficha colorFichas) {
		setNombre(nombre);
		setColorFichas(colorFichas);
	}

	// metodos

	// setters y getters
	public Ficha getColorFichas() {
		return colorFichas;
	}

	private void setColorFichas(Ficha colorFichas) {
		if(colorFichas==null) {
			throw new NullPointerException("ERROR: El color de las fichas no puede ser nulo.");
		}else {
			this.colorFichas = colorFichas;
		}		
		
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		} else if (nombre.matches(" ")) {
			throw new IllegalArgumentException("ERROR: El nombre no puede estar vacío.");
		} else if (nombre.matches("") || nombre.matches("\s+")) {
			throw new IllegalArgumentException("ERROR: El nombre no puede estar vacío.");
		} else {
			this.nombre = nombre;
		}

	}

	@Override
	public String toString() {
		return String.format("%s (%s)", nombre, colorFichas);
	}

}
