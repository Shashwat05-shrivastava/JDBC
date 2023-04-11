package practice;
import java.sql.*;
import java.util.Scanner;
public class Shashwat {

	public static final String Driver="com.mysql.cj.jdbc.Driver";
	public static final String URL="jdbc:mysql://localhost:3306/shashwat";
	public static final String USERNAME="root";
	public static final String PASSWORD="root";
	public static Connection con = null;
	public static Scanner sc = new Scanner(System.in);
	
	//Loading the Driver
	static {
		try {
			Class.forName(Driver);
			con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//Method for creating  tables
	public static void createTable() throws SQLException{
		String query="create table table2(Id int(20) primary Key auto_increment,FirstName varchar(20),LastName varchar(20))";
		Statement st=con.createStatement();
		st.executeUpdate(query);
		System.out.println("table created");
		
	}
	//Method for inserting values....
	public static void insertValue() throws SQLException {
		//String query="insert into table2 values(?,?)";
		PreparedStatement ps = con.prepareStatement("insert into table2 values(?,?,?)");
		//PreparedStatement ps=con.prepareStatement(query);
		int Id=sc.nextInt();
		String FirstName=sc.next();
		String LastName=sc.next();
		ps.setInt(1, Id);
		ps.setString(2,FirstName);
		ps.setString(3, LastName);
		int row=ps.executeUpdate();
		if(row>0)
			System.out.println("Inserted Successfully....");
	}
	
	//Method for Displaying Value....
	public static void displayValue() throws SQLException {
		
		String query="select * from table2";
		Statement st=con.createStatement();
		ResultSet result=st.executeQuery(query);
		while(result.next()) {
			System.out.println(result.getInt(1)+"\t"+result.getString(2)+"\t"+result.getString(3));
		}
		
	}
	
	//Method for updating values......
	public static void updateDetails() throws SQLException{
		String query="update table2 set FirstName=?,LastName=? where Id=?";
		PreparedStatement ps=con.prepareStatement(query);
		System.out.println("Enter the Id whose details you want to update...");
		int nId=sc.nextInt();
		System.out.println("Enter the new first Name...");
		String sFname=sc.next();
		System.out.println("Enter the new Last Name");
		String sLname=sc.next();
		
		ps.setString(1, sFname);
		ps.setString(2, sLname);
		ps.setInt(3, nId);
		
		int row=ps.executeUpdate();
		if(row>0)
			System.out.println("Recod successfully upddated");
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println("1} Insert in the database...");
		System.out.println("2} Display from the database...");
		System.out.println("3} Create table in the database...");
		System.out.println("4} Update value in the database...");
		System.out.println("Enter your Choice: ");
		int choice=sc.nextInt();
	
		switch(choice){
			case 1:insertValue();
			break;
			case 2:displayValue();
			break;
			case 3:createTable();
			break;
			case 4:updateDetails();
			break;
			default:
				System.out.println("Invalid choice...");
		}
		
	}

}
