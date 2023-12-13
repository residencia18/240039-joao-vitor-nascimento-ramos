
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {
	/** Modulo de conexão **/

	// Parâmetros de conexão
	private final String driver = "com.mysql.cj.jdbc.Driver";

	private static final String URL = "jdbc:mysql://ufwrytzg8yoorzom:n9znTckgfxEabE43zqML@bovinl7zdurb12szfwxy-mysql.services.clever-cloud.com:3306/bovinl7zdurb12szfwxy?autoReconnect=true&useSSL=false";
	private static final String USER = "ufwrytzg8yoorzom";
	private static final String SENHA = "n9znTckgfxEabE43zqML";

	// Método de conexão
	public Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(URL, USER, SENHA);
			//System.out.println("Conexão Feita com Sucesso !!!! ");
		} catch (ClassNotFoundException | SQLException ex) {
			throw new RuntimeException("Erro na conexão com o Banco de dados: ", ex);
		}
		return con;
	}

	public void testeConnexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			closeConnection(con);
		} catch (Exception e) {
			System.out.println("Jubileu: " + e);
		}

	}

	public void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
				//System.out.println("Conexão fechada !!!");
			}
		} catch (SQLException ex) {
			Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/*public static void main(String[] args) {
		DAO d = new DAO();

		d.testeConnexao();
	}*/

}
