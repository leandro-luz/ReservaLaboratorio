package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.ConnectionFactory;

public class BancoDAO {
	
	public void criarTabelas() {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = "\"CREATE TABLE departamento(" + 
					"area_id integer NOT NULL AUTO_INCREMENT," + 
					"area_nome character varying(15) NOT NULL," + 
					"area_ativo boolean NOT NULL," + 
					"CONSTRAINT departamento_pkey PRIMARY KEY (area_id)";
			stmt = connection.prepareStatement(sql);

			stmt.execute();
			connection.commit();

		} catch (SQLException e) {
			if (connection != null) {
				try {
					// desfaz alterações enviadas pro banco
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			// relança exceção
			throw new RuntimeException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}
