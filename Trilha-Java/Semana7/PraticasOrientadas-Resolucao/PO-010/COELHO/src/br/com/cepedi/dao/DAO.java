package br.com.cepedi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO {
	
	/** Modulo de conexão **/

	// Parâmetros de conexão

	private static final String URL = "jdbc:mysql://u4xjbh7qtsi2bsbe:3i69Q2umLv7htadjR68t@bwdzf9cxv3wvwsvxgvbd-mysql.services.clever-cloud.com:3306/bwdzf9cxv3wvwsvxgvbd";
	private static final String USER = "u4xjbh7qtsi2bsbe";
	private static final String SENHA = "3i69Q2umLv7htadjR68t";

	// Método de conexão
	public static Connection conectar() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, SENHA);
			//System.out.println("Conexão Feita com Sucesso !!!! ");
		} catch (SQLException ex) {
			ex.getStackTrace();
			throw new RuntimeException("Erro na conexão com o Banco de dados: ", ex);
		}
		return con;
	}
	
	public static void testeConnexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			closeConnection(con);
		} catch (Exception e) {
			System.out.println("Jubileu: " + e);
		}

	}
	
	public static void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
				//System.out.println("Conexão fechada !!!");
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
