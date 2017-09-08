package br.com.santander.test;

import br.com.santander.dao.ClienteDAO;
import br.com.santander.model.Cliente;

public class ClienteTest {

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.setCpf("11111111111");
		ClienteDAO dao = new ClienteDAO();
		dao.adiciona(cliente);
	}
}
