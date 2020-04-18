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
	// m�todos est�ticos para conectar e desconectar com o BD
//m�todo auxiliar para carregar as propriedades que est�o salvas no arquivo db.proprerties

	private static Connection conn = null;

	public static Connection getConnection() { // esse m�todo vai retornar o objeto con que ta a� em cima, se tiver
												// nulo, tenho que escrever um c�digo para conectar com o banco
		if (conn == null) {
			try {
				Properties props = loadProperties(); // propriedade de conex�o, do banco
				String url = props.getProperty("dburl"); // ser� a url do banco de dado db url � o nome que demos no
															// db.properties. O valor da url do meu banco vai para a
															// vari�vel url tipo string
				conn = DriverManager.getConnection(url, props); // para que eu possa obter uma conx�o com o banco, eu
																// posso chamar
			} // salvamos o objeto na vari�vel chamada conn, ent�o minha conex�o est� salva
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

	// Fechando a conex�o:

	public static void closeConnection() {

		if (conn != null) { // ou seja, est� testando se a conex�o est� instanciada
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
