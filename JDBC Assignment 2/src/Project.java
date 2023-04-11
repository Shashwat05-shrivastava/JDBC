import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Project {
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
		int pId;
		String pStartDate;
		String pEndDate;
		String pLocation;

		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement st = con.createStatement();
				PreparedStatement p = con.prepareStatement("insert into project values(?,?,?,?)")) {
			System.out.println("Enter the project details: ");

			pId = Integer.parseInt(br.readLine());
			pStartDate = br.readLine();
			pEndDate = br.readLine();
			pLocation = br.readLine();

			p.setInt(1, pId);
			p.setString(2, pStartDate);
			p.setString(3, pEndDate);
			p.setString(4, pLocation);

			int i = p.executeUpdate();
			if (i > 0)
				System.out.println("Details Inserted Successfully");

			if (pId == 24550) {
				System.out.println("Displaying the project details: ");
				ResultSet result = st.executeQuery("select * from project");
				while (result.next())
					System.out.println(result.getInt(1) + "\t" + result.getString(2) + "\t" + result.getString(3) + "\t"
							+ result.getString(4));
			} else {
				System.out.println("Cannot Display the details");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
