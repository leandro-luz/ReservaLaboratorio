package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import modelo.Tabela;

public class TabelaDAO {

	public List<Tabela> getLista() {
		List<Tabela> tabelas = new ArrayList<Tabela>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stmt = connection.prepareStatement("select * from tabela");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Tabela tabela = new Tabela();
				tabela.setId(rs.getInt("tabela_id"));
				tabela.setNome(rs.getString("tabela_nome"));
				tabela.setAtivo(rs.getBoolean("tabela_ativo"));
				tabelas.add(tabela);
			}
	
			connection.commit();
			return tabelas;
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
