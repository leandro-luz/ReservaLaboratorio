package modelo;

import java.io.Serializable;
import java.util.Date;

public class Laboratorio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String areaNome;
	private Integer idArea;
	private boolean ativo;
	private static long dataAtualizacao;
	
	public String getAreaNome() {
		return areaNome;
	}
	public void setAreaNome(String areaNome) {
		this.areaNome = areaNome;
	}
	public static void setDataAtualizacao(long dataAtualizacao) {
		Laboratorio.dataAtualizacao = new Date().getTime();
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

	public Integer getIdArea() {
		return idArea;
	}
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
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
		Laboratorio.dataAtualizacao = new Date().getTime();
	}

}
