package question2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Statement;

import question1.AlreadyExistingEmployeeNumberException;

public class BankService {
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
		
		BankService bs= new BankService();
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			System.out.println("Enter your Choice:");
			System.out.println("1)Create Account");
			System.out.println("2)Transfer Amount");
			System.out.println("3)Show Details");

			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				bs.createAccount();
				break;
			case 2:
				bs.TransferAmount();
				break;
			case 3:
				bs.showDetails();
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Method to create account 
	
	private void createAccount() throws InvalidAccountNumberException, InsufficientAmountException {
		System.out.println("Insert the required details: ");
		System.out.print("Enter the Account number:");
		int acno=sc.nextInt();
		System.out.print("Enter the customer Name:");
		String cName=sc.next();
		System.out.print("Enter the Balance: ");
		double balance=sc.nextDouble();
		
		if(acno<0) {
			throw new InvalidAccountNumberException("Account number is invalid");
		}
		if(balance<0) {
			throw new InsufficientAmountException("Cannot open account with this balance");
		}
		try {
			   PreparedStatement p1=connection.prepareStatement("select * from bankService where acno=?");
			   p1.setInt(1,acno);
			   ResultSet rs=p1.executeQuery();
			   if(rs.next()) {
				   throw new AlreadyExistingAccountNumberException("Account number already exists");
			   }else{
			       String sql = "insert into bankService(acno,cName,balance)values(?,?,?)";
			       PreparedStatement preparedStatement = connection.prepareStatement(sql);

			       preparedStatement.setInt(1,acno);
			       preparedStatement.setString(2,cName);
			       preparedStatement.setDouble(3,balance);

			       int rows = preparedStatement.executeUpdate();
			       if (rows > 0)
				           System.out.println("Record inserted successfully");
			}
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		
	}
	
	//Method to transfer funds
	
	private void TransferAmount() throws InvalidAccountNumberException, InsufficientAmountException, SQLException {
		System.out.print("Enter the  Source Account number:");
		int acno1=sc.nextInt();
		System.out.print("Enter the Destination Account number:");
		int acno2=sc.nextInt();
		System.out.print("Enter the Balance: ");
		double amnt=sc.nextDouble();
		
		if(acno1<0 || acno2<0) {
			throw new InvalidAccountNumberException("Account number is invalid");
		}
		if(amnt<0) {
			throw new InsufficientAmountException("Cannot open account with this balance");
		}
		//fetching balance from source account
		PreparedStatement p1=connection.prepareStatement("select * from bankService where acno=?");
		p1.setInt(1,acno1);
		ResultSet rs1=p1.executeQuery();
		rs1.next();
		double sourcebalance=rs1.getDouble(3);
		
		//fetching balance from destination account
		PreparedStatement p2=connection.prepareStatement("select * from bankService where acno=?");
		p2.setInt(1,acno2);
		ResultSet rs2=p2.executeQuery();
		rs2.next();
		double destbalance=rs2.getDouble(3);
		
		//checking fund
		if(sourcebalance<amnt) {
			throw new InsufficientAmountException("Insufficient Balance");
		}
		
		//Reducing amount from source account balance
		PreparedStatement p3=connection.prepareStatement("update bankService set balance=? where acno=?");
		p3.setDouble(1, (sourcebalance-amnt));
		p3.setInt(2,acno1);
		p3.executeUpdate();
		
		//Adding amount to destination account balance
		PreparedStatement p4=connection.prepareStatement("update bankService set balance=? where acno=?");
		p4.setDouble(1, (destbalance+amnt));
		p4.setInt(2,acno2);
		p4.executeUpdate();
		System.out.println("Amount transfered successfully");
		
	}
	
	//Method to display details
	private void showDetails() throws InvalidAccountNumberException {
		System.out.println("Insert the required details: ");
		System.out.print("Enter the Account number:");
		int acno=sc.nextInt();
		if(acno<0) {
			throw new InvalidAccountNumberException("Account number is invalid");
		}
		try {
			   PreparedStatement p1=connection.prepareStatement("select * from bankService where acno=?");
			   p1.setInt(1,acno);
			   ResultSet rs=p1.executeQuery();
			   if(!rs.next()) {
				   throw new InvalidAccountNumberException("Account number doesn't exists");
			   }else{
			       String sql = "select * from bankService where acno=?";
			        p1.setInt(1, acno);
				    ResultSet r=p1.executeQuery();
			   		while(r.next()) {
			   			System.out.println(r.getInt(1)+"\t"+r.getString(2)+"\t"+r.getDouble(3));
			   		}
			}
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		
	}
}
