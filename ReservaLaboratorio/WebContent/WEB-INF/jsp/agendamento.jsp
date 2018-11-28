<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<h1>Agenda</h1>
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


	<!-- AREA DA SELEÇÃO DA AREA E LABORATORIO -->
	<form method="post" action="mvc?logica=VerificarDatasAgendamento">

		<table>
		
			<tr>
				<td>ÁREA:</td>
				<td><select id="combo1" name="areaId" required><option>Selecione</option></select></td>
			</tr>

			<tr>
				<td>LABORATÓRIO:</td>
				<td><select id="combo2" name="laboratorioId" required><option>Selecione</option></select></td>
			</tr>

			<tr>
				<td>DATA INICIAL</td>
				<td><input type="date" name="dt_inicio"
					min="${sessionScope.dtmin}" max="${sessionScope.dtmax}" required></td>
			</tr>

			<tr>
				<td>DATA FINAL</td>
				<td><input type="date" name="dt_fim"
					min="${sessionScope.dtmin}" max="${sessionScope.dtmax}" required></td>
			</tr>

		</table>
		<input type="submit" name="bOK" value="BUSCAR" />
	</form>
<br><br><br>


<!-- AREA DA LISTAGEM DAS INFORMAÇÕES  -->
	<form method="post" action="mvc?logica=####" >
		<table border="1" style="width:60%" class="menu-sanfona">
		
			<tr bgcolor=#8B8682 style="color: #FFFFFF" >
				<td align="center" width="10%" >Área</td>
				<td align="center" width="15%" >Laboratório</td>
				<td align="center" width="15%" >Data</td>
				<td align="center" width="20%">Manhã</td>
				<td align="center" width="20%">Tarde</td>
				<td align="center" width="20%">Noite</td>
			</tr>

			<c:forEach var="agendas" items="${agendas}">
				<tr>
					<td align="center">${agendas.areaNome}</td>
					<td align="center">${agendas.laboratorioNome}</td>
					<td align="center"><fmt:formatDate pattern="dd/MM/yyyy" value="${agendas.date}" /></td>
					
					<td align="center">
						<c:if test="${agendas.turnoManha == 'Livre'}">
							<a href="mvc?logica=AlteraAgenda&turno=manha&acao=agendar&id=${agendas.id}">Livre</a>
						</c:if>
						<c:if test="${agendas.turnoManha != 'Livre' && agendas.turnoManha == sessionScope.usuarioLogado.id}">
							<a href="mvc?logica=AlteraAgenda&turno=manha&acao=excluir&id=${agendas.id}">Agendado</a>
						</c:if>
						<c:if test="${agendas.turnoManha != 'Livre' && agendas.turnoManha != sessionScope.usuarioLogado.id}">
							<!--Verificando se o usuario é um administrador  -->
							<c:if test="${sessionScope.usuarioLogado.perfil != null && sessionScope.usuarioLogado.perfil == 3}">
								<a href="mvc?logica=AlteraAgenda&turno=manha&acao=excluir&id=${agendas.id}">Ocupado</a>		
							</c:if>	
							<c:if test="${sessionScope.usuarioLogado.perfil != null && sessionScope.usuarioLogado.perfil != 3}">
								Ocupado		
							</c:if>
						
						</c:if>
						
					</td>
					
					<td align="center">
						<c:if test="${agendas.turnoTarde == 'Livre'}">
							<a href="mvc?logica=AlteraAgenda&turno=tarde&acao=agendar&id=${agendas.id}">Livre</a>
						</c:if>
						<c:if test="${agendas.turnoTarde != 'Livre' && agendas.turnoTarde == sessionScope.usuarioLogado.id}">
							<a href="mvc?logica=AlteraAgenda&turno=tarde&acao=excluir&id=${agendas.id}">Agendado</a>
						</c:if>
						<c:if test="${agendas.turnoTarde != 'Livre' && agendas.turnoTarde != sessionScope.usuarioLogado.id}">
							<!--Verificando se o usuario é um administrador  -->
							<c:if test="${sessionScope.usuarioLogado.perfil != null && sessionScope.usuarioLogado.perfil == 3}">
								<a href="mvc?logica=AlteraAgenda&turno=tarde&acao=excluir&id=${agendas.id}">Ocupado</a>		
							</c:if>	
							<c:if test="${sessionScope.usuarioLogado.perfil != null && sessionScope.usuarioLogado.perfil != 3}">
								Ocupado		
							</c:if>
						</c:if>
					</td>
					
					<td align="center">
						<c:if test="${agendas.turnoNoite == 'Livre'}">
							<a href="mvc?logica=AlteraAgenda&turno=noite&acao=agendar&id=${agendas.id}">Livre</a>
						</c:if>
						<c:if test="${agendas.turnoNoite != 'Livre' && agendas.turnoNoite == sessionScope.usuarioLogado.id}">
							<a href="mvc?logica=AlteraAgenda&turno=noite&acao=excluir&id=${agendas.id}">Agendado</a>
						</c:if>
						<c:if test="${agendas.turnoNoite != 'Livre' && agendas.turnoNoite != sessionScope.usuarioLogado.id}">
							<!--Verificando se o usuario é um administrador  -->
							<c:if test="${sessionScope.usuarioLogado.perfil != null && sessionScope.usuarioLogado.perfil == 3}">
								<a href="mvc?logica=AlteraAgenda&turno=noite&acao=excluir&id=${agendas.id}">Ocupado</a>		
							</c:if>	
							<c:if test="${sessionScope.usuarioLogado.perfil != null && sessionScope.usuarioLogado.perfil != 3}">
								Ocupado		
							</c:if>
						</c:if>
					</td>
					
					
				</tr>
			</c:forEach>
		</table>

	</form>


	<script src="https://code.jquery.com/jquery-2.2.0.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		//Array de Categoria em JSON puro
		var categorias = ${areasJason}
		//Array de Subcategoria em JSON puro
		var subCategorias = ${laboratoriosJason}

		$(document).ready(function() {
			//Percorre o array de categorias para popular o combo de categorias
			$.each(categorias, function(i, categoria) {
				//Faz o append (adiciona) cada um dos itens do array como options do select de id combo1
				$("#combo1").append($('<option>', {
					//neste caso o value do option vai ser a pripriedade Id (é case sensitive, então cuide da nomenclatura)
					value : categoria.Id,
					//no text estou usando a propriedade Categoria dos objetos dentro do array categoria (case sensitive também)
					text : categoria.area_nome
				}))
			})
			//Vai ser acionado cada vez que o combo1 tracar de item selecionado
			$("#combo1").change(function() {
				//Pegando o novo valor selecionado no combo
				var categoria = $(this).val()
				carregaCombo2(categoria);

			})

			//Definir o item selecionado no carregamento da página
			$("#combo1").val(Selecione)
			//carregar o combo2 baseado na categoria selecionada
			carregaCombo2(2)
			//Selecionar a subcategoria passando o id dela
			$("#combo2").val(3)
		})

		function carregaCombo2(categoria) {
			//Fazer um filtro dentro do array de categorias com base no Id da Categoria selecionada no combo1
			var subCategoriasFiltradas = $.grep(subCategorias, function(e) {
				return e.area_id == categoria;
			});
			//Faz o append (adiciona) cada um dos itens do array filtrado no combo2
			$("#combo2").html('<option>Selecione</option>');
			$.each(subCategoriasFiltradas, function(i, subcategoria) {
				$("#combo2").append($('<option>', {
					value : subcategoria.Id, //Id do objeto subcategoria
					text : subcategoria.labo_nome
				//Nome da Subcategoria
				}))
			})
		}
	</script>



</body>
</html>