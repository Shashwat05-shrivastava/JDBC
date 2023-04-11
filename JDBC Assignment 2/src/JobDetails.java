import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JobDetails {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/virtusadb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static Connection connection = null;
	private static Scanner sc = new Scanner(System.in);

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		JobDetails jd = new JobDetails();
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			System.out.println("Enter your Choice:");
			System.out.println("1)Insert the  record");
			System.out.println("2)Display the record");

			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				jd.insertRecord();
				break;
			case 2:
				jd.fetchRecord();
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void insertRecord() throws SQLException {
		// System.out.println("Inside insert Record");
		String sql = "insert into jobdetails(eName,eJob,pId,pName)values(?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		System.out.print("Enter the employee name:");
		preparedStatement.setString(1, sc.nextLine());

		System.out.print("Enter the employee job:");
		preparedStatement.setString(2, sc.nextLine());

		System.out.print("Enter the  project id: ");
		preparedStatement.setInt(3, sc.nextInt());

		System.out.print("Enter the project name: ");
		preparedStatement.setString(4, sc.next());

		int rows = preparedStatement.executeUpdate();
		if (rows > 0)
			System.out.println("Record inserted successfully");
	}

	public void fetchRecord() throws SQLException {
		System.out.println("Enter the name of employee whose details you want to fetch: ");
		int tempPid = sc.nextInt();
		String sql = "select * from  jobdetails where pID=" + tempPid;

		Statement statement = connection.createStatement();
		ResultSet res = statement.executeQuery(sql);
		if (res.next()) {
			String name = res.getString("eName");
			String job = res.getString("eJob");
			int pId = res.getInt("pId");
			String pName = res.getString("pName");
			System.out.println(name + "\t" + job + "\t" + pId + "\t" + pName);
		} else {
			System.out.println("Record not found");
		}
	}
}
