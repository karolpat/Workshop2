package warsztaty2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ExTab {

	public static void main(String[] args) {

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop?useSSL=false", "root",
				"coderslab")) {
			
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	

}
