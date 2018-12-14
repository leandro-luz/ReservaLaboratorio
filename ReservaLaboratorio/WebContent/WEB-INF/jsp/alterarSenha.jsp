<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RESERVA</title>
<link rel="shortcut icon" href="imagens/title.ico">
</head>
<body>
	<c:import url="/WEB-INF/jsp/cabecalho.jsp" />
	<h1>Alteração de Senha</h1>
	<c:if test="${mensagens.existeErros}">
		<div id="erro">
			<ul>
				<c:forEach var="erro" items="${mensagens.erros}">
					<li>${erro}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<c:if test="${noticias.existeAvisos}">
		<div id="aviso">
			<ul>
				<c:forEach var="aviso" items="${noticias.avisos}">
					<li>${aviso}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<form method="post" action="mvc?logica=AlterarSenha">
		<table>
			<tr>
				<th>Login:</th>
				<td>${usuarioLogado.matricula}</td>
			</tr>
			<tr>
				<th>Senha Antiga:</th>
				<td><input type="password" name="senhaAntiga" required/></td>
			</tr>
			<tr>
				<th>Senha Nova:</th>
				<td><input type="password" name="senhaNova1" required/>Deve conter no mínimo: 1 letra maiúscula, 1 letra minúscula, 1 caracter especial (#,$,%,&,',(,),*,+,,,-) e 8 caracteres</td>
			</tr>
			<tr>
				<th>Confirma a Senha Nova:</th>
				<td><input type="password" name="senhaNova2" required/></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" name="bOK" value="Alterar" />
				</td>
			</tr>
		</table>
	</form>
	<form method="post" action="mvc?logica=IniciaTarefaLogica&url=/WEB-INF/jsp/dadosUsuario.jsp">
		<input type="submit" name="bOK" value="Voltar" />
	</form>
</body>
</html>