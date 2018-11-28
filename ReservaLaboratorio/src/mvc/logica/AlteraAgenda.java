package mvc.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AgendaDAO;
import modelo.Usuario;

public class AlteraAgenda implements Logica {

	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String url = "mvc?logica=ListaAgendamento";
		String texto = null;

		Usuario user = (Usuario) req.getSession().getAttribute("usuarioLogado");

		if (user == null || Usuario.totalListaUsuario() == 0
				|| !Usuario.existeUsuarioLista(user.getId())) {
			req.getSession().invalidate();
			url = "login.jsp";
		} else {
			String turno = req.getParameter("turno");
			String acao = req.getParameter("acao");
			Integer id = Integer.parseInt(req.getParameter("id"));

			AgendaDAO dao = new AgendaDAO();
			
			switch (acao) {
				case "agendar" :
					texto = String.valueOf(user.getId());
				break;

				case "excluir" :
					texto = "Livre";
				break;
			}

			switch (turno) {
				case "manha" :
					dao.alterarTurnoManha(texto, id);
					break;

				case "tarde" :
					dao.alterarTurnoTarde(texto, id);
					break;

				case "noite" :
					dao.alterarTurnoNoite(texto, id);
					break;
			}
			
			dao.registro(String.valueOf(user.getId()),id,turno,acao);
		}
		return url;
	}
}
