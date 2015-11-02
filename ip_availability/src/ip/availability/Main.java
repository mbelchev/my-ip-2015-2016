package ip.availability;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		final Map<String, User> students = new HashMap<String, User>();
		final Map<String, Integer> loginCount = new HashMap<String, Integer>();		
		
		final Scanner input = new Scanner(System.in);
		
		while (true) {
			System.out.print("Enter command: ");
			
			final Stdin cmd = new Stdin(input.next());
			
			switch (cmd.get_command()) {
				case "login":
					if (!checkLogin(cmd.get_name(), students)) {
						students.put(cmd.get_name(), new User(cmd.get_name()));
						
						if (loginCount.containsKey(cmd.get_name()))
							loginCount.put(cmd.get_name(), loginCount.get(cmd.get_name())+1);
						else 
							loginCount.put(cmd.get_name(), 1);
						
						System.out.println("ok");
					} else System.out.println("error: user already logged in");
					break;
					
				case "logout":
					if (checkLogin(cmd.get_name(), students))
						students.get(cmd.get_name()).setLogged(false);
					else 
						System.out.println("error: not logged in");
					break;
				
				case "listavaible":
					if (checkLogin(cmd.get_name(), students)) {
						System.out.print("ok");
						for (User student: students.values())
							if (student.getLogged())  System.out.print(":" + student.getName());
						System.out.println();
					} else System.out.println("error: not logged in");
					break;
				
				case "info":
					if (checkLogin(cmd.get_name(), students)) {
						System.out.println(
							"ok:" +
							cmd.get_param() +
							":" +
							students.get(cmd.get_param()).getLogged() +
							":" +
							loginCount.get(cmd.get_param())
						);
					} else System.out.println("error: not logged in");
					break;
					
				case "shutdown":
					return;
				
				default:
					System.out.println("error: unknown command");
					break;
			}			
		}
		
	}
	
	private static Boolean checkLogin(String name, Map<String, User> students) {
		return (students.get(name) == null || !students.containsKey(name) || !students.get(name).getLogged()) ? false : true;
	}

}
