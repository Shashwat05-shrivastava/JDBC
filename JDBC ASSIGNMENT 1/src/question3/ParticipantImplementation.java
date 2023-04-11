package question3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ParticipantImplementation implements ParticipantInterfcae{
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/virtusadb2";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static Scanner sc= new Scanner(System.in);
	Participant p= new Participant();
	@Override
	public void addParticipant(Participant p) {
        String sql="insert into participant values(?,?,?,?)";
		
		System.out.print("Enter the Enrollnment ID: ");
		p.setEid(sc.nextInt());
		System.out.print("Enter the Participant name:");
		p.setpName(sc.next());
		System.out.print("Enter the Enrollnment Date: ");
		p.setEdate(sc.next());
		System.out.print("Enter the Course Id: ");
		p.setcId(sc.nextInt());
		
		
		try(Connection connection=DbUtil.getConnection();
			PreparedStatement p1=connection.prepareStatement(sql)){
			p1.setInt(1, p.getEid());
			p1.setString(2,p.getpName());
			p1.setString(3, p.getEdate());
			p1.setInt(4, p.getcId());
				
			int rows=p1.executeUpdate();
			if(rows>0)
				System.out.println("Participant added successfully");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void listParticipant() {
		String sql ="select * from participant";
		try(Connection connection=DbUtil.getConnection();
				PreparedStatement p1=connection.prepareStatement(sql)){
			ResultSet rs=p1.executeQuery();
			while(rs.next())
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void findParticipant() {
		String sql ="select * from participant where eid=?";
		System.out.print("Enter the required Eno:");
		int searchParticipant=sc.nextInt();
		try(Connection connection=DbUtil.getConnection();
				PreparedStatement p1=connection.prepareStatement(sql)){
			p1.setInt(1, searchParticipant);
			ResultSet rs=p1.executeQuery();
			if(rs.next()) {
				p1.setInt(1, searchParticipant);
				ResultSet rs1=p1.executeQuery();
				while(rs1.next()) {
					System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3)+"\t"+rs.getInt(4));
				}
			}else {
				System.out.println("Invalid Enrollnment Id");
			}
						
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
