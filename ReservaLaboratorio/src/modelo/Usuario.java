package modelo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpSession;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1813375577567333383L;

	private static Map<Integer, HttpSession> listaUsuarios = new HashMap<Integer, HttpSession>();
	private Integer id;
	private String matricula;
	private String nome;
	private String senha;
	private String email;
	private boolean mailConfirm;
	private Integer perfil;
	private String perfilNome;
	private Integer acessos;
	private String ultimoAcesso;
	private String ultimaRequisicao;
	private boolean ativo;
	private static long dataAtualizacao;

	public static long getDataAtualizacao() {
		return dataAtualizacao;
	}

	public static void setDataAtualizacao() throws ParseException {
		Usuario.dataAtualizacao = System.currentTimeMillis();
	}

	public static void setUsuarioLista(Integer id, HttpSession session) {
		HttpSession ses = listaUsuarios.get(id);
		if (listaUsuarios.containsKey(id)) {
			if (!ses.equals(session)) {
				removeUsuarioLista(id);
				setUsuarioLista(id, session);
			}
		} else {
			listaUsuarios.put(id, session);
		}
	}

	public static void removeUsuarioLista(Integer id) {
		for (Integer key : listaUsuarios.keySet()) {
			if (key == id) {
				HttpSession session = listaUsuarios.get(key);
				session.invalidate();
			}
		}
		listaUsuarios.remove(id);
	}

	public static boolean existeUsuarioLista(Integer id) {
		boolean resultado = false;
		Map<Integer, HttpSession> lista = new HashMap<Integer, HttpSession>();
		lista = Usuario.getUsuarioLista();
		for (Integer key : lista.keySet()) {
			if (key == id) {
				resultado = true;
			}
		}
		return resultado;
	}

	public static Map<Integer, HttpSession> getUsuarioLista() {
		Map<Integer, HttpSession> lista = new HashMap<Integer, HttpSession>();
		lista = listaUsuarios;
		return lista;
	}

	public static Integer totalListaUsuario() {
		return listaUsuarios.size();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isMailConfirm() {
		return mailConfirm;
	}

	public void setMailConfirm(boolean mailConfirm) {
		this.mailConfirm = mailConfirm;
	}

	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	public String getPerfilNome() {
		return perfilNome;
	}

	public void setPerfilNome(String perfilNome) {
		this.perfilNome = perfilNome;
	}

	public Integer getAcessos() {
		return acessos;
	}

	public void setAcessos(Integer acessos) {
		this.acessos = acessos;
	}

	public String getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(String ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getUltimaRequisicao() {
		return ultimaRequisicao;
	}

	public void setUltimaRequisicao(String ultimaRequisicao) {
		this.ultimaRequisicao = ultimaRequisicao;
	}

	public static List<Usuario> getLogados() {
		List<Usuario> logados = new ArrayList<>();

		Map<Integer, HttpSession> lista = new HashMap<Integer, HttpSession>();
		lista = Usuario.getUsuarioLista();
		// percorrer por toda a lista e verificar qual está fora do tempo
		for (Integer key : lista.keySet()) {
			HttpSession session = lista.get(key);

			Date dt = new Date(session.getLastAccessedTime());
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			df.setTimeZone(TimeZone.getTimeZone("GMT"));

			Usuario user = (Usuario) session.getAttribute("usuarioLogado");
			Usuario usuario = new Usuario();

			usuario.setMatricula(user.getMatricula());
			usuario.setNome(user.getNome());
			usuario.setPerfilNome(user.getPerfilNome());
			usuario.setAcessos(user.getAcessos());
			usuario.setUltimoAcesso(user.getUltimoAcesso());
			usuario.setUltimaRequisicao(df.format(dt));
			logados.add(usuario);
		}
		return logados;
	}

}
