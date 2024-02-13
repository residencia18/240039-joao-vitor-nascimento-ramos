package br.com.cepedi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.cepedi.model.Endereco;

public  abstract class EnderecoDAO {
	
	public static boolean adicionarEndereco(Endereco endereco) {
    	try (Connection con = DAO.conectar();
    		 PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO ENDERECO (RUA, NUMERO , BAIRRO , id_Estado , CEP , id_imovel)"
    		 		+ " VALUES (?, ? , ? , ? , ?, ?)")) {
            preparedStatement.setString(1, endereco.getRua());
            preparedStatement.setInt(2, endereco.getNumero());
            preparedStatement.setString(3, endereco.getBairro());
            preparedStatement.setInt(4,EstadoDAO.buscarIDEstado(endereco.getEstado()));
            preparedStatement.setString(5,endereco.getCEP());
            preparedStatement.setInt(6,endereco.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
        	System.err.println("Erro ao adicionar o endereco: " + e);
            return false;
        }
    }
	
	public static boolean atualizaEndereco(Endereco endereco) {
	    try (Connection con = DAO.conectar();
	         PreparedStatement preparedStatement = con.prepareStatement("UPDATE ENDERECO SET RUA = ?, NUMERO = ?, BAIRRO = ?, id_Estado = ?, CEP = ? WHERE id_imovel = ?")) {
	        preparedStatement.setString(1, endereco.getRua());
	        preparedStatement.setInt(2, endereco.getNumero());
	        preparedStatement.setString(3, endereco.getBairro());
	        preparedStatement.setInt(4, EstadoDAO.buscarIDEstado(endereco.getEstado()));
	        preparedStatement.setString(5, endereco.getCEP());
	        preparedStatement.setInt(6, endereco.getId());
	        
	        int rowsAffected = preparedStatement.executeUpdate();
	        return rowsAffected > 0; // Retorna true se pelo menos uma linha foi afetada
	    } catch (SQLException e) {
	        System.err.println("Erro ao atualizar o endereco: " + e);
	        return false;
	    }
	}

}
