package question3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/virtusadb2";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static Connection connection = null;
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
	    try{
			Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
		return connection;	
	}
}
