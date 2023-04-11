package question3;

import java.util.Scanner;

public class ParticipantMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice");
		System.out.println("1)ADD PARTICIPANT");
		System.out.println("2)LIST PARTICIPANT");
		System.out.println("3)FIND PARTICIPANT");
		
		
		ParticipantImplementation pi=new ParticipantImplementation();
		Participant p= new Participant();
		int choice=sc.nextInt();
		
		switch(choice) {
		case 1:
			pi.addParticipant(p);
			break;
		case 2:
			pi.listParticipant();
			break;
		case 3:
			pi.findParticipant();
			break;
		}

	}

}
