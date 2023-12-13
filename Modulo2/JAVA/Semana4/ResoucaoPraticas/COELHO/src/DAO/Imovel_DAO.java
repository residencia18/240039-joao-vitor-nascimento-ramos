package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MODEL.Imovel;

public class Imovel_DAO {
    // Método para adicionar um imóvel ao banco de dados
    public void adicionarImovel(Imovel imovel) {
        DAO dao = new DAO();
        Connection con = dao.conectar();
        String query = "INSERT INTO Imovel (matricula, endereco, ultimaLeitura, penultimaLeitura,id_cliente) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, imovel.getMatricula());
            preparedStatement.setString(2, imovel.getEndereco());
            preparedStatement.setInt(3, imovel.getUltimaLeitura());
            preparedStatement.setInt(4, imovel.getPenultimaLeitura());
			preparedStatement.setInt(5, imovel.getCliente().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeConnection(con);
        }
    }

    // Método para obter um imóvel do banco de dados pelo ID
    public Imovel obterImovelPorId(int id) {
        DAO dao = new DAO();
        Connection con = dao.conectar();
        Imovel imovel = null;
        String query = "SELECT * FROM Imovel WHERE id = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                imovel = new Imovel();
                imovel.setId(resultSet.getInt("id"));
                imovel.setMatricula(resultSet.getString("matricula"));
                imovel.setEndereco(resultSet.getString("endereco"));
                imovel.setUltimaLeitura(resultSet.getInt("ultimaLeitura"));
                imovel.setPenultimaLeitura(resultSet.getInt("penultimaLeitura"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeConnection(con);
        }

        return imovel;
    }
    
    public Imovel obterImovelPorMatricula(String matricula) {
        DAO dao = new DAO();
        Connection con = dao.conectar();
        Imovel imovel = null;
        String query = "SELECT * FROM Imovel WHERE matricula = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, matricula);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                imovel = new Imovel();
                imovel.setId(resultSet.getInt("id"));
                imovel.setMatricula(resultSet.getString("matricula"));
                imovel.setEndereco(resultSet.getString("endereco"));
                imovel.setUltimaLeitura(resultSet.getInt("ultimaLeitura"));
                imovel.setPenultimaLeitura(resultSet.getInt("penultimaLeitura"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeConnection(con);
        }

        return imovel;
    }

    // Método para obter todos os imóveis do banco de dados
    public List<Imovel> obterTodosImoveis() {
        DAO dao = new DAO();
        Connection con = dao.conectar();
        List<Imovel> imoveis = new ArrayList<>();
        String query = "SELECT * FROM Imovel";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Imovel imovel = new Imovel();
                imovel.setId(resultSet.getInt("id"));
                imovel.setMatricula(resultSet.getString("matricula"));
                imovel.setEndereco(resultSet.getString("endereco"));
                imovel.setUltimaLeitura(resultSet.getInt("ultimaLeitura"));
                imovel.setPenultimaLeitura(resultSet.getInt("PenultimaLeitura"));
                imoveis.add(imovel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeConnection(con);
        }

        return imoveis;
    }

    // Método para atualizar um imóvel no banco de dados
    public void atualizarImovel(Imovel imovel) {
        DAO dao = new DAO();
        Connection con = dao.conectar();
        String query = "UPDATE Imovel SET matricula = ?, endereco = ?, ultimaLeitura = ?, penultimaLeitura = ? WHERE id = ?";
        
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, imovel.getMatricula());
            preparedStatement.setString(2, imovel.getEndereco());
            preparedStatement.setInt(3, imovel.getUltimaLeitura());
            preparedStatement.setInt(4, imovel.getPenultimaLeitura());
            preparedStatement.setInt(5, imovel.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeConnection(con);
        }
    }

    // Método para excluir um imóvel do banco de dados
    public void excluirImovelPorMatricula(String matricula) {
        DAO dao = new DAO();
        Connection con = dao.conectar();
        String query = "DELETE FROM Imovel WHERE matricula = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, matricula);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeConnection(con);
        }
    }

}
