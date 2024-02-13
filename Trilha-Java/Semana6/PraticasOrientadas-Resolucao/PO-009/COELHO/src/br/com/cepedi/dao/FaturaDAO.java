package br.com.cepedi.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.cepedi.model.Fatura;

public abstract class FaturaDAO {
	
	public static boolean adicionarFatura(Fatura fatura, int idImovel) {
        try (Connection con = DAO.conectar();
             PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO FATURA (DATA, VALOR, ULTIMA_LEITURA, LEITURA_ATUAL, QUITADO , id_imovel) VALUES (?, ?, ?, ?, ? ,?)")) {
            preparedStatement.setDate(1, java.sql.Date.valueOf(fatura.getData()));
            preparedStatement.setBigDecimal(2, fatura.getValor());
            preparedStatement.setBigDecimal(3, fatura.getUltimaLeitura());
            preparedStatement.setBigDecimal(4, fatura.getLeituraAtual());
            preparedStatement.setBoolean(5, fatura.isQuitado());
            preparedStatement.setInt(6, idImovel);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar a fatura: " + e);
            return false;
        }
    }
	
	public static Fatura buscarFaturaPorId(int idFatura) {
        try (Connection con = DAO.conectar();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM FATURA WHERE ID = ?")) {
            preparedStatement.setInt(1, idFatura);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return criarFatura(resultSet);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar fatura por ID: " + e);
        }
        return null; 
    }
	
    public static List<Fatura> listarFaturasPorIdImovel(int idImovel) {
        List<Fatura> faturas = new ArrayList<>();
        try (Connection con = DAO.conectar();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM FATURA WHERE id_imovel = ?")) {
            preparedStatement.setInt(1, idImovel);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Fatura fatura = criarFatura(resultSet);
                faturas.add(fatura);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar faturas por id_imovel: " + e);
        }
        return faturas;
    }
    
    public static List<Fatura> listarFaturasEmAbertoPorIdImovel(int idImovel) {
        List<Fatura> faturas = new ArrayList<>();
        try (Connection con = DAO.conectar();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM FATURA WHERE id_imovel = ? AND QUITADO = false")) {
            preparedStatement.setInt(1, idImovel);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Fatura fatura = criarFatura(resultSet);
                faturas.add(fatura);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar faturas em aberto por id_imovel: " + e);
        }
        return faturas;
    }

    private static Fatura criarFatura(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        LocalDate data = resultSet.getDate("DATA").toLocalDate();
        BigDecimal valor = resultSet.getBigDecimal("VALOR");
        BigDecimal ultimaLeitura = resultSet.getBigDecimal("ULTIMA_LEITURA");
        BigDecimal leituraAtual = resultSet.getBigDecimal("LEITURA_ATUAL");
        boolean quitado = resultSet.getBoolean("QUITADO");

        return new Fatura(id, data, valor, ultimaLeitura, leituraAtual, quitado);
    }
    
    public static boolean atualizarFatura(Fatura fatura) {
        try (Connection con = DAO.conectar();
             PreparedStatement preparedStatement = con.prepareStatement("UPDATE FATURA SET DATA = ?, VALOR = ?, ULTIMA_LEITURA = ?, LEITURA_ATUAL = ?, QUITADO = ? WHERE ID = ?")) {
            preparedStatement.setDate(1, java.sql.Date.valueOf(fatura.getData()));
            preparedStatement.setBigDecimal(2, fatura.getValor());
            preparedStatement.setBigDecimal(3, fatura.getUltimaLeitura());
            preparedStatement.setBigDecimal(4, fatura.getLeituraAtual());
            preparedStatement.setBoolean(5, fatura.isQuitado());
            preparedStatement.setInt(6, fatura.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; 
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a fatura: " + e);
            return false;
        }
    }
}
