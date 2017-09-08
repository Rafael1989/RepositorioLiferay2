package br.com.santander.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.santander.model.Cliente;

public class ClienteDAO {
	
	private Connection connection;
	
	public ClienteDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Cliente cliente){
		try{
			String sql = "insert into cliente (cpf) values (?)";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, cliente.getCpf());
			stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public Cliente getCliente(String cpf){
		Cliente cliente = new Cliente();
		try{
			String sql = "select * from cliente where cpf = ?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				cliente.setCpf(rs.getString("cpf"));
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return cliente;
	}

}
