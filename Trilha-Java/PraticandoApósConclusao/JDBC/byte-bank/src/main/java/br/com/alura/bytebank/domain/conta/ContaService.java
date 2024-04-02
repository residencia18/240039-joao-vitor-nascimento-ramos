package br.com.alura.bytebank.domain.conta;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import br.com.alura.bytebank.ConnectionFactory;
import br.com.alura.bytebank.domain.RegraDeNegocioException;

public class ContaService {

	private ConnectionFactory connection;

	public ContaService() {
		this.connection = new ConnectionFactory();
	}

	private Set<Conta> contas = new HashSet<>();

	public Set<Conta> listarContasAbertas() {
		Connection connection = this.connection.recuperarConexao();
		return ContaDAO.listar(connection);
	}

	public Conta listarContaPorNumero(Integer numero) {
		Connection connection = this.connection.recuperarConexao();
		return ContaDAO.contaPorNumero(connection, numero);
	}

	public BigDecimal consultarSaldo(Integer numeroDaConta) {
		Connection connection = this.connection.recuperarConexao();
		var conta = ContaDAO.contaPorNumero(connection, numeroDaConta);
		return conta.getSaldo();
	}

	public void abrir(DadosAberturaConta dadosDaConta) {
		Connection connection = this.connection.recuperarConexao();
		ContaDAO.salvar(connection, dadosDaConta);
	}

	public void realizarSaque(Integer numeroDaConta, BigDecimal valor) {
		Connection connection = this.connection.recuperarConexao();

		try {
			Conta conta = ContaDAO.contaPorNumero(connection, numeroDaConta);
			if (valor.compareTo(BigDecimal.ZERO) <= 0)
				throw new RegraDeNegocioException("Valor do saque deve ser superior a zero!");

			if (valor.compareTo(conta.getSaldo()) > 0)
				throw new RegraDeNegocioException("Saldo insuficiente!");

			if(conta == null) {
				throw new IllegalArgumentException("Conta ativa não encontrada!");
			}
			
			BigDecimal novoValor = conta.getSaldo().subtract(valor);
			Connection connection2 = this.connection.recuperarConexao();
			ContaDAO.alterar(connection2, numeroDaConta, novoValor);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void realizarDeposito(Integer numeroDaConta, BigDecimal valor) {
		Connection connection = this.connection.recuperarConexao();
		Conta conta = ContaDAO.contaPorNumero(connection, numeroDaConta);

		if (conta == null) {
			throw new IllegalArgumentException("Conta não encontrada");
		}

		if (valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new RegraDeNegocioException("Valor do deposito deve ser superior a zero!");
		}
		
		if(conta == null) {
			throw new IllegalArgumentException("Conta ativa não encontrada!");
		}
		
		BigDecimal novoValor = conta.getSaldo().add(valor);
		Connection connection2 = this.connection.recuperarConexao();
		ContaDAO.alterar(connection2, numeroDaConta, novoValor);
	}

	public void encerrar(Integer numeroDaConta) {
		Connection connection = this.connection.recuperarConexao();
		Conta conta = ContaDAO.contaPorNumero(connection, numeroDaConta);
		if (conta.possuiSaldo()) {
			throw new RegraDeNegocioException("Conta não pode ser encerrada pois ainda possui saldo!");
		}
		Connection connection2 = this.connection.recuperarConexao();

		ContaDAO.deletar(connection2,numeroDaConta);
	}
	
	public void reabrir(Integer numeroDaConta) {
		Connection connection = this.connection.recuperarConexao();
		Conta conta = ContaDAO.contaPorNumeroInativa(connection, numeroDaConta);
		if (conta.isEstaAtiva()) {
			throw new RegraDeNegocioException("Conta não pode ser reaberta pois não foi encerrada!");
		}
		Connection connection2 = this.connection.recuperarConexao();

		ContaDAO.reativar(connection2,numeroDaConta);
	}

	private Conta buscarContaPorNumero(Integer numero) {
		return contas.stream().filter(c -> c.getNumero() == numero).findFirst()
				.orElseThrow(() -> new RegraDeNegocioException("Não existe conta cadastrada com esse número!"));
	}

	public void realizarTransferencia(int numeroDaContaOrigem, int numeroDaContaDestino, BigDecimal valor) {
		Connection connection = this.connection.recuperarConexao();
		try {
			connection.setAutoCommit(false);

			realizarSaque(numeroDaContaOrigem, valor);
			realizarDeposito(numeroDaContaDestino, valor);

			connection.commit();
		} catch (SQLException | RegraDeNegocioException e) {
			try {
				connection.rollback();
			} catch (SQLException rollbackException) {
				rollbackException.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException closeException) {
				closeException.printStackTrace();
			}
		}
	}

}
