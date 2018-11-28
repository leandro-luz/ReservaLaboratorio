package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import jdbc.ConnectionFactory;
import modelo.Area;

public class AreaDAO {

	public JSONArray getArea() {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			JSONArray array = new JSONArray();
			stmt = connection.prepareStatement(
					"select * from departamento where area_ativo = true");
			rs = stmt.executeQuery();
			while (rs.next()) {
				try {
					JSONObject json = new JSONObject();
					json.append("Id", rs.getString("area_id"));
					json.append("area_nome", rs.getString("area_nome"));
					array.put(json);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			connection.commit();
			return array;

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

	public List<Area> getList() {
		List<Area> areas = new ArrayList<>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stmt = connection.prepareStatement("select * from departamento");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Area area = new Area();
				area.setId(rs.getInt("area_id"));
				area.setNome(rs.getString("area_nome"));
				area.setAtivo(rs.getBoolean("area_ativo"));
				areas.add(area);
			}

			connection.commit();
			return areas;

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

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stmt = connection.prepareStatement(
					"select * from departamento where area_id =" + id);
			rs = stmt.executeQuery();
			Area area = new Area();
			while (rs.next()) {
				area.setAtivo(rs.getBoolean("area_ativo"));
			}
			String sql;
			if (area.isAtivo()) {
				sql = "Update departamento SET area_ativo = false where area_id = "
						+ id;
			} else {
				sql = "Update departamento SET area_ativo = true where area_id = "
						+ id;
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
			if (stmt2 != null) {
				try {
					stmt2.close();
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

	public void update(Area newArea) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = "Update departamento SET area_nome = ? where area_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, newArea.getNome());
			stmt.setInt(2, newArea.getId());

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

	public static void novoItem(String nome) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = "Insert into departamento (area_nome, area_ativo) values (?,?)";
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
					"Delete from departamento where area_id = '" + id + "'");

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

	public Integer contarItem(Integer id) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Integer resultado = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stmt = connection.prepareStatement("select (SUM(CASE WHEN area_id = '" + id + "' THEN 1 ELSE 0 END) + " + 
					"SUM(CASE WHEN area_id = '" + id + "' THEN 1 ELSE 0 END) + " + 
					"SUM(CASE WHEN area_id = '" + id +"' THEN 1 ELSE 0 END)) as qtd " + 
					"from agenda");
			
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
