package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import MODEL.Fatura;
import MODEL.Imovel;

public class Fatura_DAO {

    private static final Imovel_DAO imovelDAO = new Imovel_DAO();

	
    public void adicionarFatura(Fatura fatura) {
        DAO dao = new DAO();
        Connection con = dao.conectar();

        String query = "INSERT INTO Fatura (data, ultimaLeitura, PenultimaLeitura, valor, quitado, id_imovel) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setDate(1, java.sql.Date.valueOf(fatura.getData()));
            preparedStatement.setInt(2, fatura.getUltimaLeitura());
            preparedStatement.setInt(3, fatura.getPenultimaLeitura());
            preparedStatement.setDouble(4, fatura.getValor());
            preparedStatement.setBoolean(5, fatura.isQuitado());
            preparedStatement.setInt(6, fatura.getImovel().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeConnection(con);
        }
    }

    public ArrayList<Fatura> obterFaturasPorImovel(int imovelId) {
    	ArrayList<Fatura> faturas = new ArrayList<>();
        DAO dao = new DAO();
        Connection con = dao.conectar();

        String query = "SELECT * FROM Fatura WHERE id_imovel = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, imovelId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    faturas.add(criarFatura(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeConnection(con);
        }

        return faturas;
    }
    
    public Fatura obterFaturaPorNumero(int idFatura) {
        DAO dao = new DAO();
        Connection con = dao.conectar();
        String query = "SELECT * FROM Fatura WHERE id = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, idFatura);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return criarFatura(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeConnection(con);
        }
        return null;
    }
    
	public void atualizarFatura(Fatura fatura) {
		DAO dao = new DAO();
		Connection con = dao.conectar();
		String query = "UPDATE Fatura SET  valor = ?, quitado = ? WHERE id = ?";
		
		try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
			preparedStatement.setDouble(1, fatura.getValor());
			preparedStatement.setBoolean(2, fatura.isQuitado());
			preparedStatement.setInt(3, fatura.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.closeConnection(con);
		}
	}

    private Fatura criarFatura(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        LocalDate data = resultSet.getDate("data").toLocalDate();
        int ultimaLeitura = resultSet.getInt("ultimaLeitura");
        int penultimaLeitura = resultSet.getInt("penultimaLeitura");
        double valor = resultSet.getDouble("valor");
        boolean quitado = resultSet.getBoolean("quitado");
        int imovelId = resultSet.getInt("id_imovel");

        Imovel imovel = imovelDAO.obterImovelPorId(imovelId);

        Fatura fatura = new Fatura();
        fatura.setId(id);
        fatura.setData(data);
        fatura.setUltimaLeitura(ultimaLeitura);
        fatura.setPenultimaLeitura(penultimaLeitura);
        fatura.setValor(valor);
        fatura.setQuitado(quitado);
        fatura.setImovel(imovel);

        return fatura;
    }
}
