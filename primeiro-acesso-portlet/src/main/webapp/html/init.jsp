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
	$('#dataNascimento').mask('00/00/0000');
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
	var senha = $('#senha').val();
	var primeiroNome = $('#primeiroNome').val();
	var nomeDoMeio = $('#nomeDoMeio').val();
	var ultimoNome = $('#ultimoNome').val();
	var email = $('#email').val();
	var dataNascimento = $('#dataNascimento').val();
	if(senha == ''){
		alert('Favor informar a senha que deseja para acessar o sistema.');
		return false;
	}
	if(primeiroNome == ''){
		alert('Favor informar o primeiro nome.');
		return false;
	}
	if(email == ''){
		alert('Favor informar o email.');
		return false;
	}
	if(!$('#masculino').is(':checked') && !$('#feminino').is(':checked')){
		alert('Favor informar o sexo.');
		return false;
	}
	if(dataNascimento == ''){
		alert('Favor informar a data de nascimento.');
		return false;
	}
	var sexo;
	if($('#masculino').is(':checked')){
		sexo = $('#masculino').val();
	}else{
		sexo = $('#feminino').val();
	}
	$.ajax({
		url:'${fazerPrimeiroAcessoUrl}',
		data:{'<portlet:namespace/>cpf':cpf,
			'<portlet:namespace/>senha':senha,
			'<portlet:namespace/>primeiroNome':primeiroNome,
			'<portlet:namespace/>nomeDoMeio':nomeDoMeio,
			'<portlet:namespace/>ultimoNome':ultimoNome,
			'<portlet:namespace/>email':email,
			'<portlet:namespace/>sexo':sexo,
			'<portlet:namespace/>dataNascimento':dataNascimento,
			'<portlet:namespace/>metodo':'fazerPrimeiroAcesso'},
		type:'post',
		datatype:'json',
		success:function(data){
			if(data['status'] == 'sucesso'){
				$('#divCpf').show();
				$('#divLogin').hide();
				alert(data['mensagem']);	
			}else{
				alert('Sistema indisponível, favor tentar mais tarde.');	
			}
		}
	});	
}

</script>