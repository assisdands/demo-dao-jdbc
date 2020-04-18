package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	// métodos estáticos para conectar e desconectar com o BD
//método auxiliar para carregar as propriedades que estão salvas no arquivo db.proprerties

	private static Connection conn = null;

	public static Connection getConnection() { // esse método vai retornar o objeto con que ta aí em cima, se tiver
												// nulo, tenho que escrever um código para conectar com o banco
		if (conn == null) {
			try {
				Properties props = loadProperties(); // propriedade de conexão, do banco
				String url = props.getProperty("dburl"); // será a url do banco de dado db url é o nome que demos no
															// db.properties. O valor da url do meu banco vai para a
															// variável url tipo string
				conn = DriverManager.getConnection(url, props); // para que eu possa obter uma conxão com o banco, eu
																// posso chamar
			} // salvamos o objeto na variável chamada conn, então minha conexão está salva
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}

	}

	// Fechando a conexão:

	public static void closeConnection() {

		if (conn != null) { // ou seja, está testando se a conexão está instanciada
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}

	}

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
