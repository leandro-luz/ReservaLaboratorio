package testes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import junit.framework.TestCase;
import modelo.Agenda;
import modelo.Area;
import modelo.Laboratorio;
import modelo.Perfil;
import modelo.Tabela;
import modelo.Usuario;

public class TestModelo extends TestCase {

	@Test
	public void testModeloAgenda() {
		Calendar c1 = Calendar.getInstance();
		Date data = c1.getTime();
		long aa = 123;

		Agenda agenda = new Agenda();
		agenda.setAreaId(1);
		agenda.setAreaNome("area");
		agenda.setDate(data);
		agenda.setId(2);
		agenda.setLaboratorioId(3);
		agenda.setLaboratorioNome("laboratorio");
		agenda.setTurnoManha("manha");
		agenda.setTurnoNoite("noite");
		agenda.setTurnoTarde("tarde");
		Agenda.setDataAtualizacao(aa);

		// AreaId
		assertSame(1, agenda.getAreaId());
		// AreaNome
		assertSame("area", agenda.getAreaNome());
		// Date
		assertSame(data, agenda.getDate());
		// ID
		assertSame(2, agenda.getId());
		// LaboratorioId
		assertSame(3, agenda.getLaboratorioId());
		// LaboratorioNome
		assertSame("laboratorio", agenda.getLaboratorioNome());
		// TurnoManha
		assertSame("manha", agenda.getTurnoManha());
		// TurnoNoite
		assertSame("noite", agenda.getTurnoNoite());
		// TurnoTarde
		assertSame("tarde", agenda.getTurnoTarde());
		// Data atualização
		assertSame(aa, Agenda.getDataAtualizacao());

	}

	@Test
	public void testModeloArea() throws ParseException {
		Area area = new Area();
		area.setId(1);
		area.setNome("area");
		area.setAtivo(true);
		Area.setDataAtualizacao();

		// AreaId
		assertSame(1, area.getId());
		// Nome
		assertSame("area", area.getNome());
		// Ativo
		assertSame(true, area.isAtivo());
		// Data atualizaçao
		Long aa = Area.getDataAtualizacao();
		Long bb = System.currentTimeMillis();

		assertSame(0, aa.compareTo(bb));
		// Não Ativo
		area.setAtivo(false);
		assertSame(false, area.isAtivo());
	}

	@Test
	public void testModeloLaboratorio() throws ParseException {
		Laboratorio laboratorio = new Laboratorio();
		laboratorio.setId(1);
		laboratorio.setNome("laboratorio");
		laboratorio.setAreaNome("area");
		laboratorio.setIdArea(2);
		laboratorio.setAtivo(true);
		Laboratorio.setDataAtualizacao(System.currentTimeMillis());

		// Id
		assertSame(1, laboratorio.getId());
		// Nome
		assertSame("laboratorio", laboratorio.getNome());
		// AreaNome
		assertSame("area", laboratorio.getAreaNome());
		// IdArea
		assertSame(2, laboratorio.getIdArea());
		// Ativo
		assertSame(true, laboratorio.isAtivo());
		// Data atualizaçao
		Long aa = Laboratorio.getDataAtualizacao();
		Long bb = System.currentTimeMillis();
		assertSame(0, aa.compareTo(bb));
		// Não Ativo
		laboratorio.setAtivo(false);
		assertSame(false, laboratorio.isAtivo());
		// Outra data
		Laboratorio.setDataAtualizacao();
		// Data atualizaçao
		Long ab = Laboratorio.getDataAtualizacao();
		Long bc = System.currentTimeMillis();
		assertSame(0, ab.compareTo(bc));

	}

	@Test
	public void testModeloTabela() throws ParseException {
		Tabela tabela = new Tabela();

		tabela.setId(1);
		tabela.setNome("tabela");
		tabela.setAtivo(true);
		Tabela.setDataAtualizacao();

		// AreaId
		assertSame(1, tabela.getId());
		// Nome
		assertSame("tabela", tabela.getNome());
		// Ativo
		assertSame(true, tabela.isAtivo());
		// Data atualizaçao
		Long aa = Tabela.getDataAtualizacao();
		Long bb = System.currentTimeMillis();

		assertSame(0, aa.compareTo(bb));
		// Não Ativo
		tabela.setAtivo(false);
		assertSame(false, tabela.isAtivo());
	}

	@Test
	public void testModeloPerfil() throws ParseException {
		Perfil perfil = new Perfil();

		perfil.setId(1);
		perfil.setNome("tabela");
		perfil.setAtivo(true);
		Perfil.setDataAtualizacao();

		// AreaId
		assertSame(1, perfil.getId());
		// Nome
		assertSame("tabela", perfil.getNome());
		// Ativo
		assertSame(true, perfil.isAtivo());
		// Data atualizaçao
		Long aa = Perfil.getDataAtualizacao();
		Long bb = System.currentTimeMillis();

		assertSame(0, aa.compareTo(bb));
		// Não Ativo
		perfil.setAtivo(false);
		assertSame(false, perfil.isAtivo());
	}

	@Test
	public void testModeloUsuario() throws ParseException  {
		Usuario usuario = new Usuario();

		usuario.setId(1);
		usuario.setMatricula("123");
		usuario.setNome("usuario");
		usuario.setSenha("321");
		usuario.setEmail("usu@teste.com.br");
		usuario.setMailConfirm(true);
		usuario.setPerfil(1);
		usuario.setPerfilNome("perfilNome");
		usuario.setAcessos(10);
		usuario.setUltimoAcesso("ultimoAcesso");
		usuario.setUltimaRequisicao("ultimaRequisicao");
		usuario.setAtivo(true);
		Usuario.setDataAtualizacao();

		// AreaId
		assertSame(1, usuario.getId());
		// Matricula
		assertSame("123", usuario.getMatricula());
		// Nome
		assertSame("usuario", usuario.getNome());
		// Senha
		assertSame("321", usuario.getSenha());
		// Email
		assertSame("usu@teste.com.br", usuario.getEmail());
		// Emailconfirmado
		assertSame(true, usuario.isMailConfirm());
		// Perfil
		assertSame(1, usuario.getPerfil());
		// PerfilNome
		assertSame("perfilNome", usuario.getPerfilNome());
		// Acessos
		assertSame(10, usuario.getAcessos());
		// UltimoAcesso
		assertSame("ultimoAcesso", usuario.getUltimoAcesso());
		// UltimaRequisicao
		assertSame("ultimaRequisicao", usuario.getUltimaRequisicao());
		// Data atualizaçao
		Long aa = Usuario.getDataAtualizacao();
		Long bb = System.currentTimeMillis();
		assertSame(0, aa.compareTo(bb));
		// Ativo
		assertSame(true, usuario.isAtivo());
		// Não Ativo
		usuario.setAtivo(false);
		assertSame(false, usuario.isAtivo());
	}

	@Test
	public void testModeloUsuarioSetLista() {
		Integer id = 1;
		MockHttpSession session = new MockHttpSession();
		MockHttpSession session2 = new MockHttpSession();
		// Verificar se o usuario está na lista, deve retornar falso

		assertSame(false, Usuario.existeUsuarioLista(id));
		// Insere um usuario na lista
		Usuario.setUsuarioLista(id, session);

		// Verifica novamente se o usuario está na lista, deve retornar true
		assertSame(true, Usuario.existeUsuarioLista(id));
		assertSame(false, Usuario.existeUsuarioLista(2));

		// Insere um usuario novamente na lista, na mesma sessão
		Usuario.setUsuarioLista(id, session);
		// Insere um usuario novamente na lista, em outra sessão
		Usuario.setUsuarioLista(id, session2);

	}
	
	@Test
	public void testModeloUsuarioLogados() {
		List<Usuario> logados = new ArrayList<>();
		MockHttpSession session = new MockHttpSession();
		Integer id = 1;
		Usuario usuario = new Usuario();
		usuario.setMatricula("123");
;
		usuario.setNome("nome");
		usuario.setPerfilNome("perfil");
		usuario.setAcessos(10);
		usuario.setUltimoAcesso("ultimoAcesso");
		usuario.setUltimaRequisicao("ultimaRequisicao");

		session.setAttribute("usuarioLogado",usuario);
		
		//Inserir o usuario na sessão
		Usuario.setUsuarioLista(id, session);
		
		logados = Usuario.getLogados();
		
		assertSame(false, logados.size()>1);
	}

}
