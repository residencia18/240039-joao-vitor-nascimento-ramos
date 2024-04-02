package br.com.alura.bytebank.domain.conta;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import br.com.alura.bytebank.domain.cliente.Cliente;
import br.com.alura.bytebank.domain.cliente.DadosCadastroCliente;

public abstract class ContaDAO {

	public static void salvar(Connection connection, DadosAberturaConta dadosDaConta) {
		var cliente = new Cliente(dadosDaConta.dadosCliente());
		var conta = new Conta(dadosDaConta.numero(), cliente);

		String sql = "INSERT INTO conta(numero,saldo,cliente_nome,cliente_cpf,cliente_email, esta_ativa) "
				+ " VALUES(? , ? , ? , ? , ? , ? )";

		try {
			PreparedStatement prepare = connection.prepareStatement(sql);
			prepare.setInt(1, conta.getNumero());
			prepare.setBigDecimal(2, conta.getSaldo());
			prepare.setString(3, cliente.getNome());
			prepare.setString(4, cliente.getCpf());
			prepare.setString(5, cliente.getEmail());
			prepare.setBoolean(6,true);
			prepare.execute();
			prepare.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Set<Conta> listar(Connection connection) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<Conta> contas = new HashSet<>();

		String sql = "SELECT * FROM conta WHERE esta_ativa=true";

		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Integer numero = rs.getInt(1);
				BigDecimal saldo = rs.getBigDecimal(2);
				String cliente_nome = rs.getString(3);
				String cliente_cpf = rs.getString(4);
				String cliente_email = rs.getString(5);
				DadosCadastroCliente dadosCliente = new DadosCadastroCliente(cliente_nome, cliente_cpf, cliente_email);
				Cliente cliente = new Cliente(dadosCliente);
				Conta conta = new Conta(numero, cliente, saldo);
				contas.add(conta);

			}
			rs.close();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		return contas;

	}

	public static Conta contaPorNumero(Connection connection, Integer numero) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Conta conta = null;
		String sql = "SELECT * FROM conta WHERE numero = ? AND esta_ativa = true";

		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, numero);
			rs = ps.executeQuery();

			if (rs.next()) {
				BigDecimal saldo = rs.getBigDecimal(2);
				String cliente_nome = rs.getString(3);
				String cliente_cpf = rs.getString(4);
				String cliente_email = rs.getString(5);
				DadosCadastroCliente dadosCliente = new DadosCadastroCliente(cliente_nome, cliente_cpf, cliente_email);
				Cliente cliente = new Cliente(dadosCliente);
				conta = new Conta(numero, cliente, saldo);
			}

			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (conta == null) {
			throw new IllegalArgumentException("Conta não encontrada");
		}

		return conta;
	}
	
	public static Conta contaPorNumeroInativa(Connection connection, Integer numero) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Conta conta = null;
		String sql = "SELECT * FROM conta WHERE numero = ? AND esta_ativa = false";

		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, numero);
			rs = ps.executeQuery();

			if (rs.next()) {
				BigDecimal saldo = rs.getBigDecimal(2);
				String cliente_nome = rs.getString(3);
				String cliente_cpf = rs.getString(4);
				String cliente_email = rs.getString(5);
				boolean isAtiva = rs.getBoolean(6);
				DadosCadastroCliente dadosCliente = new DadosCadastroCliente(cliente_nome, cliente_cpf, cliente_email);
				Cliente cliente = new Cliente(dadosCliente);
				conta = new Conta(numero, cliente, saldo,isAtiva);
			}

			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (conta == null) {
			throw new IllegalArgumentException("Conta não encontrada");
		}

		return conta;
	}

	public static void alterar(Connection connection, Integer numero, BigDecimal valor) {

		PreparedStatement ps;
		String sql = "UPDATE conta SET saldo = ? where numero = ?";

		try {
			ps = connection.prepareStatement(sql);
			ps.setBigDecimal(1, valor);
			ps.setInt(2, numero);
			ps.execute();

			ps.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void deletar(Connection connection, Integer numero) {

		PreparedStatement ps;
		String sql = "UPDATE conta SET esta_ativa = false where numero = ?";

		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, numero);
			ps.execute();

			ps.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void reativar(Connection connection , Integer  numero) {
		PreparedStatement ps;
		String sql = "UPDATE conta SET esta_ativa = true where numero = ?";

		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, numero);
			ps.execute();

			ps.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}