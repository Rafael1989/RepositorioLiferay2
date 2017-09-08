package br.com.santander.test;

import br.com.santander.dao.ConnectionFactory;

public class ConnectionFactoryTest {
	
	public static void main(String[] args) {
		new ConnectionFactory().getConnection();
		System.out.println("Conexão estabelecida...");
	}

}
