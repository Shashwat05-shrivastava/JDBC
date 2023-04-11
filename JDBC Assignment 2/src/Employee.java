import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employee {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/virtusadb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int eId;
		String eName;
		String ePhoto;

		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement st = con.createStatement();
				PreparedStatement p = con.prepareStatement("insert into employee values(?,?,?)")) {
			System.out.println("Enter the employee details: ");
			eId = Integer.parseInt(br.readLine());
			eName = br.readLine();
			ePhoto = br.readLine();

			p.setInt(1, eId);
			p.setString(2, eName);
			p.setString(3, ePhoto);

			int i = p.executeUpdate();
			if (i > 0)
				System.out.println("Inserted Successfully");

			System.out.println("Dislaying the table: ");
			ResultSet result = st.executeQuery("select * from employee");
			while (result.next())
				System.out.println(result.getInt(1) + "\t" + result.getString(2) + "\t" + result.getString(3));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
