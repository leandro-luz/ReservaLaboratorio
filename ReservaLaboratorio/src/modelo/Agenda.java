package modelo;

import java.io.Serializable;
import java.util.Date;

public class Agenda implements Serializable{
	private static final long serialVersionUID = 1L;
	private static long dataAtualizacao;
	
	private Integer id;
	private Integer areaId;
	private String areaNome;
	private Integer laboratorioId;
	private String laboratorioNome;
	private Date date;
	private String turnoManha;
	private String turnoTarde;
	private String turnoNoite;
	
	public static long getDataAtualizacao() {
		return dataAtualizacao;
	}
	
	public static void setDataAtualizacao(long dataAtualizacao) {
		Agenda.dataAtualizacao = dataAtualizacao;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaNome() {
		return areaNome;
	}
	public void setAreaNome(String areaNome) {
		this.areaNome = areaNome;
	}
	public Integer getLaboratorioId() {
		return laboratorioId;
	}
	public void setLaboratorioId(Integer laboratorioId) {
		this.laboratorioId = laboratorioId;
	}
	public String getLaboratorioNome() {
		return laboratorioNome;
	}
	public void setLaboratorioNome(String laboratorioNome) {
		this.laboratorioNome = laboratorioNome;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTurnoManha() {
		return turnoManha;
	}
	public void setTurnoManha(String turnoManha) {
		this.turnoManha = turnoManha;
	}
	public String getTurnoTarde() {
		return turnoTarde;
	}
	public void setTurnoTarde(String turnoTarde) {
		this.turnoTarde = turnoTarde;
	}
	public String getTurnoNoite() {
		return turnoNoite;
	}
	public void setTurnoNoite(String turnoNoite) {
		this.turnoNoite = turnoNoite;
	}
	
}
