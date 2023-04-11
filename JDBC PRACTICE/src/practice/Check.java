package practice;
import java.sql.*;
public class Check {

	public static void main(String[] args) {
		String Driver="com.mysql.cj.jdbc.Driver";
		String URL="jdbc:mysql://localhost:3306/shashwat";
	    final String USERNAME="root";
	    final String PASSWORD="root";
		try {
			Class.forName(Driver);
			Connection con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
			if(con.isClosed())
				System.out.println("Connection closed");
			else
				System.out.println("Connected.....");
		}catch(Exception e) {
			e.printStackTrace();;
		}

	}

}
