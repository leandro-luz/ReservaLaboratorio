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
	<h1>Dados Cadastrados do Usuário</h1>
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
	<table>
		<tr>
			<td>Nome:</td>
			<td>${sessionScope.usuarioLogado.nome}</td>
		</tr>
		<tr>
			<td>Matricula:</td>
			<td>${sessionScope.usuarioLogado.matricula}</td>
		</tr>
		<tr>
		<tr>
			<td>Email:</td>
			<td>${sessionScope.usuarioLogado.email}</td>
		</tr>
	</table>
	<br>
	<br>

	<a
		href="mvc?logica=IniciaTarefaLogica&url=/WEB-INF/jsp/alterarSenha.jsp">
		Trocar senha? Clique aqui.</a>
</html>