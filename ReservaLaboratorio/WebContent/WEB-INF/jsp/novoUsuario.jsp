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
	<h1>Solicitação de Acesso</h1>
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
	<form method="post" action="mvc?logica=AdicionaUsuarioLogica">
		<table>
			<tr>
				<th>Matrícula:</th>
				<td><input type="number" name="matricula" required
					placeholder="somente números">Informar 8 caracteres!</td>
			</tr>
			<tr>
				<th>Nome:</th>
				<td><input type="text" name="nome" required
					placeholder="informe seu nome"></td>
			</tr>
			<tr>
				<th>Email:</th>
				<td><input type="email" name="email" required
					placeholder="seuemail@enel.com.br"></td>
			</tr>
			<tr>
				<th>Senha:</th>
				<td><input type="password" name="senha1" required /></td>
			</tr>
			<tr>
				<th>Confirma a Senha:</th>
				<td><input type="password" name="senha2" required /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="bOK" value="Salvar" />
				</td>
			</tr>
		</table>
	</form>
	<form method="post" action="mvc?logica=IniciaTarefaLogica&new=new&url=/WEB-INF/jsp/login.jsp">
		<input type="submit" name="bOK" value="Voltar" />
	</form>

</body>
</html>