package br.com.cepedi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.cepedi.model.Reembolso;

public abstract class ReembolsoDAO {
	
	public static boolean adicionarReembolso(Reembolso reembolso , int idPagamento) {
        try (Connection con = DAO.conectar();
             PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO REEMBOLSO (DATA, VALOR,  id_pagamento) VALUES (?, ? , ?)")) {
            preparedStatement.setDate(1, java.sql.Date.valueOf(reembolso.getData()));
            preparedStatement.setBigDecimal(2, reembolso.getValor());
            preparedStatement.setInt(3, idPagamento);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar o reembolso: " + e);
            return false;
        }
    }

}
