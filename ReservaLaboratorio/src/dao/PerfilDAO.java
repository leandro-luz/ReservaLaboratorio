package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import modelo.Perfil;

public class PerfilDAO {

	public List<Perfil> getList() {
		List<Perfil> perfis = new ArrayList<>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement("select * from perfil");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Perfil perfil = new Perfil();
				perfil.setId(rs.getInt("perfil_id"));
				perfil.setNome(rs.getString("perfil_nome"));
				perfil.setAtivo(rs.getBoolean("perfil_ativo"));
				perfis.add(perfil);
			}

			connection.commit();
			return perfis;

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

	public void alterarStatus(String id) {
		Connection connection = null;
		PreparedStatement stmt = null, stmt2 = null;
		ResultSet rs = null;
		Boolean ativo = false;
		String sql;
		
		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stmt = connection.prepareStatement(
					"select * from perfil where perfil_id =" + id);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				ativo = rs.getBoolean("perfil_ativo");
			}

			
			if (ativo) {
				sql = "Update perfil SET perfil_ativo = false where perfil_id = " + id;
			} else {
				sql = "Update perfil SET perfil_ativo = true where perfil_id = " + id;
			}

			stmt2 = connection.prepareStatement(sql);
			stmt2.execute();
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
	
	public static void novoItem(String nome) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = "Insert into perfil (perfil_nome, perfil_ativo) values (?,?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setBoolean(2, false);

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

	public void excluir(Integer id) {
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stmt = connection.prepareStatement(
					"Delete from perfil where perfil_id = '" + id + "'");

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
	
	public void update(Perfil newPerfil) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = "Update perfil SET perfil_nome = ? where perfil_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, newPerfil.getNome());
			stmt.setInt(2, newPerfil.getId());

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

	public Integer contarItem(Integer id) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Integer resultado = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stmt = connection.prepareStatement("select count(*) as qtd from usuario where perfil_id = '" + id + "'");
			rs = stmt.executeQuery();

			while (rs.next()) {
				resultado = rs.getInt("qtd");
			}

			connection.commit();
			return resultado;

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
