package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import modelo.Agenda;
import util.FormatarDatas;

public class AgendaDAO {

	public List<Agenda> getList(Integer areaId, Integer laboId,
			Integer dtInicio, Integer dtFim) throws ParseException {
		List<Agenda> agendas = new ArrayList<>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = ("select agenda_id, dp.area_id, dp.area_nome, lb.labo_id, lb.labo_nome, "
					+ "ag.agenda_data, ag.agenda_turno_manha, ag.agenda_turno_tarde, ag.agenda_turno_noite from agenda as ag "
					+ "inner join departamento as dp on(ag.area_id = dp.area_id) "
					+ "inner join laboratorio as lb on(ag.labo_id = lb.labo_id) "
					+ "where ag.area_id = ? and ag.labo_id = ? and ag.agenda_data >= ? and  ag.agenda_data <= ? order by ag.agenda_data");

			// String sql = ("select * from agenda order by agenda_data");

			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, areaId);
			stmt.setInt(2, laboId);
			stmt.setInt(3, dtInicio);
			stmt.setInt(4, dtFim);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Agenda agenda = new Agenda();
				agenda.setId(rs.getInt("agenda_id"));
				agenda.setAreaId(rs.getInt("area_id"));
				agenda.setAreaNome(rs.getString("area_nome"));
				agenda.setLaboratorioId(rs.getInt("labo_id"));
				agenda.setLaboratorioNome(rs.getString("labo_nome"));
				agenda.setDate(FormatarDatas
						.converterIntegertoDate(rs.getInt("agenda_data"))
						.getTime());
				agenda.setTurnoManha(rs.getString("agenda_turno_manha"));
				agenda.setTurnoTarde(rs.getString("agenda_turno_tarde"));
				agenda.setTurnoNoite(rs.getString("agenda_turno_noite"));
				agendas.add(agenda);
			}

			connection.commit();
			return agendas;

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

	public Integer contarItem(Integer areaId, Integer laboId, Integer dtInicio,
			Integer dtFim) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Integer resultado = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = ("select count(*) as qtd from agenda where area_id = ? and labo_id = ? and agenda_data >= ? and agenda_data <= ?");

			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, areaId);
			stmt.setInt(2, laboId);
			stmt.setInt(3, dtInicio);
			stmt.setInt(4, dtFim);
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

	public void alterarTurnoManha(String texto, Integer id) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = "Update agenda SET agenda_turno_manha = ? where agenda_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, texto);
			stmt.setInt(2, id);

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

	public void alterarTurnoTarde(String texto, Integer id) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = "Update agenda SET agenda_turno_tarde = ? where agenda_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, texto);
			stmt.setInt(2, id);

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

	public void alterarTurnoNoite(String texto, Integer id) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = "Update agenda SET agenda_turno_noite = ? where agenda_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, texto);
			stmt.setInt(2, id);

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

	public void novoItem(Integer area_id, Integer labo_id, Integer dt_inicio,
			Integer dt_fim) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			// dt_fim = new Date(dt_fim.getTime() + 86400000);

			while (dt_inicio <= dt_fim) {
				String sql = "insert into agenda(area_id, labo_id, agenda_data, "
						+ "agenda_turno_manha, agenda_turno_tarde, agenda_turno_noite) "
						+ "value (?,?,?,'Livre','Livre','Livre')";
				stmt = connection.prepareStatement(sql);
				stmt.setInt(1, area_id);
				stmt.setInt(2, labo_id);
				stmt.setInt(3, dt_inicio);

				stmt.execute();
				connection.commit();

				dt_inicio = FormatarDatas.incrementarData(dt_inicio);

			}

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

	public Integer getMaxData(Integer areaId, Integer laboId, Integer dtInicio,
			Integer dtFim) throws ParseException {
		Integer data = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = ("select max(agenda_data) as dt from agenda where area_id = ? and labo_id = ? and agenda_data >= ? and  agenda_data <= ? ");

			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, areaId);
			stmt.setInt(2, laboId);
			stmt.setInt(3, dtInicio);
			stmt.setInt(4, dtFim);

			rs = stmt.executeQuery();

			while (rs.next()) {
				data = rs.getInt("dt");
			}
			connection.commit();
			return data;

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

	public void registro(String matricula, Integer id, String turno, String acao) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = "insert into historico(agenda_id, agenda_turno, agenda_acao, usu_mat) value (?,?,?,?)";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setString(2, turno);
			stmt.setString(3, acao);
			stmt.setString(4, matricula);

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
