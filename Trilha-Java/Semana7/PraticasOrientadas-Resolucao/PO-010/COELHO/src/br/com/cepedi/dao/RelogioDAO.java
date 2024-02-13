package br.com.cepedi.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.cepedi.model.Relogio;

public abstract class RelogioDAO {
	
	public static boolean adicionarRelogio(Relogio relogio) {
    	try (Connection con = DAO.conectar();
    		 PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO RELOGIO (id_imovel,ULTIMA_LEITURA , LEITURA_ATUAL) VALUES (? , ? , ?)")){
    		preparedStatement.setInt(1, relogio.getId());
    		preparedStatement.setBigDecimal(2, relogio.getUltimaLeitura());
            preparedStatement.setBigDecimal(3, relogio.getLeituraAtual());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
        	System.err.println("Erro ao adicionar o relogio: " + e);
            return false;
        }
    }

	public static boolean atualizaRelogio(Relogio relogio) {
	    try (Connection con = DAO.conectar();
	         PreparedStatement preparedStatement = con.prepareStatement("UPDATE RELOGIO SET ULTIMA_LEITURA = ?, LEITURA_ATUAL = ? WHERE id_imovel = ?")) {
	        preparedStatement.setBigDecimal(1, relogio.getUltimaLeitura());
	        preparedStatement.setBigDecimal(2, relogio.getLeituraAtual());
	        preparedStatement.setInt(3, relogio.getId());
	        int rowsAffected = preparedStatement.executeUpdate();
	        return rowsAffected > 0; // Retorna true se pelo menos uma linha foi afetada
	    } catch (SQLException e) {
	        System.err.println("Erro ao atualizar o relogio: " + e);
	        return false;
	    }
	}
	
	 public static Relogio buscarRelogio(int idImovel) {
	        Relogio relogio = null;
	        try (Connection con = DAO.conectar();
	             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM RELOGIO WHERE id_imovel = ?")) {
	            preparedStatement.setInt(1, idImovel);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                relogio = criarRelogio(resultSet);
	            }
	        } catch (SQLException e) {
	            System.err.println("Erro ao buscar o relógio por id_imovel: " + e);
	        }
	        return relogio;
	    }

	    private static Relogio criarRelogio(ResultSet resultSet) throws SQLException {
	        int idImovel = resultSet.getInt("id_imovel");
	        BigDecimal ultimaLeitura = resultSet.getBigDecimal("ULTIMA_LEITURA");
	        BigDecimal leituraAtual = resultSet.getBigDecimal("LEITURA_ATUAL");

	        // Supondo que você tenha um construtor na classe Relogio para inicializá-lo com esses valores
	        return new Relogio(idImovel, ultimaLeitura, leituraAtual);
	    }

	

}
