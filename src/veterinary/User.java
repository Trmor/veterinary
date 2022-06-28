package veterinary;

import java.sql.*;
import java.util.Scanner;


public class User {

	private static String sqlUrl = "jdbc:postgresql://localhost:5432/veterinary";
	private static String sqlUser = "postgres";
	private static String sqlPass = "1488";
	
	public static boolean auth(Scanner scanner) {
		
		final int attempts = 3;
		
		try(Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass)){
			try(PreparedStatement ps = conn.prepareStatement("select * from users where username = ?")){
				System.out.println("Введите имя пользователя :");
				String login = scanner.nextLine();
				ps.setString(1, login);
				ResultSet rs = ps.executeQuery(); 
				if(rs.next()) {
					for(int i = 0; i < attempts; i++) {
					System.out.println("Введите пароль :");
					String password = scanner.nextLine();	
						if(password.equals(rs.getString("password"))){
							return true;
						}
					}
				}
			}
		}
		catch(Exception ex){
            System.out.println(ex);
        }
		return false;
	}
	
	public static void createUser(Scanner scanner) {
			System.out.println("Введите логин :");
			String login = scanner.nextLine();
			System.out.println("Введите пароль :");
			String password = scanner.nextLine();
			try(Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass))
			{
				 PreparedStatement ps = conn.prepareStatement("Insert into users (username, password) values (?,?)");
				 ps.setString(1, login);
				 ps.setString(2, password);
				 ps.executeQuery();
				 System.out.println("Вы удачно зарегистрировались");
			}
			catch(Exception ex){
	            System.out.println(ex);
	        }
		}	
	}
