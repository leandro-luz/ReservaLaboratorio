package testes;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import junit.framework.TestCase;
import modelo.Usuario;
import util.Aviso;
import util.Erro;
import util.FormatarDatas;
import util.RegistroEvento;
import util.Senha;
import util.UsuarioLogado;

public class TestUtil extends TestCase {

	@Test
	public void testSenha() {
		String valor = "10";
		String ss = Senha.criptografar(valor);
		assertSame(true, ss.equalsIgnoreCase("09lEaAKkQll1XTjm0WPoIA=="));

	}

	@Test
	public void testRegistroEvento() {
		MockHttpSession session = new MockHttpSession();
		MockHttpServletRequest request = new MockHttpServletRequest();

		String logica = "logica";
		String url = "url";

		// usuario = null
		RegistroEvento.atividades(request, logica, url);

		Usuario usuario = new Usuario();
		usuario.setNome("usuario");
		usuario.setMatricula("123");
		usuario.setPerfil(1);
		session.setAttribute("usuarioLogado", usuario);
		request.setSession(session);

		RegistroEvento.atividades(request, logica, url);

	}

	@Test
	public void testFormatarDatas() throws ParseException {

		String dt_prevista = "2018-10-07";
		String dataFormatada = FormatarDatas.converterData(dt_prevista);
		assertSame(true, dataFormatada.equalsIgnoreCase("07/10/2018 23:59:59"));

		Integer dataConvertidaTeste = FormatarDatas
				.converterStringtoInteger(dt_prevista);
		Integer dataConvertidaEsperada = 20181007;
		assertSame(true, dataConvertidaTeste.equals(dataConvertidaEsperada));

		Integer dataConvertidaEsperada2 = 20181008;
		Integer dataConvertidaTeste2 = FormatarDatas
				.incrementarData(dataConvertidaTeste);
		assertSame(true, dataConvertidaTeste2.equals(dataConvertidaEsperada2));

	}

	@Test
	public void testErro() throws ParseException {
		Erro erros = new Erro();
		List<String> erros2 = null;
		// Sem erros
		assertSame(false, erros.isExisteErros());

		// Com erros
		erros.add("teste");
		assertSame(true, erros.isExisteErros());

		// Com erros
		erros2 =  erros.getErros();
		assertSame(true, erros2.contains("teste"));

	}
	
	@Test
	public void testAviso() throws ParseException {
		Aviso avisos = new Aviso();
		List<String> avisos2 = null;
		// Sem erros
		assertSame(false, avisos.isExisteAvisos());

		// Com erros
		avisos.add("teste");
		assertSame(true, avisos.isExisteAvisos());

		// Com erros
		avisos2 =  avisos.getAvisos();
		assertSame(true, avisos2.contains("teste"));
	}
	
	@Test
	public void testUsuarioLogado() throws ParseException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		
		UsuarioLogado.usuarioLogado(request);
		
	}

}
