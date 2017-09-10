package br.com.santander.liferay.test;

import br.com.santander.liferay.dao.ClienteDAO;
import br.com.santander.liferay.model.Cliente;

public class ClienteTest {

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.setCpf("11111111111");
		ClienteDAO dao = new ClienteDAO();
		dao.adiciona(cliente);
	}
}
