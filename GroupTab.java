package warsztaty2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public class GroupTab {

	public static void main(String[] args) {

		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop?useSSL=false", "root",
				"coderslab")) {
			
//			Group first = new Group("second");
//			first.saveToDb(conn);
//			
//			System.out.println(first.toString());
			
//			System.out.println(Arrays.toString(Group.loadAll(conn)));
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
