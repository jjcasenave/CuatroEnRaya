package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Jugador {
	
	//atributos
	private Ficha ficha;
	private String nombre;
	
	//constructor con parametros
	
	public Jugador(String nombre, Ficha ficha)	{
		setNombre(nombre);
		setFicha(ficha);
	}
	
	//metodos
	
	//setters y getters
	public Ficha getFicha() {
		return ficha;
	}
	private void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}
	public String getNombre() {
		return nombre;
	}
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return String.format("%s (%s)", nombre, ficha);
	}

	
	
	
	

}
