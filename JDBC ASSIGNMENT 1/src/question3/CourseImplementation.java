package question3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CourseImplementation implements CourseInterface{
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/virtusadb2";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static Scanner sc= new Scanner(System.in);
	Course c= new Course();
	@Override
	public void addCourse(Course c) {
		String sql="insert into course values(?,?,?,?)";
		
		System.out.print("Enter the CourseID: ");
		c.setCourseId(sc.nextInt());
		System.out.print("Enter the Course Name:");
		c.setName(sc.next());
		System.out.print("Enter the Start Date: ");
		c.setStartDate(sc.next());
		System.out.print("Enter the End Date: ");
		c.setEndDate(sc.next());
		
		try(Connection connection=DbUtil.getConnection();
			PreparedStatement p1=connection.prepareStatement(sql)){
			p1.setInt(1, c.getCourseId());
			p1.setString(2,c.getName());
			p1.setString(3, c.getStartDate());
			p1.setString(4, c.getEndDate());
			
			int rows=p1.executeUpdate();
			if(rows>0)
				System.out.println("Course added successfully");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void listCourses() {
		String sql ="select * from course";
		try(Connection connection=DbUtil.getConnection();
				PreparedStatement p1=connection.prepareStatement(sql)){
			ResultSet rs=p1.executeQuery();
			while(rs.next())
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void findCourses() {
		String sql ="select * from course where courseId=?";
		System.out.print("Enter the required Idno:");
		int searchCourse=sc.nextInt();
		try(Connection connection=DbUtil.getConnection();
				PreparedStatement p1=connection.prepareStatement(sql)){
			p1.setInt(1, searchCourse);
			ResultSet rs=p1.executeQuery();
			if(rs.next()) {
				p1.setInt(1, searchCourse);
				ResultSet rs1=p1.executeQuery();
				while(rs1.next()) {
					System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3)+"\t"+rs1.getString(4));
				}
			}else {
				System.out.println("Invalid Course Id");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCourse() {
		String sql="update course set startDate=? where courseId=?";
		System.out.println("Enter the Start Date:");
		String sDate=sc.next();
		System.out.println("Enter the Course Id: ");
		int cId=sc.nextInt();
		
		try(Connection connection=DbUtil.getConnection();
				PreparedStatement p1=connection.prepareStatement(sql)){
			p1.setString(1, sDate);
			p1.setInt(2, cId);
			p1.executeUpdate();
			System.out.println("Record Updated Successfully");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteCourse() {
		String sql ="delete from course where startDate=?";
		System.out.println("Enter the Date: ");
		String date=sc.next();
		
		try(Connection connection=DbUtil.getConnection();
				PreparedStatement p1=connection.prepareStatement(sql)){
			p1.setString(1, date);
			int rows=p1.executeUpdate();
			System.out.println("Course Deleted Succesfully");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
