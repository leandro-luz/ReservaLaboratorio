package mvc.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import modelo.Usuario;
import util.Aviso;
import util.Erro;
import util.Senha;

public class AdicionaUsuarioLogica implements Logica {
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Erro erros = new Erro();
		Aviso avisos = new Aviso();
		String url = "login.jsp";
		if (req.getParameter("bOK").equalsIgnoreCase("Salvar")) {
			// pegando os parametros do request
			String matricula = req.getParameter("matricula");
			String nome = req.getParameter("nome");
			String email = req.getParameter("email");
			String senhaTxt1 = req.getParameter("senha1");
			String senhaTxt2 = req.getParameter("senha2");
			
			
			if(!Senha.verificarFormato(senhaTxt1)) {
				erros.add("A senha não está no formato informado!");
			}
			
			if(matricula.length()<8) {
				erros.add("A matricula não tem 8 caracteres");
			}
			if(senhaTxt1 == null) {
				erros.add("A senha não foi informada!");
			}
			if(senhaTxt2 == null) {
				erros.add("A senha não foi informada!");
			}
			if(senhaTxt1 != null && !senhaTxt1.equalsIgnoreCase(senhaTxt2)) {
				erros.add("As senhas não são identicas!");
			}
			
			if (matricula == null || matricula.isEmpty()) {
				erros.add("Matricula não foi informada!");
			}
			if (nome == null || nome.isEmpty()) {
				erros.add("Nome não foi informado!");
			}		
			if (email == null || email.isEmpty()) {
				erros.add("Email não foi informado!");
			}
			if (!erros.isExisteErros()) {
				// monta um objeto contato
				Usuario usuario = new Usuario();
				usuario.setMatricula(matricula);
				usuario.setNome(nome);
				usuario.setSenha(Senha.criptografar(senhaTxt1));
				usuario.setEmail(email);
				usuario.setMailConfirm(true);				
				usuario.setPerfil(1);

				// salva o contato
				UsuarioDAO dao = new UsuarioDAO();
				Usuario user = dao.existeUsuario(usuario);
				Usuario existeEmail = dao.existeEmail(usuario);
				if (user == null && existeEmail == null) {
					try {
						dao.adiciona(usuario);
						//JavaMail.enviarEmail(email, senhaProvisoria);
						avisos.add("Solicitação efetuada com sucesso!");
						avisos.add("Sistema liberado para logar!");
						//url = "mvc?logica=IniciaTarefaLogica&url=/WEB-INF/jsp/login.jsp";
					} catch (RuntimeException e) {
						e.printStackTrace();
						//url = "/WEB-INF/jsp/novoUsuario.jsp";
					}
				} else {
					erros.add("Usuario já cadastrado ou email já cadastrado");
					url = "mvc?logica=IniciaTarefaLogica&url=/WEB-INF/jsp/novoUsuario.jsp";
				}
			}else {
				url = "mvc?logica=IniciaTarefaLogica&url=/WEB-INF/jsp/novoUsuario.jsp";
			}
			
			req.getSession().setAttribute("mensagens", erros);
			req.getSession().setAttribute("noticias", avisos);
		}
		return url;
	}
}
