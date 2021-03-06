package mvc.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import modelo.Usuario;
import util.Aviso;
import util.Erro;
import util.Senha;

public class AlterarSenha implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String url = "mvc?logica=IniciaTarefaLogica&new=new&url=/WEB-INF/jsp/dadosUsuario.jsp";
		Erro erros = new Erro();
		Aviso avisos = new Aviso();

		Usuario user = (Usuario) req.getSession().getAttribute("usuarioLogado");
		
		if (user == null || Usuario.totalListaUsuario() == 0 || !Usuario.existeUsuarioLista(user.getId())) {
			req.getSession().invalidate();
			url = "login.jsp";
		} else {
			if (req.getParameter("bOK") != null) {
				String matricula = user.getMatricula();
				String senhaAntiga = Senha.criptografar(req.getParameter("senhaAntiga"));
				String senhaTxt1 = req.getParameter("senhaNova1");
				String senhaTxt2 = req.getParameter("senhaNova2");
				
				String senha1 = Senha.criptografar(senhaTxt1);
				String senha2 = Senha.criptografar(senhaTxt2);

				Usuario usuario = new Usuario();
				usuario.setMatricula(matricula);
				usuario.setSenha(senhaAntiga);
				UsuarioDAO dao = new UsuarioDAO();
				Usuario u = dao.existeUsuario(usuario);
				if (u != null) {
					u.setSenha(senha1);
					
					if (!u.getSenha().equalsIgnoreCase(senha2)) {
						erros.add("As senhas não são identicas!");
					}
						
					if(!Senha.verificarFormato(senhaTxt1)) {
						erros.add("A senha não está no formato informado!");
					}
						
					if (!erros.isExisteErros()) {
						u.setMatricula(matricula);
						dao.alterarSenha(u);
						avisos.add("Senha alterada com sucesso!");
					}else {
						url = "login.jsp";
					}
					
				} else {
					erros.add("Usuário não cadastrado ou senha errada!");
					url = "mvc?logica=IniciaTarefaLogica&url=/WEB-INF/jsp/alterarSenha.jsp";
				}
			}
		}
		req.setAttribute("mensagens", erros);
		req.setAttribute("noticias", avisos);
		return url;
	}
}
