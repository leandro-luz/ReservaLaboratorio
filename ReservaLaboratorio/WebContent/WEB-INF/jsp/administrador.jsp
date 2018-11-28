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
	<h1>Administração</h1>
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
	<form method="post" action="mvc?logica=Administracao">
		<table border="1">
			<tr>
				<th>TABELA</th>
				<td><select name="tabela">
						<c:forEach var="tabelas" items="${tabelas}">
							<option value="${tabelas.nome}">${tabelas.nome}</option>
						</c:forEach>
				</select></td>
				<td><input type="submit" name="acao" value="Listar" /></td>
				<td><input type="submit" name="acao" value="Novo" /></td>
			</tr>
		</table>
	</form>


	<br>
	<br>
	<c:if test="${acao == 'Listar'}">
	Lista de ${tabelaNome}

		<form method="post" action="mvc?logica=Administracao">
			<table border="1">
				<tr>
					<td>Id</td>
					<c:if test="${tabelaNome == 'laboratorio'}">
						<td>ÁREA</td>
					</c:if>
					<c:if test="${tabelaNome != 'usuario'}">
						<td>DESCRIÇÃO</td>
					</c:if>
					<c:if test="${tabelaNome == 'usuario'}">
						<td>MATRICULA</td>
						<td>NOME</td>
						<td>EMAIL</td>
						<td>PERFIL</td>
						<td>ACESSOS</td>
						<td>ÚLTIMO ACESSO</td>
					</c:if>
					<td>ATIVO</td>
				</tr>


				<c:if test="${tabelaNome == 'area'}">
					<c:forEach var="area" items="${areas}">
						<tr>
							<td>${area.id}</td>
							<td>${area.nome}</td>
							<td>${area.ativo}</td>
							<td><a
								href="mvc?logica=Administracao&acao=Listar&Listaracao=alterar&tabela=area&id=${area.id}">Alterar</a></td>
							<td><a
								href="mvc?logica=Administracao&acao=Listar&Listaracao=ativarDesativar&tabela=area&id=${area.id}">Ativar/Desativar</a></td>
							<td><a
								href="mvc?logica=Administracao&acao=Listar&Listaracao=excluir&tabela=area&id=${area.id}">Excluir</a></td>
						
						</tr>
					</c:forEach>
				</c:if>
				
				<c:if test="${tabelaNome == 'perfil'}">
					<c:forEach var="perfis" items="${perfis}">
						<tr>
							<td>${perfis.id}</td>
							<td>${perfis.nome}</td>
							<td>${perfis.ativo}</td>
							<td><a
								href="mvc?logica=Administracao&acao=Listar&Listaracao=alterar&tabela=perfila&id=${perfis.id}">Alterar</a></td>
							<td><a
								href="mvc?logica=Administracao&acao=Listar&Listaracao=ativarDesativar&tabela=perfil&id=${perfis.id}">Ativar/Desativar</a></td>
							<td><a
								href="mvc?logica=Administracao&acao=Listar&Listaracao=excluir&tabela=perfil&id=${perfis.id}">Excluir</a></td>
						
						</tr>
					</c:forEach>
				</c:if>
				
				<c:if test="${tabelaNome == 'laboratorio'}">
					<c:forEach var="laboratorios" items="${laboratorios}">
						<tr>
							<td>${laboratorios.id}</td>
							<td>${laboratorios.areaNome}</td>
							<td>${laboratorios.nome}</td>
							<td>${laboratorios.ativo}</td>
							<td><a
								href="mvc?logica=Administracao&acao=Listar&Listaracao=alterar&tabela=laboratorio&id=${laboratorios.id}">Alterar</a></td>
							<td><a
								href="mvc?logica=Administracao&acao=Listar&Listaracao=ativarDesativar&tabela=laboratorio&id=${laboratorios.id}">Ativar/Desativar</a></td>
							<td><a
								href="mvc?logica=Administracao&acao=Listar&Listaracao=excluir&tabela=laboratorio&id=${laboratorios.id}">Excluir</a></td>
						</tr>
					</c:forEach>
				</c:if>
				
				<c:if test="${tabelaNome == 'usuario'}">
					<c:forEach var="usuario" items="${usuarios}">
						<tr>
							<td>${usuario.id}</td>
							<td>${usuario.matricula}</td>
							<td>${usuario.nome}</td>
							<td>${usuario.email}</td>
							<td>${usuario.perfilNome}</td>
							<td>${usuario.acessos}</td>
							<td>${usuario.ultimoAcesso}</td>
							<td>${usuario.ativo}</td>
							<td><a
								href="mvc?logica=Administracao&acao=Listar&Listaracao=alterar&tabela=usuario&id=${usuario.id}">Alterar</a></td>
							<td><a
								href="mvc?logica=Administracao&acao=Listar&Listaracao=ativarDesativar&tabela=usuario&id=${usuario.id}">Ativar/Desativar</a></td>
							<td><a
								href="mvc?logica=Administracao&acao=Listar&Listaracao=excluir&tabela=usuario&id=${usuario.id}">Excluir</a></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</form>
	</c:if>


	<c:if test="${acao == 'alterar'}">
	Lista de ${tabelaNome}

		<form method="post" action="mvc?logica=Administracao">
			<table border="1">
				<!-- Titulo da tabela -->
				<tr>
					<td>ID</td>
					<c:if test="${tabelaNome != 'usuario'}">
						<td>DESCRIÇÃO</td>
					</c:if>
					<c:if test="${tabelaNome == 'usuario'}">
						<td>MATRICULA</td>
						<td>NOME</td>
						<td>EMAIL</td>
						<td>PERFIL</td>
					</c:if>
				</tr>
				<!-- Valores da tabela -->
				<c:if test="${tabelaNome == 'area'}">
				<input type="hidden" name="tabela" value= "${tabelaNome}"/>
					<tr>
						<td> <input type="text" name="id" value="${sessionScope.oldDados.id}" readonly="readonly" /> </td>
						<td><input type="text" name="nome" placeholder="${sessionScope.oldDados.nome}"></td>
						<td><input type="submit" name="update" value="Salvar" /></td>
					</tr>
				</c:if>
				<c:if test="${tabelaNome == 'laboratorio'}">
				<input type="hidden" name="tabela" value= "${tabelaNome}"/>
					<tr>
						<td> <input type="text" name="id" value="${sessionScope.oldDados.id}" readonly="readonly" /> </td>
						<td><input type="text" name="nome" placeholder="${sessionScope.oldDados.nome}"></td>
							<td><input type="submit" name="update" value="Salvar" /></td>
					</tr>
				</c:if>
				
				<c:if test="${tabelaNome == 'usuario'}">
				<input type="hidden" name="tabela" value= "${tabelaNome}"/>
					<tr>
						<td><input type="text" name="id" value="${sessionScope.oldDados.id}" readonly="readonly" /> </td>
						<td><input type="text" name="matricula"	placeholder="${sessionScope.oldDados.matricula}"></td>
						<td><input type="text" name="nome" placeholder="${sessionScope.oldDados.nome}"></td>
						<td><input type="text" name="email" placeholder="${sessionScope.oldDados.email}"></td>
						<td><select name="perfil">
								<option>Selecione</option>
								<c:forEach var="perfis" items="${perfis}">
									<option value="${perfis.id}">${perfis.nome}</option>
								</c:forEach>
						</select></td>
						<td><input type="submit" name="update" value="Salvar" /></td>
					</tr>
				</c:if>
			</table>
		</form>

	</c:if>


<c:if test="${acao == 'Novo'}">

		<form method="post" action="mvc?logica=Administracao">
			<table border="1">
				<!-- Titulo da tabela -->
				<tr>
					<c:if test="${tabelaNome == 'usuario'}">
						<td>Favor cadastrar pelo sistema</td>
					</c:if>
					<c:if test="${tabelaNome == 'perfil'}">
						<td>Favor solicitar ao desenvolver do sistema</td>
					</c:if>
				</tr>
				<!-- Valores da tabela -->
				<c:if test="${tabelaNome == 'area'}">
				<input type="hidden" name="tabela" value= "${tabelaNome}"/>
					<tr>
						<td>DESCRIÇÃO</td>
					</tr>
					<tr>
						<td><input type="text" name="nome" placeholder="nome" required></td>
						<td><input type="submit" name="update" value="Gravar" /></td>
					</tr>
				</c:if>
				
				<c:if test="${tabelaNome == 'laboratorio'}">
				<input type="hidden" name="tabela" value= "${tabelaNome}"/>
					<tr>
						<td>ÁREA:</td>
						<td>DESCRIÇÃO:</td>
					</tr>
					<tr>
					
						<td><select id="combo1" name="areaId" required><option>Selecione</option></select></td>
						<td><input type="text" name="nome" placeholder="nome" required></td>
						<td><input type="submit" name="update" value="Gravar" /></td>
					</tr>
				
				</c:if>
			</table>
		</form>

	</c:if>



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