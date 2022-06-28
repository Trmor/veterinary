package veterinary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Scanner;

public class Appointment {
	
	private static String sqlUrl = "jdbc:postgresql://localhost:5432/veterinary";
	private static String sqlUser = "postgres";
	private static String sqlPass = "1488";
	private static String format =  "%-5s %-30s %n";
	private static String formatList =  "%-5s %-30s %-30s %-15s %-15s %n";

	public static void createAppointment(Scanner scanner) {
		Doctor.listDoctor();
		System.out.println("Выберите Id доктора:");
		int doctor = Integer.parseInt(scanner.nextLine());
		Patient.listPatient();
		System.out.println("Выберите Id пациента:");
		int patient = Integer.parseInt(scanner.nextLine());
		System.out.println("Введите дату и время для записи yyyy-mm-dd hh:mm :");
		String time = scanner.nextLine();
		try(Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass))
		{
			PreparedStatement ps = conn.prepareStatement("Insert into appointment (Id_doctor, Id_patient, Id_state, appointment date) values (?,?,?,?)");
			ps.setInt(1, doctor);
			ps.setInt(2, patient);
			ps.setInt(3, 1);//новый
			ps.setTimestamp(4, Timestamp.valueOf(time));;
			ps.execute();
			System.out.println("Запись удачно добавлена");
			return;
		}
		catch(Exception ex){
            System.out.println(ex);
        }
	}
	
	public static void editAppointment(Scanner scanner) {
		listAppointment();
		System.out.println("Выберите Id записи для изменения: ");
		int appId = Integer.parseInt(scanner.nextLine());
		Patient.listPatient();
		System.out.println("Выберите Id пациента : ");
		int patientId = Integer.parseInt(scanner.nextLine());
		Doctor.listDoctor();
		System.out.println("Выберите Id доктора : ");
		int doctorId = Integer.parseInt(scanner.nextLine());
		listState();
		System.out.println("Выберите Id статуса записи : ");
		int stateId = Integer.parseInt(scanner.nextLine());
		System.out.println("Выберите дату записи : ");
		String time = scanner.nextLine();
		try (Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass)){
            PreparedStatement ps = conn.prepareStatement("UPDATE Appointment Set id_doctor = ?, id_patient = ?, id_state = ?, appointmentdate = ?  Where id_Appointment = ?");
            ps.setInt(1, appId);
            ps.setInt(2, doctorId);
            ps.setInt(3, patientId);
            ps.setInt(4,stateId);
            ps.setTime(5,Time.valueOf(time));
            ps.execute();
            System.out.println("Запись изменена");
        }
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	public static void listAppointment() {
		try (Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass)){
            PreparedStatement ps = conn.prepareStatement("Select appointment.id_appointment, patient.name, doctor.name, state.name, appointment.appointmentdate\r\n"
            		+ "from appointment \r\n"
            		+ "join patient on appointment.id_patient = patient.id_patient\r\n"
            		+ "join doctor on appointment.id_doctor = doctor.id_doctor\r\n"
            		+ "join state on appointment.id_state = state.id_state");
            ResultSet rs = ps.executeQuery();
            System.out.format(formatList, "Id","Patient", "Doctor", "State", "Date");
	        while(rs.next()){
                System.out.format(formatList, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
            }
        }
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
		
	public static void listFilterAppointment(Scanner scanner) {
		try (Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass)){
            PreparedStatement ps = conn.prepareStatement("Select appointment.id_appointment, patient.name, doctor.name, state.name, appointment.appointmentdate\r\n"
            		+ "from appointment \r\n"
            		+ "join patient on appointment.id_patient = patient.id_patient\r\n"
            		+ "join doctor on appointment.id_doctor = doctor.id_doctor\r\n"
            		+ "join state on appointment.id_state = state.id_state\r\n"
            		+ "where patient.name like ?");
            System.out.println("Введите фамилию пациента :");
            ps.setString(1, "%"+ scanner.nextLine()+"%");
            ResultSet rs = ps.executeQuery();
            System.out.format(formatList, "Id","Patient", "Doctor", "State", "Date");
	        while(rs.next()){
                System.out.format(formatList, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
            }
        }
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
	public static void listState() {
		try (Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass)){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM state");
            ResultSet rs = ps.executeQuery();
            System.out.format(format,"id", "name");
	        while(rs.next()){
                System.out.format(format, rs.getInt(1), rs.getString(2));
            }
        }
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
	
}

