package warsztaty2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public class SolutsTab {

	public static void main(String[] args) {

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop?useSSL=false", "root",
				"coderslab")) {
//			Solution solut = new Solution("2017-12-22", "2017-12-24","tytul2", 1, 2);
//			solut.saveToDb(conn);
			
			Solution[] solutsArr = Solution.loadAllByUserId(conn, 2);
			System.out.println(Arrays.toString(solutsArr));
			
//			Solution[] allSoluts = Solution.loadAll(conn);
//			System.out.println();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

}
