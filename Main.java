package warsztaty2;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop?useSSL=false", "root",
				"coderslab")) {

//			Users[] users = Users.loadAll(conn);
//			for (Users u : users) {
//				System.out.println(u.toString());
//			}
//
//			Users loaded = Users.loadById(conn, 4);
//			if (loaded.getId() != 0) {
//				System.out.println(loaded.toString());
//			} else {
//
//				System.out.println("Brak użytkownika o takim ID");
//			}
			
			
//			loaded.deleteFromDb(conn, 6); 
			
//			Users[] users1 = Users.loadAll(conn);
//			for (Users u : users1) {
//				System.out.println(u.toString());
//			}
			
			Users user = new Users ("User", "hasło", "usr@mail.ru", 1);
			user.saveToDb(conn);

			System.out.println(user.toString());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
