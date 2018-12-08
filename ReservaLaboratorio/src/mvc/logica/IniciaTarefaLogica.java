package mvc.logica;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import dao.AreaDAO;
import dao.LaboratorioDAO;
import dao.PerfilDAO;
import dao.TabelaDAO;
import dao.UsuarioDAO;
import modelo.Agenda;
import modelo.Area;
import modelo.Laboratorio;
import modelo.Perfil;
import modelo.Tabela;
import modelo.Usuario;
import util.Aviso;
import util.Erro;

public class IniciaTarefaLogica implements Logica {

	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String url = req.getParameter("url");
		String novo = req.getParameter("new");
		Usuario user = (Usuario) req.getSession().getAttribute("usuarioLogado");
		Erro erros = new Erro();
		Aviso avisos = new Aviso();

		if ((user == null || Usuario.totalListaUsuario() == 0 || !Usuario.existeUsuarioLista(user.getId()))
				&&(!url.equalsIgnoreCase("/WEB-INF/jsp/novoUsuario.jsp"))) {
			req.getSession().invalidate();
			url = "login.jsp";
		} else {
			if (req.getSession().getAttribute("start") == null) {
				atualizarTodas(req, user);
			} else {
				atualizarParcial(req, user);
			}
			if(novo != null && novo.equalsIgnoreCase("new")) {
				req.getSession().setAttribute("mensagens", erros);
				req.getSession().setAttribute("noticias", avisos);
			}
			
		}
		return url;

	}

	private void atualizarParcial(HttpServletRequest req, Usuario u) {
		
		if (req.getSession().getLastAccessedTime() < Area.getDataAtualizacao()) {
			atualizarArea(req);
			atualizarAreaJason(req);
		}
		if (req.getSession().getLastAccessedTime() < Laboratorio.getDataAtualizacao()) {
			atualizarLaboratorio(req);
			atualizarLaboratorioJason(req);
		}
		if (req.getSession().getLastAccessedTime() < Agenda.getDataAtualizacao()) {
			atualizarAgenda(req);
		}
		if (req.getSession().getLastAccessedTime() < Tabela.getDataAtualizacao()) {
			atualizarTabela(req);
		}
		if (req.getSession().getLastAccessedTime() < Perfil.getDataAtualizacao()) {
			atualizarPerfil(req);
		}
		
		if (u != null && u.getPerfil() == 3) {
			if (req.getSession().getLastAccessedTime() < Usuario.getDataAtualizacao()) {
				atualizarUsuario(req);
			}
			atualizarLogados(req);
		}
	}

	
	private void atualizarTodas(HttpServletRequest req, Usuario u) {
		atualizarAreaJason(req);
		atualizarLaboratorioJason(req);
		atualizarArea(req);
		atualizarLaboratorio(req);
		atualizarAgenda(req);
		atualizarTabela(req);
		atualizarData(req);

		//se não estiver logado ou for administrador
		if (u != null && u.getPerfil() == 3) {
			atualizarUsuario(req);
			atualizarPerfil(req);
			atualizarLogados(req);
		}
		//seta para 1 como atualizado
		req.getSession().setAttribute("start", "1");
	}
	
	private void atualizarPerfil(HttpServletRequest req) {
		List<Perfil> perfis = new PerfilDAO().getList();
		req.getSession().setAttribute("perfis", perfis);
	}

	private void atualizarData(HttpServletRequest req) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		String min = String.valueOf(sdf.format(c.getTime()));
		req.getSession().setAttribute("dtmin", min);

		c.set(Calendar.YEAR, (c.get(Calendar.YEAR) + 2));
		String max = String.valueOf(sdf.format(c.getTime()));
		req.getSession().setAttribute("dtmax", max);
	}

	private void atualizarLogados(HttpServletRequest req) {
		List<Usuario> logados = new Usuario().getLogados();
		req.getSession().setAttribute("logados", logados);
	}

	private void atualizarAreaJason(HttpServletRequest req) {
		JSONArray areasJason = new AreaDAO().getArea();
		req.getSession().setAttribute("areasJason", areasJason);
	}

	private void atualizarLaboratorioJason(HttpServletRequest req) {
		JSONArray laboratoriosJason = new LaboratorioDAO().getLaboratorio();
		req.getSession().setAttribute("laboratoriosJason", laboratoriosJason);
	}

	private void atualizarArea(HttpServletRequest req) {
		List<Area> areas = new AreaDAO().getList();
		req.getSession().setAttribute("areas", areas);
	}

	private void atualizarLaboratorio(HttpServletRequest req) {
		List<Laboratorio> laboratorios = new LaboratorioDAO().getList();
		req.getSession().setAttribute("laboratorios", laboratorios);
	}

	private void atualizarAgenda(HttpServletRequest req) {
		//List<Agenda> agendas = new AgendaDAO().getList();
		//req.getSession().setAttribute("agendas", agendas);
	}

	private void atualizarUsuario(HttpServletRequest req) {
		List<Usuario> usuarios = new UsuarioDAO().getList();
		req.getSession().setAttribute("usuarios", usuarios);
	}

	private void atualizarTabela(HttpServletRequest req) {
		List<Tabela> tabelas = new TabelaDAO().getLista();
		req.getSession().setAttribute("tabelas", tabelas);
	}
}
