package util;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import modelo.Usuario;

public class ThreadRetirarUsuario implements Runnable {
	private static Integer instancia = 0;
	private static final Integer limiteTempo = 5;

	public static Integer getInstancia() {
		return instancia;
	}

	public static void setInstancia() {
		instancia++;
	}

	@Override
	public void run() {
		while (!Thread.interrupted() && Usuario.totalListaUsuario() > 0) {
			try {
				Map<Integer, HttpSession> lista = new HashMap<Integer, HttpSession>();
				lista = Usuario.getUsuarioLista();
				// percorrer por toda a lista e verificar qual está fora do
				// tempo
				for (Integer key : lista.keySet()) {
					HttpSession session = lista.get(key);
					// calcula com o tempo de inativação
					Long segundos = (System.currentTimeMillis()
							- session.getLastAccessedTime()) / 1000;
					long minutos = TimeUnit.SECONDS.toMinutes(segundos);
					// verifica se o tempo de inativaçao e maior do que o limite
					if (minutos > limiteTempo) {
						// lista.get(key).invalidate();
						Usuario user = (Usuario) session.getAttribute("usuarioLogado");
						String texto = "Usuario: " + user.getNome()  + " obsoleto no sistema - : " + FormatarDatas.dataAtual();
						RegistroEvento.registrar(texto);
						Usuario.removeUsuarioLista(key);
						
					}
				}
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String texto;
		try {
			texto = "Sem usuarios no sistema - : " + FormatarDatas.dataAtual();
			RegistroEvento.registrar(texto);
			Thread.interrupted();
			instancia = 0;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
