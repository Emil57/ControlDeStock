package com.alura.jdbc.modelo;

public class Categoria {

	private int id;
	 
	private String nombre;

	public Categoria(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
}
