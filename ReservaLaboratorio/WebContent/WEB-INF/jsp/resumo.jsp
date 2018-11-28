<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" media="screen"
	href="css/estilos.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RESERVA</title>
<link rel="shortcut icon" href="imagens/title.ico">
</head>
<body>
	<c:import url="/WEB-INF/jsp/cabecalho.jsp" />
	<h1>Resumo</h1>
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

	Escolha:
	<form method="post" action="mvc?logica=Extrato">
		<table border="1">
			<tr>
				<th>Tabela</th>
				<td><select name="tabela">
						<c:forEach var="tabelas" items="${tabelas}">
							<option value="${tabelas.nome}">${tabelas.nome}</option>
						</c:forEach>
				</select></td>
				<td><input type="submit" name="acao" value="Listar" /></td>
			</tr>
		</table>
	</form>


	<br>
	<br>
	<c:if test="${acao == 'Listar'}">

		<form method="post" action="mvc?logica=Extrato">
			<table border="1">

				<!-- Primeira Linha -->
				<tr>
					<c:if test="${tabelaNome == 'area'}">
						<td></td>
					</c:if>

					<c:if test="${tabelaNome == 'subarea'}">
						<td></td>
						<td></td>
					</c:if>


					<c:if test="${tabelaNome != 'area' && tabelaNome != 'subarea'}">
						<td></td>
						<td></td>
						<td></td>
					</c:if>
					
					
					<td></td>
					<td colspan="5" align="center">Compilado</td>
					<td></td>
					<td colspan="8" align="center">Extratificado</td>
				</tr>

				<!-- Segunda Linha -->
				<tr>

					<c:if test="${tabelaNome == 'area'}">
						<td></td>
					</c:if>

					<c:if test="${tabelaNome == 'subarea'}">
						<td></td>
						<td></td>
					</c:if>


					<c:if test="${tabelaNome != 'area' && tabelaNome != 'subarea'}">
						<td></td>
						<td></td>
						<td></td>
					</c:if>

					<td></td>
					<td colspan="5" align="center"></td>
					<td></td>
					<td colspan="2" align="center">Aprovado</td>
					<td colspan="2" align="center">Concluido</td>
					<td colspan="2" align="center">Reprovado</td>
					<td colspan="2" align="center">Excluido</td>
				</tr>


				<!-- Terceira Linha -->
				<tr>

					<c:if test="${tabelaNome == 'area'}">
						<td align="center">Area</td>
					</c:if>

					<c:if test="${tabelaNome == 'subarea'}">
						<td align="center">Area</td>
						<td align="center">Subarea</td>
					</c:if>


					<c:if test="${tabelaNome != 'area' && tabelaNome != 'subarea'}">
						<td align="center">Nome</td>
						<td align="center">Area</td>
						<td align="center">Subarea</td>
					</c:if>

					<td></td>
					<td align="center">Total</td>
					<td align="center">Analise</td>
					<td align="center">Pendente</td>
					<td align="center">Concluido</td>
					<td align="center">Excluido</td>
					<td></td>
					<td align="center">No prazo</td>
					<td align="center">Atrasado</td>
					<td align="center">No prazo</td>
					<td align="center">Atrasado</td>
					<td align="center">No prazo</td>
					<td align="center">Atrasado</td>
					<td align="center">No prazo</td>
					<td align="center">Atrasado</td>
				</tr>


				<!-- Valores -->
				<c:forEach var="resumos" items="${resumos}">
					<tr>
						<c:if test="${tabelaNome == 'area'}">
							<td align="center">${resumos.area}</td>
						</c:if>

						<c:if test="${tabelaNome == 'subarea'}">
							<td align="center">${resumos.area}</td>
							<td align="center">${resumos.subArea}</td>
						</c:if>


						<c:if test="${tabelaNome != 'area' && tabelaNome != 'subarea'}">
							<td align="center">${resumos.nome}</td>
							<td align="center">${resumos.area}</td>
							<td align="center">${resumos.subArea}</td>
						</c:if>

						<td></td>
						<td align="center">${resumos.total}</td>
						<td align="center">${resumos.analise}</td>
						<td align="center">${resumos.pendente}</td>
						<td align="center">${resumos.concluida}</td>
						<td align="center">${resumos.excluida}</td>
						<td></td>
						<td align="center">${resumos.aprovadaPrazo}</td>
						<td align="center">${resumos.aprovadoAtrasada}</td>
						<td align="center">${resumos.concluidaPrazo}</td>
						<td align="center">${resumos.concluidaAtrasada}</td>
						<td align="center">${resumos.reprovadaPrazo}</td>
						<td align="center">${resumos.reprovadaAtrasada}</td>
						<td align="center">${resumos.excluidaPrazo}</td>
						<td align="center">${resumos.excluidaAtrasada}</td>
					</tr>
				</c:forEach>

			</table>
		</form>
	</c:if>
</body>
</html>