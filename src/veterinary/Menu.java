package veterinary;

import java.util.Scanner;

public class Menu {

	
	public static void entry(Scanner scanner){
		
			
			while(scanner.hasNextLine()) {
			String choice = scanner.nextLine();
			if(choice.contains("authorise"))
			{
				if(User.auth(scanner)) {
					System.out.println("Вы успешно вошли");
					actions(scanner);
					return;
				}
				return;
			}
			else if(choice.contains("create user")){
				User.createUser(scanner);
				return;
			}
			else if(choice.contains("exit")){
				return;
			}
			else {
				System.out.println();
				entry(scanner);
			}
		}
	}
	
	
	public static void actions(Scanner scanner) {
			
			String choice =scanner.nextLine();
		
			if(choice.contains("create doctor")) {
				Doctor.createDoctor(scanner);
				actions(scanner);
			}
			else if(choice.contains("list doctor")){
				Doctor.listDoctor();
				actions(scanner);
			}
			else if(choice.contains("edit doctor")){
				Doctor.editDoctor(scanner);
				actions(scanner);
			}
			else if(choice.contains("delete doctor")){
				Doctor.deleteDoctor(scanner);
				actions(scanner);
			}
			else if(choice.contains("create patient")){
				Patient.createPatient(scanner);
				actions(scanner);
			}
			else if(choice.contains("list patient")){
				Patient.listPatient();
				actions(scanner);
			}
			else if(choice.contains("edit patient")){
				Patient.editPatient(scanner);
				actions(scanner);
			}
			else if(choice.contains("delete patient")){
				Patient.deletePatient(scanner);
				actions(scanner);
			}
			else if(choice.contains("create appointment")){
				Appointment.createAppointment(scanner);
				actions(scanner);
			}
			else if(choice.contains("list appointment")){
				Appointment.listFilterAppointment(scanner);
				actions(scanner);
			}
			else if(choice.contains("edit appointment")){
				Appointment.editAppointment(scanner);
				actions(scanner);
			}
			else if(choice.contains("back")){
				entry(scanner);
			}
			else if(choice.contains("exit")){
				return;
			}
			else {
				System.out.println("Такой комманды не существует");
				actions(scanner);
			}
		
	}
}
