package modelo;

import java.io.Serializable;
import java.text.ParseException;

public class Tabela implements Serializable {
	
	private static final long serialVersionUID = 3243776520687445838L;
	
	private int id;
	private String nome;
	private boolean ativo;
	private static long dataAtualizacao;

	public static long getDataAtualizacao() {
		return dataAtualizacao;
	}

	public static void setDataAtualizacao() throws ParseException {
		Tabela.dataAtualizacao = System.currentTimeMillis();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
