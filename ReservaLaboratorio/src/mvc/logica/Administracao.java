package mvc.logica;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AreaDAO;
import dao.LaboratorioDAO;
import dao.PerfilDAO;
import dao.UsuarioDAO;
import modelo.Area;
import modelo.Laboratorio;
import modelo.Perfil;
import modelo.Usuario;
import util.Aviso;
import util.Erro;

public class Administracao implements Logica {
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		Erro erros = new Erro();
		Aviso avisos = new Aviso();
		String acao;
		String tabelaNome;
		String listarAcao;
		String id;
		String tabela;
		String update;
		
		String url = "mvc?logica=IniciaTarefaLogica&new=new&url=/WEB-INF/jsp/administrador.jsp";

		Usuario user = (Usuario) req.getSession().getAttribute("usuarioLogado");

		if (user == null || Usuario.totalListaUsuario() == 0
				|| !Usuario.existeUsuarioLista(user.getId())) {
			req.getSession().invalidate();
			url = "login.jsp";
		} else {
			acao = req.getParameter("acao");
			tabelaNome = req.getParameter("tabela");
			listarAcao = req.getParameter("Listaracao");
			id = req.getParameter("id");
			tabela = req.getParameter("tabela");
			update = req.getParameter("update");
			
			if (acao != null && acao.equalsIgnoreCase("Listar")) {
				if (listarAcao != null) {

					// Ativar e desativar o item
					if (listarAcao.equalsIgnoreCase("ativarDesativar")) {
						ativarDesativar(tabela, id, req);
					}
					if (listarAcao.equalsIgnoreCase("alterar")) {
						alterar(tabela, id, req);
						acao = "alterar";
					}
					if (listarAcao.equalsIgnoreCase("excluir")) {

						if (!excluirItem(tabela, id)) {
							erros.add("Não poder ser exluído pois existem registros vinculados");
						}
						acao = "Listar";
					}

				}

			}

			// Atualizar os nomes
			if (update != null && update.equalsIgnoreCase("Salvar")) {
				if (!update(tabela, req)) {
					erros.add("O novo nome para o item já existe");
				}
				acao = "Listar";
			}

			// Novo item
			if (update != null && update.equalsIgnoreCase("Gravar")) {
				if (!novoItem(tabela, req)) {
					erros.add("Este novo registro já existe");
				}
				acao = "Listar";
			}

			req.setAttribute("acao", acao);
			req.setAttribute("tabelaNome", tabelaNome);
		}

		req.setAttribute("mensagens", erros);
		req.setAttribute("noticias", avisos);
		return url;

	}

	private boolean excluirItem(String tabela, String id) {
		boolean resultado = false;
		try {
			if (tabela.equalsIgnoreCase("area")) {
				AreaDAO dao = new AreaDAO();
				Integer quantidade = dao.contarItem(Integer.parseInt(id));

				// verificar se existe registros na tabela
				if (quantidade == 0) {
					dao.excluir(Integer.parseInt(id));
					Area.setDataAtualizacao();
					resultado = true;
				}
			}
			if (tabela.equalsIgnoreCase("laboratorio")) {
				LaboratorioDAO dao = new LaboratorioDAO();
				Integer quantidade = dao.contarItem(Integer.parseInt(id));

				// verificar se existe registros na tabela
				if (quantidade == 0) {
					dao.excluir(Integer.parseInt(id));
					Laboratorio.setDataAtualizacao();
					resultado = true;
				}
			}

			if (tabela.equalsIgnoreCase("usuario")) {
				UsuarioDAO dao = new UsuarioDAO();
				Integer quantidade = dao.contarItem(id);

				// verificar se existe registros na tabela
				if (quantidade == 0) {
					dao.excluir(Integer.parseInt(id));
					Usuario.setDataAtualizacao();
					resultado = true;
				}
			}
			
			if (tabela.equalsIgnoreCase("perfil")) {
				PerfilDAO perfilDAO = new PerfilDAO();
				Integer quantidade = perfilDAO.contarItem(Integer.parseInt(id));

				// verificar se existe usuarios com este perfil
				if (quantidade == 0) {
					perfilDAO.excluir(Integer.parseInt(id));
					Perfil.setDataAtualizacao();
					resultado = true;
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}

	private boolean novoItem(String tabela, HttpServletRequest req) {
		boolean resultado = false;

		try {
			if (tabela.equalsIgnoreCase("area")) {
				String nome = req.getParameter("nome");

				if (!existeItem(tabela, nome, req)) {
					AreaDAO.novoItem(nome);
					Area.setDataAtualizacao();
					resultado = true;
				}
			}
			if (tabela.equalsIgnoreCase("laboratorio")) {
				String nome = req.getParameter("nome");

				if (!existeItem(tabela, nome, req)) {
					Integer areaId = Integer.parseInt(req.getParameter("areaId"));
					LaboratorioDAO.novoItem(nome, areaId);
					Laboratorio.setDataAtualizacao();
					resultado = true;
				}
			}
			if (tabela.equalsIgnoreCase("perfil")) {
				String nome = req.getParameter("nome");

				if (!existeItem(tabela, nome, req)) {
					PerfilDAO.novoItem(nome);
					Perfil.setDataAtualizacao();
					resultado = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	private boolean existeItem(String tabela, String nome,
			HttpServletRequest req) {
		boolean resultado = false;
		Integer teste;

		switch (tabela) {
			case "area" :
				teste = 0;
				List<Area> areas = new ArrayList<>();
				areas = (List<Area>) req.getSession().getAttribute("areas");

				for (Area area : areas) {
					if (area.getNome().equalsIgnoreCase(nome)) {
						teste++;
					}
				}
				if (teste == 0) {
					resultado = false;
				} else {
					resultado = true;
				}
				break;

			case "laboratorio" :
				teste = 0;
				List<Laboratorio> laboratorios = new ArrayList<>();
				laboratorios = (List<Laboratorio>) req.getSession().getAttribute("laboratorios");

				for (Laboratorio laboratorio : laboratorios) {
					if (laboratorio.getNome().equalsIgnoreCase(nome)) {
						teste++;
					}
				}
				if (teste == 0) {
					resultado = false;
				} else {
					resultado = true;
				}
				break;

		}
		return resultado;
	}

	private boolean update(String tabela, HttpServletRequest req) {
		boolean resultado = false;
		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String matricula = req.getParameter("matricula");
		String email = req.getParameter("email");
		String perfil = req.getParameter("perfil");
		Usuario user = retonarUsuario(req, id);

		try {
			if (tabela.equalsIgnoreCase("area")) {

				if (!existeItem(tabela, nome, req)) {
					Area newArea = new Area();
					newArea.setId(Integer.parseInt(id));
					newArea.setNome(nome);
					AreaDAO dao = new AreaDAO();
					dao.update(newArea);
					Area.setDataAtualizacao();
					resultado = true;
				}
			}

			if (tabela.equalsIgnoreCase("laboratorio")) {

				if (!existeItem(tabela, nome, req)) {
					Laboratorio newLaboratorio = new Laboratorio();
					newLaboratorio.setId(Integer.parseInt(id));
					newLaboratorio.setNome(nome);
					LaboratorioDAO dao = new LaboratorioDAO();
					dao.update(newLaboratorio);
					Laboratorio.setDataAtualizacao();
					resultado = true;
				}
			}

			if (tabela.equalsIgnoreCase("usuario")) {

				if (matricula.isEmpty()) {
					matricula = user.getMatricula();
				}
				if (nome.isEmpty()) {
					nome = user.getNome();
				}
				if (email.isEmpty()) {
					email = user.getEmail();
				}

				if (perfil.equalsIgnoreCase("Selecione")) {
					perfil = String.valueOf(user.getPerfil());
				}

				Usuario newUsuario = new Usuario();
				newUsuario.setId(Integer.parseInt(id));
				newUsuario.setNome(nome);
				newUsuario.setEmail(email);
				newUsuario.setMatricula(matricula);
				newUsuario.setPerfil(Integer.parseInt(perfil));

				UsuarioDAO dao = new UsuarioDAO();
				dao.update(newUsuario);
				Usuario.setDataAtualizacao();
				resultado = true;

			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return resultado;
	}

	@SuppressWarnings("unchecked")
	private Usuario retonarUsuario(HttpServletRequest req, String id) {
		List<Usuario> usuarios = new ArrayList<>();
		Usuario us = new Usuario();
		usuarios = (List<Usuario>) req.getSession().getAttribute("usuarios");
		Integer id2 = Integer.parseInt(id);
		for (Usuario usuario : usuarios) {
			if (usuario.getId() == id2) {
				us.setMatricula(usuario.getMatricula());
				us.setNome(usuario.getNome());
				us.setEmail(usuario.getEmail());
				us.setPerfil(usuario.getPerfil());
			}
		}
		return us;
	}

	@SuppressWarnings("unchecked")
	private void alterar(String tabela, String id, HttpServletRequest req) {
		int id2 = Integer.parseInt(id);
		Object oldDados = null;

		if (tabela.equalsIgnoreCase("area")) {
			List<Area> areas = new ArrayList<>();
			areas = (List<Area>) req.getSession().getAttribute("areas");
			oldDados = new Area();
			// percorre a lista para encontrar o id
			for (Area area : areas) {
				if (area.getId() == id2) {
					((Area) oldDados).setId(area.getId());
					((Area) oldDados).setNome(area.getNome());
				}
			}
		}

		if (tabela.equalsIgnoreCase("laboratorio")) {
			List<Laboratorio> laboratorios = new ArrayList<>();
			laboratorios = (List<Laboratorio>) req.getSession().getAttribute("laboratorios");
			oldDados = new Laboratorio();
			// percorre a lista para encontrar o id
			for (Laboratorio laboratorio : laboratorios) {
				if (laboratorio.getId() == id2) {
					((Laboratorio) oldDados).setId(laboratorio.getId());
					((Laboratorio) oldDados).setNome(laboratorio.getNome());
				}
			}
		}

		if (tabela.equalsIgnoreCase("usuario")) {
			List<Usuario> usuarios = new ArrayList<>();
			usuarios = (List<Usuario>) req.getSession()
					.getAttribute("usuarios");
			oldDados = new Usuario();
			// percorre a lista para encontrar o id
			for (Usuario usuario : usuarios) {
				if (usuario.getId() == id2) {
					((Usuario) oldDados).setId(usuario.getId());
					((Usuario) oldDados).setMatricula(usuario.getMatricula());
					((Usuario) oldDados).setNome(usuario.getNome());
					((Usuario) oldDados).setEmail(usuario.getEmail());
					((Usuario) oldDados).setPerfil(usuario.getPerfil());
				}
			}
		}
		req.getSession().setAttribute("oldDados", oldDados);
	}

	private void ativarDesativar(String tabela, String id,
			HttpServletRequest req) {
		try {
			switch (tabela) {
				case "area" :
					AreaDAO areaDAO = new AreaDAO();
					areaDAO.alterarStatus(id);
					Area.setDataAtualizacao();
					break;
				case "usuario" :
					UsuarioDAO usuarioDAO = new UsuarioDAO();
					usuarioDAO.alterarStatus(id);
					Usuario.setDataAtualizacao();
					break;
				case "perfil" :
					PerfilDAO perfilDAO = new PerfilDAO();
					perfilDAO.alterarStatus(id);
					Perfil.setDataAtualizacao();
					break;
				case "laboratorio" :
					LaboratorioDAO laboratorioDAO = new LaboratorioDAO();
					laboratorioDAO.alterarStatus(id);
					Laboratorio.setDataAtualizacao();
					break;

			}
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		req.getSession().setAttribute("start", null);

	}

}
