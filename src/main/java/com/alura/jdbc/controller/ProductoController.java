package com.alura.jdbc.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alura.jdbc.factory.ConnectionFactory;

import java.util.HashMap;


public class ProductoController {

	public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) throws SQLException {
        Connection con = new ConnectionFactory().recuperarConexion();
        PreparedStatement statement = con.prepareStatement("UPDATE PRODUCTO SET " 
                + "NOMBRE = ?,"
                + "DESCRIPCION = ?,"
                + "CANTIDAD = ? "
                + "WHERE ID = ?");
        
        statement.setString(1, nombre);
        statement.setString(2, descripcion);
        statement.setInt(3, cantidad);
        statement.setInt(4, id);

        
        statement.execute();
        return statement.getUpdateCount();
	}

	public int eliminar(Integer id) throws SQLException {
        Connection con = new ConnectionFactory().recuperarConexion();
        PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");
        statement.setInt(1, id);
        statement.execute();
        return statement.getUpdateCount();
	}

	public List<Map<String, String>> listar() throws SQLException {
        Connection con = new ConnectionFactory().recuperarConexion();

		PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
		statement.execute();
		ResultSet resultSet = statement.getResultSet();
		
		List<Map<String, String>> resultado = new ArrayList<>();
		
		while(resultSet.next()) {
			Map<String,String> fila = new HashMap<>();
			fila.put("ID", String.valueOf(resultSet.getInt("ID")));
			fila.put("NOMBRE", String.valueOf(resultSet.getString("NOMBRE")));
			fila.put("DESCRIPCION", String.valueOf(resultSet.getString("DESCRIPCION")));
			fila.put("CANTIDAD", String.valueOf(resultSet.getInt("CANTIDAD")));
			resultado.add(fila);
		}
		con.close();
		return resultado;
	}

    public void guardar(Map<String, String> producto) throws SQLException {
    	Connection con = new ConnectionFactory().recuperarConexion();
    	PreparedStatement statement = con.prepareStatement("INSERT INTO PRODUCTO(nombre, descripcion, cantidad)"
    			+ "VALUES (?, ?, ?)",
    			Statement.RETURN_GENERATED_KEYS);
    	statement.setString(1, producto.get("NOMBRE"));
    	statement.setString(2, producto.get("DESCRIPCION"));
    	statement.setInt(3, Integer.valueOf(producto.get("CANTIDAD")));
    	statement.execute();
    	
    	ResultSet resultSet = statement.getGeneratedKeys();
    	
    	while(resultSet.next()) {
    		System.out.println(String.format("ID --> %d", resultSet.getInt(1)));    		
    	}
    }

}
