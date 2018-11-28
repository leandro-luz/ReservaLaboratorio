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
	<h1>Usuários Logados</h1>
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


	<Table border = "1">
		<tr>
			<td>Matricula</td>
			<td>Nome</td>
			<td>Perfil</td>
			<td>Acessos</td>
			<td>Último Acesso</td>
			<td>Última Requisição</td>
		</tr>


		<c:forEach var="usuario" items="${logados}">
			<tr>
				<td>${usuario.matricula}</td>
				<td>${usuario.nome}</td>
				<td>${usuario.perfilNome}</td>
				<td>${usuario.acessos}</td>
				<td>${usuario.ultimoAcesso}</td>
				<td>${usuario.ultimaRequisicao}</td>
			</tr>
		</c:forEach>

	</Table>


</body>
</html>