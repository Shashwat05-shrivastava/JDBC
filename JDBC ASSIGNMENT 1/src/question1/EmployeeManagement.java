package question1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeManagement {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/virtusadb2";
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
		EmployeeManagement em = new EmployeeManagement();
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			System.out.println("Enter your Choice:");
			System.out.println("1)Insert the  record");
			System.out.println("2)Display the record");

			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				em.insertRecord();
				break;
			case 2:
				em.fetchRecord();
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void insertRecord() throws SQLException {
		System.out.println("Inside insert Record");
		System.out.print("Enter the employee code:");
		int eCode=sc.nextInt();
		System.out.print("Enter the employee Name:");
		String eName=sc.next();
		System.out.print("Enter the  employee Salary: ");
		double eSalary=sc.nextDouble();
		
	try {
		   PreparedStatement p1=connection.prepareStatement("select * from employees where eCode=?");
		   p1.setInt(1,eCode);
		   ResultSet rs=p1.executeQuery();
		   if(rs.next()) {
			   throw new AlreadyExistingEmployeeNumberException("Employee ID already exists");
		   }else{
		       String sql = "insert into employees(eCode,eName,eSalary)values(?,?,?)";
		       PreparedStatement preparedStatement = connection.prepareStatement(sql);

		       preparedStatement.setInt(1,eCode);
		       preparedStatement.setString(2,eName);
		       preparedStatement.setDouble(3,eSalary);

		       int rows = preparedStatement.executeUpdate();
		       if (rows > 0)
			           System.out.println("Record inserted successfully");
		}
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	}
	
	public void fetchRecord() throws SQLException {
		System.out.print("Enter the employee code:");
		int eCode=sc.nextInt();
		
		try {
			   PreparedStatement p1=connection.prepareStatement("select * from employees where eCode=?");
			   p1.setInt(1,eCode);
			   ResultSet rs=p1.executeQuery();
			   if(rs.next()) {
				        p1.setInt(1, eCode);
					    ResultSet r=p1.executeQuery();
				   		while(rs.next()) {
				   			System.out.println(r.getInt(1)+"\t"+r.getString(2)+"\t"+r.getDouble(3));
				   		}
				   }else {
					   throw new InvalidEmployeeIDException("Invalid Employee ID");
				   }
			   }catch(Exception e) {
				   e.printStackTrace();
			}
	}
}
