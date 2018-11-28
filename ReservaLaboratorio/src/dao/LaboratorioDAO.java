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
import modelo.Laboratorio;

public class LaboratorioDAO {

	public JSONArray getLaboratorio() {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			JSONArray array = new JSONArray();
			stmt = connection.prepareStatement(
					"select * from laboratorio where labo_ativo = true");
			rs = stmt.executeQuery();
			while (rs.next()) {
				try {
					JSONObject json = new JSONObject();
					json.append("Id", rs.getString("labo_id"));
					json.append("area_id", rs.getString("area_id"));
					json.append("labo_nome", rs.getString("labo_nome"));
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

	public List<Laboratorio> getList() {
		List<Laboratorio> laboratorios = new ArrayList<>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stmt = connection.prepareStatement("select lb.labo_id, lb.labo_nome, lb.area_id, dp.area_nome, lb.labo_ativo from " + 
					"laboratorio as lb inner join departamento as dp on(lb.area_id = dp.area_id)");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Laboratorio laboratorio = new Laboratorio();
				laboratorio.setId(rs.getInt("labo_id"));
				laboratorio.setIdArea(rs.getInt("area_id"));
				laboratorio.setAreaNome(rs.getString("area_nome"));
				laboratorio.setNome(rs.getString("labo_nome"));
				laboratorio.setAtivo(rs.getBoolean("labo_ativo"));
				laboratorios.add(laboratorio);
			}

			connection.commit();
			return laboratorios;

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
		Boolean ativo = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stmt = connection.prepareStatement(
					"select * from laboratorio where labo_id =" + id);
			rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				ativo = rs.getBoolean("labo_ativo");
			}
			String sql;
			if (ativo) {
				sql = "Update laboratorio SET labo_ativo = false where labo_id = " + id;
			} else {
				sql = "Update laboratorio SET labo_ativo = true where labo_id = " + id;
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

	public void update(Laboratorio newLaboratorio) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = "Update laboratorio SET labo_nome = ? where labo_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, newLaboratorio.getNome());
			stmt.setInt(2, newLaboratorio.getId());

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

	public static void novoItem(String nome, Integer areaId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = "Insert into laboratorio (labo_nome, area_id, labo_ativo) values (?,?,?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setInt(2, areaId);
			stmt.setBoolean(3, true);

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
					"Delete from laboratorio where labo_id = '" + id + "'");

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

			stmt = connection.prepareStatement("select (SUM(CASE WHEN labo_id = '" + id + "' THEN 1 ELSE 0 END) + " + 
					"SUM(CASE WHEN labo_id = '" + id + "' THEN 1 ELSE 0 END) + " + 
					"SUM(CASE WHEN labo_id = '" + id + "' THEN 1 ELSE 0 END)) as qtd " + 
					"from agenda ");
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
