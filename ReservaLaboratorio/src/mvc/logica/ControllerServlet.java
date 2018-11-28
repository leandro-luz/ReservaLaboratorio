package mvc.logica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.RegistroEvento;
import util.UsuarioLogado;

@WebServlet("/mvc")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = -638464686125881039L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parametro = request.getParameter("logica");
		String url = request.getParameter("url");
		String nomeDaClasse = "mvc.logica." + parametro;
		RegistroEvento.atividades(request, parametro, url);	
		UsuarioLogado.usuarioLogado(request);
		
		try {
			Class<?> classe = Class.forName(nomeDaClasse);
			Logica logica = (Logica) classe.newInstance();
			String pagina = logica.executa(request, response);
			request.getRequestDispatcher(pagina).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
