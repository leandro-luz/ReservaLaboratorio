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

	<h1>Autenticação de Usuário</h1>
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

	<form method="post" action="mvc?logica=Login">
		<table>
			<tr>
				<th>Login:</th>
				<td><input type="text" name="matricula"
					value="${param.matricula}" required /></td>
			</tr>
			<tr>
				<th>Senha:</th>
				<td><input type="password" name="senha" required /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="bOK" value="Entrar" />
				</td>
			</tr>
		</table>
	</form>


	<a
		href="mvc?logica=IniciaTarefaLogica&new=new&url=/WEB-INF/jsp/novoUsuario.jsp">
		Sem acesso? Clique aqui para se cadastrar.</a>
</body>
</html>