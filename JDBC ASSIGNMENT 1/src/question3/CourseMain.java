package question3;
import java.util.*;
public class CourseMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice");
		System.out.println("1)ADD COURSE");
		System.out.println("2)LIST COURSE");
		System.out.println("3)FIND COURSE");
		System.out.println("4)UPDATE COURSE");
		System.out.println("5)DELETE COURSE");
		
		CourseImplementation ci=new CourseImplementation();
		Course c= new Course();
		int choice=sc.nextInt();
		
		switch(choice) {
		case 1:
			ci.addCourse(c);
			break;
		case 2:
			ci.listCourses();
			break;
		case 3:
			ci.findCourses();
			break;
		case 4:
			ci.updateCourse();
			break;
		case 5:
			ci.deleteCourse();
			break;
		}
	}

}
