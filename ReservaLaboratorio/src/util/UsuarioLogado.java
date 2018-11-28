package util;

import javax.servlet.http.HttpServletRequest;

import modelo.Usuario;

public class UsuarioLogado {

	public static void usuarioLogado(HttpServletRequest request) {
		request.getSession().setAttribute("qtdUsuariosLogados", Usuario.totalListaUsuario());
	}

}
