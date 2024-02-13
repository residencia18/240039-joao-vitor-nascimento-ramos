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
import br.com.cepedi.model.Pagamento;
import br.com.cepedi.model.Reembolso;

public abstract class PagamentoDAO {

	   public static boolean adicionarPagamento(Pagamento pagamento ,int idFatura) {
	        try (Connection con = DAO.conectar();
	             PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO PAGAMENTO (DATA, VALOR, id_fatura) VALUES (?, ?, ?)")) {
	            preparedStatement.setDate(1, java.sql.Date.valueOf(pagamento.getData()));
	            preparedStatement.setBigDecimal(2, pagamento.getValor());
	            preparedStatement.setInt(3, idFatura);

	            preparedStatement.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            System.err.println("Erro ao adicionar o pagamento: " + e);
	            return false;
	        }
	    }
	   
	    public static boolean atualizaPagamento(Pagamento pagamento) {
	        try (Connection con = DAO.conectar();
	             PreparedStatement preparedStatement = con.prepareStatement("UPDATE PAGAMENTO SET DATA = ?, VALOR = ? WHERE ID = ?")) {
	            preparedStatement.setDate(1, java.sql.Date.valueOf(pagamento.getData()));
	            preparedStatement.setBigDecimal(2, pagamento.getValor());
	            preparedStatement.setInt(3, pagamento.getId());

	            int rowsAffected = preparedStatement.executeUpdate();
	            return rowsAffected > 0; 
	        } catch (SQLException e) {
	            System.err.println("Erro ao atualizar o pagamento: " + e);
	            return false;
	        }
	    }

	    public static Pagamento buscarPagamentoPorId(int idPagamento) {
	        try (Connection con = DAO.conectar();
	             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM PAGAMENTO WHERE ID = ?")) {
	            preparedStatement.setInt(1, idPagamento);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                return criarPagamento(resultSet);
	            }
	        } catch (SQLException e) {
	            System.err.println("Erro ao buscar pagamento por ID: " + e);
	        }
	        return null; 
	    }

	    private static Pagamento criarPagamento(ResultSet resultSet) throws SQLException {
	        int id = resultSet.getInt("ID");
	        LocalDate data = resultSet.getDate("DATA").toLocalDate();
	        BigDecimal valor = resultSet.getBigDecimal("VALOR");

	        int idFatura = resultSet.getInt("id_fatura");
	        // Aqui você precisa implementar a lógica para recuperar a fatura com base no idFatura
	        Fatura fatura = FaturaDAO.buscarFaturaPorId(idFatura); // Supondo que você tenha um método para buscar fatura por ID

	        return new Pagamento(id, data, valor);
	    }
	    
	    public static int proximoId() {
	        int proximoId = 0;
	        try (Connection con = DAO.conectar();
	             PreparedStatement preparedStatement = con.prepareStatement("SHOW TABLE STATUS LIKE 'PAGAMENTO'");
	             ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                proximoId = resultSet.getInt("Auto_increment");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return proximoId;
	    }
	    
	    public static List<Pagamento> listarPagamentosPorFatura(int idFatura) {
	        List<Pagamento> pagamentos = new ArrayList<>();
	        try (Connection con = DAO.conectar();
	             PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM PAGAMENTO WHERE id_fatura = ?")) {
	            preparedStatement.setInt(1, idFatura);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                Pagamento pagamento = criarPagamento(resultSet);
	                pagamentos.add(pagamento);
	            }
	        } catch (SQLException e) {
	            System.err.println("Erro ao buscar pagamentos por id de fatura: " + e);
	        }
	        return pagamentos;
	    }

	    public static List<Pagamento> listarPagamentosComReembolsos(int idFatura) {
	        List<Pagamento> pagamentosComReembolsos = new ArrayList<>();
	        try (Connection con = DAO.conectar();
	             PreparedStatement preparedStatement = con.prepareStatement("SELECT P.* FROM PAGAMENTO P INNER JOIN REEMBOLSO R ON P.ID = R.id_pagamento WHERE P.id_fatura = ?")) {
	            preparedStatement.setInt(1, idFatura);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                Pagamento pagamento = criarPagamento(resultSet);
	                pagamentosComReembolsos.add(pagamento);
	            }
	        } catch (SQLException e) {
	            System.err.println("Erro ao buscar pagamentos com reembolsos: " + e);
	        }
	        return pagamentosComReembolsos;
	    }

	    
	    private static Reembolso criarReembolso(ResultSet resultSet) throws SQLException {
	        int id = resultSet.getInt("ID");
	        LocalDate data = resultSet.getDate("DATA").toLocalDate();
	        BigDecimal valor = resultSet.getBigDecimal("VALOR");
	        return new Reembolso(data, valor);
	    }
	
}
