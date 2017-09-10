<%@ include file="init.jsp" %>
<html>
	<body onload="inicio();">
		<h3>Primeiro Acesso</h3>
		<p>Essa página tem como objetivo criar o acesso ao cliente.</p>
		<div id="divCpf">
			*CPF:<br/>
			<input type="text" name="<portlet:namespace/>cpf" maxlength="11" id="cpf"><br/>
			<input type="button" onclick="validarCpf();" value="Validar CPF">
		</div>
		<div id="divLogin">
			<p>Favor preencher os dados a seguir:.</p>
			*Primeiro Nome:<br/>
			<input type="text" name="<portlet:namespace/>primeiroNome" id="primeiroNome"><br/>
			Nome do meio:<br/>
			<input type="text" name="<portlet:namespace/>nomeDoMeio" id="nomeDoMeio"><br/>
			Último nome:<br/>
			<input type="text" name="<portlet:namespace/>ultimoNome" id="ultimoNome"><br/>
			*Email:<br/>
			<input type="text" name="<portlet:namespace/>email" id="email"><br/>
			*Sexo:<br/>
			<input type="radio" name="<portlet:namespace/>sexo" value="Masculino" id="masculino">Masculino<br/>
			<input type="radio" name="<portlet:namespace/>sexo" value="Feminino" id="feminino">Feminino<br/>
			*Data de Nascimento:<br/>
			<input type="text" name="<portlet:namespace/>dataNascimento" id="dataNascimento"><br/>
			*Senha:<br/>
			<input type="password" name="<portlet:namespace/>senha" id="senha"><br/>
			<input type="button" onclick="fazerPrimeiroAcesso();" value="Fazer Primeiro Acesso">
		</div>
	</body>
</html>