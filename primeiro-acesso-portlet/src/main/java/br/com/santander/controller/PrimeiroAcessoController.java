package br.com.santander.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.json.JSONObject;

import br.com.santander.dao.ClienteDAO;
import br.com.santander.dao.UsuarioDAO;
import br.com.santander.model.Cliente;
import br.com.santander.model.Usuario;

import com.liferay.util.bridges.mvc.MVCPortlet;

public class PrimeiroAcessoController extends MVCPortlet{
	
	@Override
	public void serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException,
			PortletException {
		String metodo = resourceRequest.getParameter("metodo");
		JSONObject jsonObject = new JSONObject();
		try{
			if(metodo.equals("validarCpf")){
				jsonObject = validarCpf(resourceRequest, jsonObject);
			}else if(metodo.equals("fazerPrimeiroAcesso")){
				jsonObject = fazerPrimeiroAcesso(resourceRequest,jsonObject);
			}
			jsonObject.put("status", "sucesso");
		}catch(Exception e){
			jsonObject.put("status", "erro");
		}finally{
			resourceResponse.setContentType("application/json");
			PrintWriter printWriter = resourceResponse.getWriter();
			printWriter.print(jsonObject);
			printWriter.flush();
		}
	}
	
	private JSONObject fazerPrimeiroAcesso(ResourceRequest resourceRequest,
			JSONObject jsonObject) {
		String cpf = resourceRequest.getParameter("cpf");
		String login = resourceRequest.getParameter("login");
		String senha = resourceRequest.getParameter("senha");
		Usuario usuario = new Usuario();
		usuario.setCpf(cpf);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.adiciona(usuario);
		jsonObject.put("mensagem", "Login cadastrado com sucesso.");
		return jsonObject;
	}

	public JSONObject validarCpf(ResourceRequest resourceRequest, JSONObject jsonObject){
		String cpf = resourceRequest.getParameter("cpf");
		ClienteDAO clienteDao = new ClienteDAO();
		Cliente cliente = clienteDao.getCliente(cpf);
		
		if(cliente.getCpf() != null && cliente.getCpf().equals(cpf)){
			jsonObject.put("cpfValido", "true");
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.getUsuario(cpf);
			if(usuario.getCpf() != null && usuario.getCpf().equals(cpf)){
				jsonObject.put("temAcesso", "true");
			}else{
				jsonObject.put("temAcesso", "false");
			}
		}else{
			jsonObject.put("cpfValido", "false");
		}
		return jsonObject;
	}
	

}
