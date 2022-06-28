package veterinary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Doctor {
	
	private static String sqlUrl = "jdbc:postgresql://localhost:5432/veterinary";
	private static String sqlUser = "postgres";
	private static String sqlPass = "1488";
	private static String format =  "%-5s %-30s %n";

	public static void createDoctor(Scanner scanner) {
		System.out.print("\nВведите имя доктора :");
		String name = scanner.nextLine();
		try(Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass)){
			 PreparedStatement ps = conn.prepareStatement("Insert into doctor (name) values (?)");
			 ps.setString(1, name);
			 ps.execute();
			 System.out.println("\nДоктор удачно добавлен");
			 return;
		}
		catch(Exception ex){
            System.out.println(ex);
        }
	}
	
	public static void listDoctor() {
		try (Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass)){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM doctor");
            ResultSet rs = ps.executeQuery();
            System.out.format("%-5s %-30s %n","id", "name");
	        while(rs.next()){
                System.out.format(format, rs.getInt(1), rs.getString(2));
            }
        }
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	public static void editDoctor(Scanner scanner) {
		listDoctor();
		
		try (Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass)){
            PreparedStatement ps = conn.prepareStatement("UPDATE Doctor Set Name = ? Where id_doctor = ?");
            	System.out.println("Введите Id доктора что бы изменить его ФИО: ");
				ps.setInt(2, Integer.parseInt(scanner.nextLine()));
				System.out.println("Введите ФИО: ");
				ps.setString(1, scanner.nextLine());
				ps.execute();
        }
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	public static void deleteDoctor(Scanner scanner) {
		listDoctor();
		
		try (Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass)){
            PreparedStatement ps = conn.prepareStatement("delete from Doctor Where id_Doctor = ?");
            	System.out.println("Введите Id доктора чтобы удалить его: ");
				ps.setInt(1, Integer.parseInt(scanner.nextLine()));
				ps.execute();
        }
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
}
