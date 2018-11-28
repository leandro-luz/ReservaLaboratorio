package testes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import junit.framework.TestCase;
import modelo.Usuario;
import mvc.logica.ControllerServlet;
import mvc.logica.ListaAgendamento;
import mvc.logica.Login;
import util.Aviso;
import util.Erro;

public class TestMvcLogica extends TestCase {
	
	MockHttpSession session = new MockHttpSession();
	MockHttpServletRequest request = new MockHttpServletRequest();
	MockHttpServletResponse response = new MockHttpServletResponse();

	Login login = new Login();
	Erro erro = new Erro();
	Aviso aviso = new Aviso();
	List<String> erros = new ArrayList<>();
	List<String> avisos = new ArrayList<>();
	
	
	public void testControlador() throws ServletException, IOException {
		request.setParameter("logica", "Administracao");
		request.setParameter("url", "login.jsp");
		
		ControllerServlet control = new ControllerServlet();
		control.service(request, response);
	}
	
	@Test
	public void testLoginValueBok() throws Exception {
		// Resultado esperado
		String ResultadoEsperado = "login.jsp";

		// Criando os objetos necessarios
		request.setParameter("bOK", "null"); //botão bOK nulo

		// Comparando os resultados
		assertSame(ResultadoEsperado, login.executa(request, response));
	}

	@Test
	public void testLoginMatriculaNull() throws Exception {
		// Resultado esperado
		String erro = "Matricula não foi informada!";

		// Seta os parametros para simular o erro
		request.setParameter("bOK", "Salvar");
		// request.setParameter("matricula", null); //matricula sem informar
		request.setParameter("senha", "aaa");
		request.setSession(session);

		// Executa o metodo
		login.executa(request, response);

		// Recupera o atributo mensagens, que contem os erros
		Erro mensagem = (Erro) request.getSession().getAttribute("mensagens");
		erros = mensagem.getErros();

		// Verificar se o metodo está correto
		assertSame(true, erros.contains(erro));
	}
	
	@Test
	public void testLoginMatriculaVazia() throws Exception {
		// Resultado esperado
		String erro = "Matricula não foi informada!";

		// Seta os parametros para simular o erro
		request.setParameter("bOK", "Salvar");
		request.setParameter("matricula", ""); //matricula vazia
		request.setParameter("senha", "aaa");
		request.setSession(session);

		// Executa o metodo
		login.executa(request, response);

		// Recupera o atributo mensagens, que contem os erros
		Erro mensagem = (Erro) request.getSession().getAttribute("mensagens");
		erros = mensagem.getErros();

		// Verificar se o metodo está correto
		assertSame(true, erros.contains(erro));
	}
	
	@Test
	public void testLoginSenhaNula() throws Exception {
		// Resultado esperado
		String erro = "Senha não informada!";

		// Seta os parametros para simular o erro
		request.setParameter("bOK", "Salvar");
		request.setParameter("matricula", "789");
		//request.setParameter("senha", "aaa"); //senha nula
		request.setSession(session);

		// Executa o metodo
		login.executa(request, response);

		// Recupera o atributo mensagens, que contem os erros
		Erro mensagem = (Erro) request.getSession().getAttribute("mensagens");
		erros = mensagem.getErros();

		// Verificar se o metodo está correto
		assertSame(true, erros.contains(erro));
	}
	
	@Test
	public void testLoginSenhaVazia() throws Exception {
		// Resultado esperado
		String erro = "Senha não informada!";

		// Seta os parametros para simular o erro
		request.setParameter("bOK", "Salvar");
		request.setParameter("matricula", "789");
		request.setParameter("senha", ""); //senha vazia
		request.setSession(session);

		// Executa o metodo
		login.executa(request, response);

		// Recupera o atributo mensagens, que contem os erros
		Erro mensagem = (Erro) request.getSession().getAttribute("mensagens");
		erros = mensagem.getErros();

		// Verificar se o metodo está correto
		assertSame(true, erros.contains(erro));
	}

	@Test
	public void testListaAgendamento() throws Exception {
		ListaAgendamento lista = new ListaAgendamento();
		String respontaEsperada = "login.jsp";
		String resposta = lista.executa(request, response);
		Usuario usuario = new Usuario();
		Integer id = 1;
		session.setAttribute("usuarioLogado", usuario);
		Usuario.setUsuarioLista(id, session);
		
		//Usuario 
		assertSame(true, resposta.equalsIgnoreCase(respontaEsperada));
		
		
		
	}

}
