package br.com.cepedi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.cepedi.conjuntos.Clientes;
import br.com.cepedi.exceptions.ConjuntoClientes.ClienteNaoEncontradoException;
import br.com.cepedi.exceptions.cliente.CPFPessoaInvalidoException;
import br.com.cepedi.exceptions.cliente.NomePessoaInvalidoException;
import br.com.cepedi.model.Cliente;

public abstract class ClienteDAO {
	
	public static boolean adicionarCliente(Cliente cliente) {
    	try (Connection con = DAO.conectar();
    		 PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO CLIENTE (NOME, CPF) VALUES (?, ?)")) {
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCpf());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
        	System.err.println("Erro ao adicionar o cliente: " + e);
            return false;
        }
    }
	
	public static Cliente buscarCliente(int id) throws ClienteNaoEncontradoException {
		try (Connection con = DAO.conectar();
			 PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM CLIENTE WHERE ID = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return criarCliente(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		throw new ClienteNaoEncontradoException();
    }
	
	public static Clientes obterTodosClientes() {
    	Connection con = DAO.conectar();
    	Clientes clientes = new Clientes();
    	String query = "SELECT * FROM CLIENTE";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente = criarCliente(resultSet);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	DAO.closeConnection(con);
        }

        return clientes;
    }
	
	public static void atualizarCliente(Cliente cliente) {
    	Connection con = DAO.conectar();
        String query = "UPDATE CLIENTE SET nome = ?, cpf = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCpf());
            preparedStatement.setInt(3, cliente.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	DAO.closeConnection(con);
        }
    }
	
    public static void excluirCliente(int id) {
    	Connection con = DAO.conectar();
        String query = "DELETE FROM CLIENTE WHERE id = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	DAO.closeConnection(con);
        }
    }
	
	private static Cliente criarCliente(ResultSet resultSet) throws SQLException {
		Cliente cliente = new Cliente();
        cliente.setId(resultSet.getInt("ID"));
        try {
			cliente.setNome(resultSet.getString("NOME"));
		} catch (NomePessoaInvalidoException | CPFPessoaInvalidoException e) {
			System.err.println("Erro ao definir o nome do cliente: " + e);
		}
        try {
			cliente.setCpf(resultSet.getString("CPF"));
		} catch (CPFPessoaInvalidoException e) {
			System.err.println("Erro ao definir o CPF do cliente: " + e);
		} catch (NomePessoaInvalidoException e) {
			System.err.println("Erro ao definir o nome do cliente: " + e);
		}
        return cliente;
	}
	
	
	
}
