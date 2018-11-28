<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>RESERVA</title>
<link rel="shortcut icon" href="imagens/title.ico">
</head>

<body>

	<table>
		<tr>

			<td><a
				href="mvc?logica=IniciaTarefaLogica&url=/WEB-INF/jsp/agendamento.jsp">
					Reservas</a></td>


			<td><a
				href="mvc?logica=IniciaTarefaLogica&url=/WEB-INF/jsp/dadosUsuario.jsp">
					Olá-- ${sessionScope.usuarioLogado.nome}</a></td>

			<td><a href="mvc?logica=Login&bOK=null"> Sair</a></td>
			
			<c:if
				test="${sessionScope.usuarioLogado.perfil != null && sessionScope.usuarioLogado.perfil == 3}">

				<td><a
					href="mvc?logica=IniciaTarefaLogica&url=/WEB-INF/jsp/administrador.jsp">
						Administração</a></td>

				<td><a
					href="mvc?logica=IniciaTarefaLogica&url=/WEB-INF/jsp/usuariosLogados.jsp">Usuários
						logados: ${sessionScope.qtdUsuariosLogados} </a></td>
						
			</c:if>

		</tr>
	</table>
</html>