package com.alura.jdbc.pruebas;

import java.sql.Connection;
import java.sql.SQLException;

import com.alura.jdbc.factory.ConnectionFactory;

public class PruebaPoolDeConexiones {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		for (int i = 0; i < 12; i++) {
			Connection conexion = connectionFactory.recuperarConexion();
			System.out.println("Conexion: "+ i);
		}
	}

}
