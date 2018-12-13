package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {

	public Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/UsersDB");
			try {
				conn = ds.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (conn == null) {
				conn = criarNovoBanco();
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		} 
		return conn;
	}

	private Connection criarNovoBanco() {
		String url = "jdbc:mysql://localhost/?useTimezone=true&serverTimezone=UTC";
		String user = "root";
		String passw = "";
		Statement s;
		Connection Conn = null;
		try {
			Conn = DriverManager.getConnection(url, user, passw);
			s = Conn.createStatement();
			int Result = s.executeUpdate("CREATE DATABASE qualidade");
			
			if(Result == 1) {	
				criarTabelas(Conn);
				Conn = getConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Conn;
	}
	
	private static void criarTabelas(Connection conn)  {
		Statement s;
		try {
			s = conn.createStatement();
			String  depto = "CREATE TABLE departamento(area_id integer NOT NULL AUTO_INCREMENT,area_nome character varying(50) NOT NULL,area_ativo boolean NOT NULL,CONSTRAINT departamento_pkey PRIMARY KEY (area_id));"; 
			String  labo =	"CREATE TABLE laboratorio(labo_id integer NOT NULL AUTO_INCREMENT,labo_nome character varying(50) NOT NULL,area_id integer NOT NULL,labo_ativo boolean NOT NULL,CONSTRAINT laboratorio_pkey PRIMARY KEY (labo_id));";
			String  tab =	"CREATE TABLE tabela(tabela_id integer NOT NULL AUTO_INCREMENT,tabela_nome character varying(30) NOT NULL,tabela_ativo boolean NOT NULL,CONSTRAINT tabela_pkey PRIMARY KEY (tabela_id));";
			String  per =	"CREATE TABLE perfil(perfil_id integer NOT NULL AUTO_INCREMENT,perfil_nome character varying(15) NOT NULL,perfil_ativo boolean NOT NULL,CONSTRAINT perfil_pkey PRIMARY KEY (perfil_id));"; 
			String  usu =	"CREATE TABLE usuario(usu_id integer NOT NULL AUTO_INCREMENT,usu_mat character varying(8) NOT NULL UNIQUE,usu_nome character varying(100) NOT NULL,usu_senha character varying(30) NOT NULL,usu_email character varying(50) NOT NULL,usu_mailconfir boolean NOT NULL,perfil_id integer NOT NULL,usu_acessos integer,usu_ultimoAcesso character varying(30),usu_ativo boolean NOT NULL,CONSTRAINT usuario_pkey PRIMARY KEY (usu_id));"; 
			String  agd =	"CREATE TABLE agenda(agenda_id integer NOT NULL AUTO_INCREMENT,area_id character varying(30) NOT NULL,labo_id character varying(30) NOT NULL,agenda_data character varying(10) NOT NULL,agenda_turno_manha character varying(10) NOT NULL,agenda_turno_tarde character varying(10) NOT NULL,agenda_turno_noite character varying(10) NOT NULL,CONSTRAINT agenda_pkey PRIMARY KEY (agenda_id));";
			String  histo =	"CREATE TABLE historico(historico_id integer NOT NULL AUTO_INCREMENT,agenda_id integer NOT NULL,agenda_turno character varying(10) NOT NULL,agenda_acao character varying(10) NOT NULL,usu_mat character varying(7) NOT NULL,CONSTRAINT historico_pkey PRIMARY KEY (historico_id));";
			
			String  tb1 = "insert into tabela (tabela_nome, tabela_ativo) values('perfil', true);";
			String  tb2	= "insert into tabela (tabela_nome, tabela_ativo) values('usuario', true);";
			String  tb3	= "insert into tabela (tabela_nome, tabela_ativo) values('laboratorio', true);";
			String  tb4	= "insert into tabela (tabela_nome, tabela_ativo) values('area', true);";
			String  pf1	= "insert into perfil ( perfil_nome, perfil_ativo) values ('CONVIDADO', true);";
			String  pf2	= "insert into perfil ( perfil_nome, perfil_ativo) values ('ANALISTA', true);";
			String  pf3	= "insert into perfil ( perfil_nome, perfil_ativo) values ('ADMINISTRADOR', true);";
			String  usu1 = "insert into usuario (usu_mat, usu_nome, usu_senha, usu_email, usu_mailconfir, perfil_id, usu_ativo) values (1234567, 'Administrador','gnzLDuqKcGxMNKFokfhOew==', 'teste@teste.com.br', true, 3, true);";

			String depto1 = "insert into departamento (area_nome, area_ativo) values ('Sistemas', 1);";
			String labo1 = "insert into laboratorio (labo_nome, area_id, labo_ativo) values ('Info-1', 1, 1);";
			
			s.executeUpdate("USE qualidade");
			s.executeUpdate(depto);
			s.executeUpdate(labo);
			s.executeUpdate(tab);
			s.executeUpdate(per);
			s.executeUpdate(usu);
			s.executeUpdate(agd);
			s.executeUpdate(histo);
			
			s.executeUpdate(tb1);
			s.executeUpdate(tb2);
			s.executeUpdate(tb3);
			s.executeUpdate(tb4);
			s.executeUpdate(pf1);
			s.executeUpdate(pf2);
			s.executeUpdate(pf3);
			s.executeUpdate(usu1);
			s.executeUpdate(depto1);
			s.executeUpdate(labo1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
