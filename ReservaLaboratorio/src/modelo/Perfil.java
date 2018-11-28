package modelo;

import java.io.Serializable;
import java.util.Date;

public class Perfil implements Serializable{
	
	private static final long serialVersionUID = 1033249795670288342L;
	
	private int id;
	private String nome;
	private boolean ativo;
	private static long dataAtualizacao;
	
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
	public static long getDataAtualizacao() {
		return dataAtualizacao;
	}
	public static void setDataAtualizacao() {
		Perfil.dataAtualizacao = new Date().getTime();
	}
	
	
	
}
