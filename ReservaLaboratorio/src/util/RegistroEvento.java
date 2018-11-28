package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import modelo.Usuario;

public class RegistroEvento {

	public static void atividades(HttpServletRequest req, String logica, String url) {
		Usuario u = (Usuario) req.getSession().getAttribute("usuarioLogado");

		String dataTime = null;
		String nome = null;
		String matricula = null;
		Integer area = null;
		Integer subarea = null;
		int perfil = 0;

		if (u != null) {
			matricula = u.getMatricula();
			nome = u.getNome();
			perfil = u.getPerfil();
		}

		try {
			dataTime = FormatarDatas.dataAtual();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		String texto = "time:" + dataTime + 
				", matricula:" + matricula + 
				", nome:" + nome +
				", perfil:" + perfil + 
				", logica:" + logica +
				", url:" + url + 
				", logados:" + Usuario.totalListaUsuario();

		registrar(texto);
	}

	public static void registrar(String texto) {
		String path = "d:\\reservaslog.txt";
		try {
			FileWriter fw = new FileWriter(path, true);
			BufferedWriter conexao = new BufferedWriter(fw);
			conexao.write(texto);
			conexao.newLine();
			conexao.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
