package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import modelo.Usuario;
import util.FormatarDatas;

public class UsuarioDAO {

	public void adiciona(Usuario usuario) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = "Insert into usuario (usu_mat, usu_nome, usu_senha, usu_email, usu_mailconfir, perfil_id, usu_ativo)"
					+ " values (?,?,?,?,?,?,?)";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getMatricula());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getSenha());
			stmt.setString(4, usuario.getEmail());
			stmt.setBoolean(5, usuario.isMailConfirm());
			stmt.setInt(6, 1);
			stmt.setBoolean(7, true);

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

	public Usuario existeUsuario(Usuario usuario) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = ("select * from usuario as us inner join perfil as pf on(us.perfil_id = pf.perfil_id) where us.usu_mat = ?");
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getMatricula());
			rs = stmt.executeQuery();

			if (rs.next()) {
				Usuario user = new Usuario();
				user.setId(rs.getInt("usu_id"));
				user.setMatricula(rs.getString("usu_mat"));
				user.setNome(rs.getString("usu_nome"));
				user.setSenha(rs.getString("usu_senha"));
				user.setEmail(rs.getString("usu_email"));
				user.setMailConfirm(rs.getBoolean("usu_mailconfir"));
				user.setPerfil(rs.getInt("perfil_id"));
				user.setPerfilNome(rs.getString("perfil_nome"));
				user.setAtivo(rs.getBoolean("usu_ativo"));
				user.setAcessos(rs.getInt("usu_acessos"));
				user.setUltimoAcesso(rs.getString("usu_ultimoAcesso"));
				return user;
			}

			connection.commit();
			return null;

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

	public Usuario existeEmail(Usuario usuario) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = ("select * from usuario as us inner join perfil as pf on(us.perfil_id = pf.perfil_id) where us.usu_email = ?");
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getEmail());
			rs = stmt.executeQuery();

			if (rs.next()) {
				Usuario user = new Usuario();
				user.setId(rs.getInt("usu_id"));
				user.setMatricula(rs.getString("usu_mat"));
				user.setNome(rs.getString("usu_nome"));
				user.setSenha(rs.getString("usu_senha"));
				user.setEmail(rs.getString("usu_email"));
				user.setMailConfirm(rs.getBoolean("usu_mailconfir"));
				user.setPerfil(rs.getInt("perfil_id"));
				user.setPerfilNome(rs.getString("perfil_nome"));
				return user;
			}
			connection.commit();
			return null;

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

	public List<Usuario> getList() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stmt = connection.prepareStatement("select * from usuario as us inner join perfil as pf on(us.perfil_id = pf.perfil_id)");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Usuario user = new Usuario();
				user.setId(rs.getInt("usu_id"));
				user.setMatricula(rs.getString("usu_mat"));
				user.setNome(rs.getString("usu_nome"));
				user.setEmail(rs.getString("usu_email"));
				user.setMailConfirm(rs.getBoolean("usu_mailconfir"));
				user.setPerfil(rs.getInt("perfil_id"));
				user.setPerfilNome(rs.getString("perfil_nome"));
				user.setAcessos(rs.getInt("usu_acessos"));
				user.setUltimoAcesso(rs.getString("usu_ultimoAcesso"));
				user.setAtivo(rs.getBoolean("usu_ativo"));
				usuarios.add(user);
			}

			connection.commit();
			return usuarios;

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

	public void alterarSenha(Usuario usuario) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = "Update usuario SET usu_senha = ?, usu_mailconfir = ? where usu_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getSenha());
			stmt.setBoolean(2, true);
			stmt.setInt(3, usuario.getId());

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

	public void setAcesso(Usuario usuario) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = "Update usuario SET usu_acessos = ?, usu_ultimoAcesso = ? where usu_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, usuario.getAcessos() + 1);
			try {
				stmt.setString(2, FormatarDatas.dataAtual());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			stmt.setInt(3, usuario.getId());

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

	public void alterarStatus(String id) {
		Connection connection = null;
		PreparedStatement stmt = null, stmt2 = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stmt = connection.prepareStatement(
					"select * from usuario where usu_id =" + id);
			rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
			while (rs.next()) {
				usuario.setAtivo(rs.getBoolean("usu_ativo"));
			}

			String sql;
			if (usuario.isAtivo()) {
				sql = "Update usuario SET usu_ativo = false where usu_id = "
						+ id;
			} else {
				sql = "Update usuario SET usu_ativo = true where usu_id = "
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public void update(Usuario newUsuario) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			String sql = "Update usuario SET usu_mat = ?, usu_nome = ?, usu_email=?, perfil_id = ? where usu_id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, newUsuario.getMatricula());
			stmt.setString(2, newUsuario.getNome());
			stmt.setString(3, newUsuario.getEmail());
			stmt.setInt(4, newUsuario.getPerfil());
			stmt.setInt(5, newUsuario.getId());

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
					"Delete from usuario where usu_id = '" + id + "'");

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

	public Integer contarItem(String matricula) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Integer resultado = null;

		try {
			connection = new ConnectionFactory().getConnection();
			connection.setAutoCommit(false);

			stmt = connection.prepareStatement("select (SUM(CASE WHEN agenda_turno_manha = '" + matricula + "' THEN 1 ELSE 0 END) + " + 
					"SUM(CASE WHEN agenda_turno_tarde = '" + matricula + "' THEN 1 ELSE 0 END) + " + 
					"SUM(CASE WHEN agenda_turno_noite = '" + matricula + "' THEN 1 ELSE 0 END)) as qtd " + 
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
