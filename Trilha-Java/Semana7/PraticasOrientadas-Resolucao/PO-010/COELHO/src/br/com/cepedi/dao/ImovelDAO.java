package br.com.cepedi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.cepedi.exceptions.ConjuntoClientes.ClienteNaoEncontradoException;
import br.com.cepedi.exceptions.imovel.ImovelNaoEncontrado;
import br.com.cepedi.model.Cliente;
import br.com.cepedi.model.Imovel;

public abstract class ImovelDAO {
	
	public static boolean adicionarImovel(Imovel imovel) {
    	try (Connection con = DAO.conectar();
    		 PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO IMOVEL (MATRICULA)"
    		 		+ " VALUES (?)")) {
            preparedStatement.setString(1, imovel.getMatricula());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
        	System.err.println("Erro ao adicionar o imovel: " + e);
            return false;
        }
    }
	
    public static int buscarIdPorMatricula(String matricula) {
        int id = -1; // Valor padrão se o imóvel não for encontrado
        try (Connection con = DAO.conectar();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT ID FROM IMOVEL WHERE MATRICULA = ?")) {
            preparedStatement.setString(1, matricula);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o ID do imóvel por matrícula: " + e);
        }
        throw new IllegalArgumentException("Imovel não encontrado");
    }
    
	public static void atualizarImovel(Imovel imovel) {
    	Connection con = DAO.conectar();
        String query = "UPDATE IMOVEL SET matricula = ?, id_Cliente = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, imovel.getMatricula());
            preparedStatement.setInt(2, imovel.getProprietario().getId());
            preparedStatement.setInt(3, imovel.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	DAO.closeConnection(con);
        }
    }
	
	public static Imovel buscarImovel(int id) throws ImovelNaoEncontrado, ClienteNaoEncontradoException  {
		try (Connection con = DAO.conectar();
			 PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM IMOVEL WHERE ID = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return criarImovel(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		throw new ImovelNaoEncontrado();
    }
	
	 public static boolean deletar(int id) {
	        try (Connection con = DAO.conectar();
	             PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM IMOVEL WHERE ID = ?")) {
	            preparedStatement.setInt(1, id);
	            int rowsAffected = preparedStatement.executeUpdate();
	            return rowsAffected > 0; // Retorna true se pelo menos uma linha foi afetada
	        } catch (SQLException e) {
	            System.err.println("Erro ao deletar o imóvel: " + e);
	            return false;
	        }
	    }
	
	private static Imovel criarImovel(ResultSet resultSet) throws SQLException, ClienteNaoEncontradoException {
	    int id = resultSet.getInt("ID");
	    String matricula = resultSet.getString("MATRICULA");
	    // Supondo que o cliente seja armazenado como uma referência para o proprietário na tabela
	    int idCliente = resultSet.getInt("id_Cliente");
	    
	    // Aqui você precisa implementar a lógica para recuperar o cliente com base no idCliente
	    Cliente proprietario = ClienteDAO.buscarCliente(idCliente); // Supondo que você tenha um método para buscar cliente por ID
	    
	    // Aqui você precisa criar o objeto Imovel com os dados do ResultSet
	    Imovel imovel = new Imovel(matricula);
	    imovel.setId(id);
	    imovel.setProprietario(proprietario);
	    
	    return imovel;
	}
	
    public static int proximoId() {
        int proximoId = 0;
        try (Connection con = DAO.conectar();
             PreparedStatement preparedStatement = con.prepareStatement("SHOW TABLE STATUS LIKE 'IMOVEL'");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                proximoId = resultSet.getInt("Auto_increment");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proximoId;
    }
    
    public static List<Imovel> obterTodosImoveis() {
        List<Imovel> imoveis = new ArrayList<>();
        try (Connection con = DAO.conectar();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM IMOVEL");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Imovel imovel = criarImovel(resultSet);
                imoveis.add(imovel);
            }
        } catch (SQLException | ClienteNaoEncontradoException e) {
            e.printStackTrace();
        }
        return imoveis;
    }

}
