package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Erro implements Serializable {

	private static final long serialVersionUID = 5801842926256625063L;
	private final List<String> erros;

	public Erro() {
		erros = new ArrayList<>();
	}

	public void add(String mensagem) {
		erros.add(mensagem);
	}

	public boolean isExisteErros() {
		return !erros.isEmpty();
	}

	public List<String> getErros() {
		return erros;
	}
}