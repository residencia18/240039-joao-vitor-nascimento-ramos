package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import MODEL.Leitura;

public class Leitura_DAO {
	
	// Método para adicionar uma leitura
	public void adicionarLeitura(Leitura leitura) {
		DAO dao = new DAO();
		Connection con = dao.conectar();
		String query = "INSERT INTO Leitura (leitura, id_imovel) VALUES (?, ?)";

		try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
			preparedStatement.setInt(1, leitura.getLeitura());
			preparedStatement.setInt(2, leitura.getId_imovel());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection(con);
		}
	}

	// Método para obter uma leitura pelo ID
	public Leitura obterLeituraPorID(int id) {
		DAO dao = new DAO();
		Connection con = dao.conectar();
		Leitura leitura = null;
		String query = "SELECT * FROM Leitura WHERE id = ?";

		try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				leitura = new Leitura();
				leitura.setId(resultSet.getInt("id"));
				leitura.setLeitura(resultSet.getInt("leitura"));
				leitura.setId_imovel(resultSet.getInt("id_imovel"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection(con);
		}

		return leitura;
	}
	
	// Método para obter todas as leituras
	public ArrayList<Leitura> obterLeituras(int id_imovel) {
		DAO dao = new DAO();
		Connection con = dao.conectar();
		Leitura leitura = null;
		ArrayList<Leitura> leituras = new ArrayList<>();
		String query = "SELECT * FROM Leitura WHERE id_imovel = ?";

		try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
			preparedStatement.setInt(1, id_imovel);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				leitura = new Leitura();
				leitura.setId(resultSet.getInt("id"));
				leitura.setLeitura(resultSet.getInt("leitura"));
				leitura.setId_imovel(resultSet.getInt("id_imovel"));
				leituras.add(leitura);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection(con);
		}
		return leituras;
	}

	// Método para atualizar uma leitura
	public void atualizarLeitura(Leitura leitura) {
		DAO dao = new DAO();
		Connection con = dao.conectar();
		String query = "UPDATE Leitura SET leitura = ? WHERE id = ?";

		try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
			preparedStatement.setInt(1, leitura.getLeitura());
			preparedStatement.setInt(2, leitura.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection(con);
		}
	}

	// Método para excluir uma leitura
	public void excluirLeitura(int id) {
		DAO dao = new DAO();
		Connection con = dao.conectar();
		String query = "DELETE FROM Leitura WHERE id = ?";

		try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection(con);
		}
	}

}
