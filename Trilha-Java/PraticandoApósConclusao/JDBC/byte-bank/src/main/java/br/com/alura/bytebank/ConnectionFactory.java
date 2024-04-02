package br.com.alura.bytebank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionFactory {
	
	
	
	
	public Connection recuperarConexao() {
		try {
			return  createDataSource().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	   private HikariDataSource createDataSource() {
	        HikariConfig config = new HikariConfig();
	        config.setJdbcUrl("jdbc:mysql://localhost:3306/byte_bank");
	        config.setUsername("root");
	        config.setPassword("12345");
	        config.setMaximumPoolSize(10);

	        return new HikariDataSource(config);
	    }
	


}
