package br.com.santander.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.json.JSONObject;

import br.com.santander.liferay.dao.ClienteDAO;
import br.com.santander.liferay.dao.UsuarioDAO;
import br.com.santander.liferay.model.Cliente;
import br.com.santander.liferay.model.Usuario;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
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
			JSONObject jsonObject) throws NumberFormatException, PortalException, SystemException {
		String cpf = resourceRequest.getParameter("cpf");
		String login = resourceRequest.getParameter("login");
		String senha = resourceRequest.getParameter("senha");
		String nome = resourceRequest.getParameter("nome");
		String primeiroSobrenome = resourceRequest.getParameter("primeiroSobrenome");
		String ultimoSobrenome = resourceRequest.getParameter("ultimoSobrenome");
		String email = resourceRequest.getParameter("email");
		String sexo = resourceRequest.getParameter("sexo");
		String dataNascimento = "21/07/1989";
		String[] dataNascimentoArray = dataNascimento.split("/");
		Usuario usuario = new Usuario();
		usuario.setCpf(cpf);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.adiciona(usuario);
		User user = UserLocalServiceUtil.addUser(0, CompanyThreadLocal.getCompanyId(), false, senha, senha, false, login, login+"@hotmail.com", 0, StringPool.BLANK, LocaleUtil.getDefault()
				, "Rafael", "", "", 0, 0, true, Integer.parseInt(dataNascimentoArray[1]), Integer.parseInt(dataNascimentoArray[0]), 
				Integer.parseInt(dataNascimentoArray[2]), StringPool.BLANK, null, null, null, null, false, new ServiceContext());
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		Group group = themeDisplay.getLayout().getGroup();
		UserLocalServiceUtil.addGroupUser(group.getGroupId(),user);
		//UserLocalServiceUtil.addOrganizationUser(group.getOrganizationId(), user.getUserId());
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
