package mvc.logica;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AgendaDAO;
import modelo.Agenda;
import modelo.Usuario;
import util.Erro;

public class ListaAgendamento implements Logica {

	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		Erro erros = new Erro();
		String url = "/WEB-INF/jsp/agendamento.jsp";
		Integer dt_inicio;
		Integer dt_fim;
		Integer areaId = 0;
		Integer laboratorioId = 0;

		Usuario user = (Usuario) req.getSession().getAttribute("usuarioLogado");

		if (user == null || Usuario.totalListaUsuario() == 0
				|| !Usuario.existeUsuarioLista(user.getId())) {
			req.getSession().invalidate();
			url = "login.jsp";
		} else {

			// verificando e cast das informações
			areaId = (Integer) req.getSession().getAttribute("areaId");
			laboratorioId = (Integer) req.getSession().getAttribute("laboId");
			dt_inicio = (Integer) req.getSession().getAttribute("dt_inicio");
			dt_fim = (Integer) req.getSession().getAttribute("dt_fim");

			// buscando a lista de agendamento do banco e colocando na sessão

			List<Agenda> agendas = new AgendaDAO().getList(areaId,
					laboratorioId, dt_inicio, dt_fim);
			// Integer qtdAgenda = new AgendaDAO().contarItem(areaId,laboratorioId, dt_inicio, dt_fim);

			req.getSession().setAttribute("agendas", agendas);
			// req.getSession().setAttribute("qtd", qtdAgenda);
			req.getSession().setAttribute("areaId", areaId);
			req.getSession().setAttribute("laboId", laboratorioId);
			req.getSession().setAttribute("dt_inicio", dt_inicio);
			req.getSession().setAttribute("dt_fim", dt_fim);

			// JSONArray areas = new AreaDAO().getArea();
		}

		// req.setAttribute("areas", areas);
		req.getSession().setAttribute("erros", erros);
		return url;
	}
}
