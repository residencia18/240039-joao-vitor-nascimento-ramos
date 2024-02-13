package br.com.cepedi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.cepedi.model.Estado;

public abstract class EstadoDAO {
    
    public static int buscarIDEstado(Estado estado) {
        int id = -1;
        try (Connection con = DAO.conectar();
             PreparedStatement preparedStatement = con.prepareStatement("SELECT ID FROM ESTADO WHERE NOME = ?")) {
            preparedStatement.setString(1, estado.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("ID");
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Estado não encontrado");
    }

}
