<%@ include file="init.jsp" %>
<html>
	<body onload="inicio();">
		<h3>Primeiro Acesso</h3>
		<p>Essa página tem como objetivo criar o acesso ao cliente.</p>
		<div id="divCpf">
			CPF:<br/>
			<input type="text" name="<portlet:namespace/>cpf" maxlength="11" id="cpf"><br/>
			<input type="button" onclick="validarCpf();" value="Validar CPF">
		</div>
		<div id="divLogin">
			<p>Favor digitar o login e senha que desejar se logar no sistema.</p>
			Login:<br/>
			<input type="text" name="<portlet:namespace/>login" id="login"><br/>
			Senha:<br/>
			<input type="password" name="<portlet:namespace/>senha" id="senha"><br/>
			<input type="button" onclick="fazerPrimeiroAcesso();" value="Fazer Primeiro Acesso">
		</div>
	</body>
</html>