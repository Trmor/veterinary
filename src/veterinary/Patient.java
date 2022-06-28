package veterinary;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;

public class Patient {
	
	private static String sqlUrl = "jdbc:postgresql://localhost:5432/veterinary";
	private static String sqlUser = "postgres";
	private static String sqlPass = "1488";
	private static String format =  "%-5s %-30s %-12s %n";

	public static void createPatient(Scanner scanner) {
			System.out.println("Введите имя пациента :");
			String name = scanner.nextLine();
			try(Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass))
			{
				 PreparedStatement ps = conn.prepareStatement("Insert into patient (name, registration_date) values (?,?)");
				 ps.setString(1, name);
				 ps.setDate(2, Date.valueOf(LocalDate.now()));
				 ps.execute();
				 System.out.println("Пациент удачно добавлен");
				 return;
			}
			catch(Exception ex){
	            System.out.println(ex);
	        }
		}
	
	public static void listPatient() {
		try (Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass)){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM patient");
            ResultSet rs = ps.executeQuery();
            System.out.format("%-5s %-30s %-12s %n","id", "name", "registrated");
	        while(rs.next()){
                System.out.format(format, rs.getInt(1), rs.getString(2), rs.getDate(3));
            }
        }
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	public static void editPatient(Scanner scanner) {
		listPatient();
		
		try (Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass)){
            PreparedStatement ps = conn.prepareStatement("UPDATE Patient Set Name = ? Where id_patient = ?");
            	System.out.println("Введите Id пациента что бы изменить его ФИО: ");
				ps.setInt(2, Integer.parseInt(scanner.nextLine()));
				System.out.println("Введите ФИО: ");
				ps.setString(1, scanner.nextLine());
				ps.execute();
        }
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	public static void deletePatient(Scanner scanner) {
		listPatient();
		
		try (Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass)){
            PreparedStatement ps = conn.prepareStatement("delete from Patient Where id_patient = ?");
            	System.out.println("Введите Id пациента чтобы удалить его: ");
				ps.setInt(1, Integer.parseInt(scanner.nextLine()));
				ps.execute();
        }
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
}
