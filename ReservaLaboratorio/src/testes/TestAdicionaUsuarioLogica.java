package testes;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import junit.framework.TestCase;
import mvc.logica.AdicionaUsuarioLogica;
import util.Aviso;
import util.Erro;

public class TestAdicionaUsuarioLogica extends TestCase {
	MockHttpSession session = new MockHttpSession();
	MockHttpServletRequest request = new MockHttpServletRequest();
	MockHttpServletResponse response = new MockHttpServletResponse();

	AdicionaUsuarioLogica adc = new AdicionaUsuarioLogica();
	Erro erro = new Erro();
	Aviso aviso = new Aviso();
	List<String> erros = new ArrayList<>();
	List<String> avisos = new ArrayList<>();

	@Test
	public void testValueBok() throws Exception {
		// Resultado esperado
		String ResultadoEsperado = "login.jsp";

		// Criando os objetos necessarios
		request.setParameter("bOK", "difSalvar");

		// Comparando os resultados
		assertSame(ResultadoEsperado, adc.executa(request, response));
	}

	@Test
	public void testMatriculaNull() throws Exception {
		// Resultado esperado
		String erro = "Matricula não foi informada!";

		// Seta os parametros para simular o erro
		request.setParameter("bOK", "Salvar");
		// request.setParameter("matricula", null); //matricula sem informar
		request.setParameter("nome", "nome555");
		request.setParameter("email", "nome555@teste.com.br");
		request.setParameter("senha1", "aaa");
		request.setParameter("senha2", "aaa");
		request.setSession(session);


		// Executa o metodo
		adc.executa(request, response);

		// Recupera o atributo mensagens, que contem os erros
		Erro mensagem = (Erro) request.getSession().getAttribute("mensagens");
		erros = mensagem.getErros();

		// Verificar se o metodo está correto
		assertSame(true, erros.contains(erro));
	}

	@Test
	public void testMatriculaVazia() throws Exception {
		// Resultado esperado
		String erro = "Matricula não foi informada!";

		// Seta os parametros para simular o erro
		request.setParameter("bOK", "Salvar");
		request.setParameter("matricula", ""); // matricula vazia
		request.setParameter("nome", "nome555");
		request.setParameter("email", "nome555@teste.com.br");
		request.setParameter("senha1", "aaa");
		request.setParameter("senha2", "aaa");
		request.setSession(session);

		// Executa o metodo
		adc.executa(request, response);

		// Recupera o atributo mensagens, que contem os erros
		Erro mensagem = (Erro) request.getSession().getAttribute("mensagens");
		erros = mensagem.getErros();

		// Verificar se o metodo está correto
		assertSame(true, erros.contains(erro));
	}

	@Test
	public void testNomeNull() throws Exception {
		// Resultado esperado
		String erro = "Nome não foi informado!";

		// Seta os parametros para simular o erro
		request.setParameter("bOK", "Salvar");
		request.setParameter("matricula", "12345");
		// request.setParameter("nome", "nome555"); //nome sem informar
		request.setParameter("email", "nome555@teste.com.br");
		request.setParameter("senha1", "aaa");
		request.setParameter("senha2", "aaa");
		request.setSession(session);

		// Executa o metodo
		adc.executa(request, response);

		// Recupera o atributo mensagens, que contem os erros
		Erro mensagem = (Erro) request.getSession().getAttribute("mensagens");
		erros = mensagem.getErros();

		// Verificar se o metodo está correto
		assertSame(true, erros.contains(erro));
	}

	@Test
	public void testNomeVazio() throws Exception {
		// Resultado esperado
		String erro = "Nome não foi informado!";

		// Seta os parametros para simular o erro
		request.setParameter("bOK", "Salvar");
		request.setParameter("matricula", "12345");
		request.setParameter("nome", ""); // nome vazio
		request.setParameter("email", "nome555@teste.com.br");
		request.setParameter("senha1", "aaa");
		request.setParameter("senha2", "aaa");
		request.setSession(session);

		// Executa o metodo
		adc.executa(request, response);

		// Recupera o atributo mensagens, que contem os erros
		Erro mensagem = (Erro) request.getSession().getAttribute("mensagens");
		erros = mensagem.getErros();

		// Verificar se o metodo está correto
		assertSame(true, erros.contains(erro));
	}

	@Test
	public void testEmailNull() throws Exception {
		// Resultado esperado
		String erro = "Email não foi informado!";

		// Seta os parametros para simular o erro
		request.setParameter("bOK", "Salvar");
		request.setParameter("matricula", "12345");
		request.setParameter("nome", "nome555");
		// request.setParameter("email", "nome555@teste.com.br"); //sem o email
		request.setParameter("senha1", "aaa");
		request.setParameter("senha2", "aaa");
		request.setSession(session);

		// Executa o metodo
		adc.executa(request, response);

		// Recupera o atributo mensagens, que contem os erros
		Erro mensagem = (Erro) request.getSession().getAttribute("mensagens");
		erros = mensagem.getErros();

		// Verificar se o metodo está correto
		assertSame(true, erros.contains(erro));
	}

	@Test
	public void testEmailVazio() throws Exception {
		// Resultado esperado
		String erro = "Email não foi informado!";

		// Seta os parametros para simular o erro
		request.setParameter("bOK", "Salvar");
		request.setParameter("matricula", "12345");
		request.setParameter("nome", "nome555");
		request.setParameter("email", ""); // email vazio
		request.setParameter("senha1", "aaa");
		request.setParameter("senha2", "aaa");
		request.setSession(session);

		// Executa o metodo
		adc.executa(request, response);

		// Recupera o atributo mensagens, que contem os erros
		Erro mensagem = (Erro) request.getSession().getAttribute("mensagens");
		erros = mensagem.getErros();

		// Verificar se o metodo está correto
		assertSame(true, erros.contains(erro));
	}

	@Test
	public void testSenhasDiferentes() throws Exception {
		// Resultado esperado
		String erro = "As senhas não são identicas!";

		// Seta os parametros para simular o erro
		request.setParameter("bOK", "Salvar");
		request.setParameter("matricula", "12345");
		request.setParameter("nome", "nome555");
		request.setParameter("email", "nome555@teste.com.br");
		request.setParameter("senha1", "aaa"); // senhas diferentes
		request.setParameter("senha2", "aba");
		request.setSession(session);

		// Executa o metodo
		adc.executa(request, response);

		// Recupera o atributo mensagens, que contem os erros
		Erro mensagem = (Erro) request.getSession().getAttribute("mensagens");
		erros = mensagem.getErros();

		// Verificar se o metodo está correto
		assertSame(true, erros.contains(erro));
	}

	@Test
	public void testSenha1Null() throws Exception {
		// Resultado esperado
		String erro = "A senha não foi informada!";

		// Seta os parametros para simular o erro
		request.setParameter("bOK", "Salvar");
		request.setParameter("matricula", "12345");
		request.setParameter("nome", "nome555");
		request.setParameter("email", "nome555@teste.com.br");
		// request.setParameter("senha1", "aaa"); //senha null
		request.setParameter("senha2", "aaa");
		request.setSession(session);

		// Executa o metodo
		adc.executa(request, response);

		// Recupera o atributo mensagens, que contem os erros
		Erro mensagem = (Erro) request.getSession().getAttribute("mensagens");
		erros = mensagem.getErros();

		// Verificar se o metodo está correto
		assertSame(true, erros.contains(erro));
	}

	@Test
	public void testSenha2Null() throws Exception {
		// Resultado esperado
		String erro = "A senha não foi informada!";

		// Seta os parametros para simular o erro
		request.setParameter("bOK", "Salvar");
		request.setParameter("matricula", "12345");
		request.setParameter("nome", "nome555");
		request.setParameter("email", "nome555@teste.com.br");
		// request.setParameter("senha1", "aaa");
		// request.setParameter("senha2", "aaa");//senha null
		session.setAttribute("mensagens", "bbb");
		request.setSession(session);

		// Executa o metodo
		adc.executa(request, response);

		// Recupera o atributo mensagens, que contem os erros
		Erro mensagem = (Erro) request.getSession().getAttribute("mensagens");
		erros = mensagem.getErros();

		// Verificar se o metodo está correto
		assertSame(true, erros.contains(erro));
	}

	
	/*
	public void testUsuarioExiste() throws Exception { // Resultado esperado
		String erro = "Email não foi informado!";
		// Seta os parametros para simular o erro
		request.setParameter("bOK", "Salvar");
		request.setParameter("matricula", "789");
		request.setParameter("nome", "789");
		request.setParameter("email", "789@teste@.com.br");
		request.setParameter("senha1", "789");
		request.setParameter("senha2", "789");
		session.setAttribute("mensagens", "aaa");
		request.setSession(session);
		
		
						

		// Executa o metodo
		adc.executa(request, response);

		// Recupera o atributo mensagens, que contem os erros
		Erro mensagem = (Erro) request.getSession().getAttribute("mensagens");
		erros = mensagem.getErros();

		// Verificar se o metodo está correto
		assertSame(true, erros.contains(erro));
		
	}
	*/

}
