<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="theme" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>

<%@ page import="javax.portlet.PortletURL"%>
<%@ page import="javax.portlet.ActionRequest"%>
<%@ page import="javax.portlet.PortletPreferences"%>

<portlet:defineObjects />
<theme:defineObjects/> 

<portlet:resourceURL var="validarCpfUrl"></portlet:resourceURL>
<portlet:resourceURL var="fazerPrimeiroAcessoUrl"></portlet:resourceURL>


<script>

function inicio(){
	$('#divCpf').show();
	$('#divLogin').hide();
}

function validarCpf(){
	var cpf = $('#cpf').val(); 
	if(cpf == ''){
		alert("Favor informar o cpf!");
		return false;
	}
	$.ajax({
		url: '${validarCpfUrl}',
		data:{'<portlet:namespace/>cpf':cpf,
			'<portlet:namespace/>metodo':'validarCpf'},
		type: 'post',
		datatype: 'json',
		success: function(data){
			if(data['status'] == 'sucesso'){
				if(data['cpfValido'] == 'true'){
					if(data['temAcesso'] == 'true'){
						alert('Você já fez o primeiro acesso, favor recuperar sua senha.');
					}else{
						$('#divCpf').hide();
						$('#divLogin').show();						
					}
				}else{
					alert('O cpf informado é inválido.');
				}	
			}else{
				alert('Sistema indisponível, favor tentar mais tarde.')
			}
		}
	});
}

function fazerPrimeiroAcesso(){
	var cpf = $('#cpf').val();
	var login = $('#login').val();
	var senha = $('#senha').val();
	if(login == ''){
		alert('Favor informar o login que deseja para acessar o sistema.');
		return false;	
	}
	if(senha == ''){
		alert('Favor informar a senha que deseja para acessar o sistema.');
		return false;
	}
	$.ajax({
		url:'${fazerPrimeiroAcessoUrl}',
		data:{'<portlet:namespace/>cpf':cpf,
			'<portlet:namespace/>login':login,
			'<portlet:namespace/>senha':senha,
			'<portlet:namespace/>metodo':'fazerPrimeiroAcesso'},
		type:'post',
		datatype:'json',
		success:function(data){
			$('#cpf').val('');
			$('#login').val('');
			$('#senha').val('');
			alert(data['mensagem']);
		}
	});	
}

</script>