package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {

	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties(); // pega as propriedades
				String url = props.getProperty("dburl");// pega a url
				conn = DriverManager.getConnection(url, props); // conecta
				// passa a url e as propriedades de conexão.
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	private static Properties loadProperties() {// Carrega as propriedades
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			// Faz a leitura d guarda os dados dentro do obj props.
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

	// Métodos auxiliares para fechar os objetos.
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());

			}
		}
	}
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());

			}
		}
	}
}
