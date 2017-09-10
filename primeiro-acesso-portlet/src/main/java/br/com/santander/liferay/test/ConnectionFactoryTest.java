package br.com.santander.liferay.test;

import br.com.santander.liferay.dao.ConnectionFactory;

public class ConnectionFactoryTest {
	
	public static void main(String[] args) {
		new ConnectionFactory().getConnection();
		System.out.println("Conexão estabelecida...");
	}

}
