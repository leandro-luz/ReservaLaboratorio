package mvc.logica;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import modelo.Usuario;
import util.Erro;
import util.FormatarDatas;
import util.RegistroEvento;
import util.Senha;
import util.ThreadRetirarUsuario;

public class Login extends HttpServlet implements Logica {

	private static final long serialVersionUID = 7462441425463565431L;

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Erro erros = new Erro();
		String url = "login.jsp";
		String senha = null;

		if (!req.getParameter("bOK").equalsIgnoreCase("null")) {

			String matricula = req.getParameter("matricula");
			String senhaTxt = req.getParameter("senha");
			
			if (matricula == null || matricula.isEmpty()) {
				erros.add("Matricula não foi informada!");
			}
			if (senhaTxt == null || senhaTxt.isEmpty()) {
				erros.add("Senha não informada!");
			}else {
				senha = Senha.criptografar(senhaTxt);
			}

			if (!erros.isExisteErros()) {
				Usuario usuario = new Usuario();
				usuario.setMatricula(matricula);
				usuario.setNome(null);
				usuario.setSenha(senha);
				usuario.setPerfil(1);
				UsuarioDAO dao = new UsuarioDAO();
				Usuario user = dao.existeUsuario(usuario);
				// verificar se existe o cadastro do usuario
				if (user != null) {
					// Verificar se o usuario está ativo
					if (user.isAtivo()) {

						// verificar se a senha cadastrada são iguais
						if (user.getSenha().equalsIgnoreCase(senha)) {
							// retira a senha da sessão
							user.setSenha("******");
							// incluir o usuario na lista dos logados
							Usuario.setUsuarioLista(user.getId(), req.getSession());
							// incluir os dados do usuario na sessão
							req.getSession().setAttribute("usuarioLogado", user);
							// incrementa o acesso e colocar a data do ultimo acesso
							dao.setAcesso(user);
							// verificar se o email já foi confirmado
							if (user.isMailConfirm()) {
								url = "mvc?logica=IniciaTarefaLogica&new=new&url=/WEB-INF/jsp/agendamento.jsp";
							} else {
								url = "mvc?logica=IniciaTarefaLogica&url=/WEB-INF/jsp/alterarSenha.jsp";
							}
						
						} else {
							erros.add("Senha inválida!");
						}

					} else {
						erros.add("Favor procurar o administrador para reativar o seu login.");
					}

				} else {
					erros.add("Usuário não cadastrado ou senha errada!");
				}
			}
		} else {
			Usuario user = (Usuario) req.getSession().getAttribute("usuarioLogado");
			if (user != null) {
				Usuario userr = new Usuario();
				userr = (Usuario) req.getSession().getAttribute("usuarioLogado");
				Usuario.removeUsuarioLista(userr.getId());
			}else {
				req.getSession().invalidate();
			}
			
		}

		if (ThreadRetirarUsuario.getInstancia() == 0) {
			ThreadRetirarUsuario.setInstancia();
			ThreadRetirarUsuario t1 = new ThreadRetirarUsuario();
			Thread thread = new Thread(t1);
			thread.start();
			String texto = "Reinicio do sistema com o primeiro usuario no sistema - : " + FormatarDatas.dataAtual();
			RegistroEvento.registrar(texto);
		}
		

		req.getSession().setAttribute("mensagens", erros);
		return url;
	}
}