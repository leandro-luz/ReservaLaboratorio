package testes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import junit.framework.TestCase;
import mvc.logica.AdicionaUsuarioLogica;
import util.Aviso;
import util.Erro;

public class TestWithEasyMock extends Mockito {

	@Test
	public void TestMatriculaNull() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		Erro erro = new Erro();
		Aviso aviso = new Aviso();
		List<String> erros = new ArrayList<>();
		List<String> avisos = new ArrayList<>();
		AdicionaUsuarioLogica adc = new AdicionaUsuarioLogica();

		String erroEsperado = "Matricula não foi informada!";

		when(request.getParameter("bOK")).thenReturn("Salvar");
		when(request.getParameter("matricula")).thenReturn(null);
		when(request.getParameter("nome")).thenReturn("nome555");
		when(request.getParameter("email")).thenReturn("nome555@teste.com.br");
		when(request.getParameter("senha1")).thenReturn("aaa");
		when(request.getParameter("senha2")).thenReturn("aaa");
		when(request.getSession()).thenReturn(session);
		
		adc.executa(request, response);

		// Recupera o atributo mensagens, que contem os erros
		System.out.println(request.getSession().getAttribute("mensagens"));
		System.out.println(request.getAttribute("mensagens"));
		System.out.println(session.getAttribute("mensagens"));
		
		Erro mensagem = (Erro) request.getSession().getAttribute("mensagens");
		erros = mensagem.getErros();

		// Verificar se o metodo está correto
		TestCase.assertEquals(true, erros.contains(erroEsperado));
	}

}
