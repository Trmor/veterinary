package veterinary;

import java.util.Scanner;

public class Menu {

	
	public static void entry(Scanner scanner){
		
			System.out.println("Пожалуйста выберите действие:"
					+ "\n1. Войти"
					+ "\n2. Зарегистрировать пользователя"
					+ "\n0. Выйти");
			while(scanner.hasNextLine()) {
			int choice = Integer.parseInt(scanner.nextLine());
			switch(choice) {
			case (1):{
				if(User.auth(scanner)) {
					System.out.println("Вы успешно вошли");
					actions(scanner);
					return;
				}
				return;
			}
			case (2):{
				User.createUser(scanner);
				return;
			}
			default:
				return;
			}
		}
	}
	
	
	public static void actions(Scanner scanner) {
			System.out.println("Пожалуйста выберите действие:"
					+ "\n1. Добавить доктора"
					+ "\n2. Посмотреть список докторов"
					+ "\n3. Редактировать список докторов"
					+ "\n4. Удалить докторов"
					+ "\n5. Добавить пациента"
					+ "\n6. Посмотреть список пациентов"
					+ "\n7. Редактировать список пациентов"
					+ "\n8. Удалить пациента"
					+ "\n9. Создать заявку"
					+ "\n10. Посмотреть заявки по ФИО"
					+ "\n11. Редактировать заявки"
					+ "\n0. Вернуться");
			int choice = Integer.parseInt(scanner.nextLine());
		
			switch(choice) {
			case (1):{
				Doctor.createDoctor(scanner);
				actions(scanner);
			}
			case (2):{
				Doctor.listDoctor();
				actions(scanner);
			}
			case (3):{
				Doctor.editDoctor(scanner);
				actions(scanner);
			}
			case (4):{
				Doctor.deleteDoctor(scanner);
				actions(scanner);
			}
			case (5):{
				Patient.createPatient(scanner);
				actions(scanner);
			}
			case (6):{
				Patient.listPatient();
				actions(scanner);
			}
			case (7):{
				Patient.editPatient(scanner);
				actions(scanner);
			}
			case (8):{
				Patient.deletePatient(scanner);
				actions(scanner);
			}
			case (9):{
				Appointment.createAppointment(scanner);
				actions(scanner);
			}
			case (10):{
				Appointment.listFilterAppointment(scanner);
				actions(scanner);
			}
			case (11):{
				Appointment.editAppointment(scanner);
				actions(scanner);
			}
			default:
				entry(scanner);
			}
		
	}
}
