package warsztaty2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserAdmin {

	public static void main(String[] args) {

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop?useSSL=false", "root",
				"coderslab")) {
		
			showUsers(conn);
			
			System.out.println("============================");
			
			
			
			
			
			Scanner scan = new Scanner (System.in);
			
			String input=" ";
			
			while(!input.equals("quit")){
				
				showOptions();
				input = scan.nextLine();
				
				if(Pattern.matches("^(add){1}", input)){
					setAtt(newUser()).saveToDb(conn);
					System.out.println("User added successfully");
				}else if(Pattern.matches("^(edit){1}", input)){
					setAtt(editOption(conn)).saveToDb(conn);
					System.out.println("User editted successfully");
				}else if(Pattern.matches("^(delete){1}", input)){
					delete(conn);
					System.out.println("User deleted successfully");
				}else if(Pattern.matches("^(quit){1}", input)){
					System.out.println("Bye");
				}else {
					System.out.println("Wrong input. Try again");
				}
				
				
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		
	}
	
	/** Print all users from DB
	 * @param conn
	 * @throws SQLException
	 */
	public static void showUsers(Connection conn) throws SQLException{
		
		Users[] users = Users.loadAll(conn);
		System.out.println("Username | mail | id");
		System.out.println("--------------------------------------");
		for (Users u : users) {
			System.out.println(u.toString());
		}
		
	}
	
	/** Print option to choose for user from txt file
	 * 
	 */
	public static void showOptions (){
		
		try{
			BufferedReader br = new BufferedReader(new FileReader("Options.txt"));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			
			while(line!=null){
				
				sb.append(line);
				sb.append(System.lineSeparator());
		        line = br.readLine();
			}
			String output = sb.toString();
			System.out.println(output);
			
		}catch (IOException e ){
			e.printStackTrace();
			
		}
		
	}
	
	/** To generate new User to add attributes in setAtt method.
	 * @return New User without any attribute
	 */
	public static Users newUser() {
		
		Users user=new Users();
		
		return user;
	}
		
	/** Creates User by given ID to edit attrbutes in setAtt method. In case, ID does not exist, creates new User
	 * @param conn
	 * @return User by given ID to get atrributes set.
	 * @throws SQLException
	 */
	public static Users editOption(Connection conn) throws SQLException{
				
		Scanner edit = new Scanner (System.in);
		
		System.out.println("Enter ID of existing user to edit");
		
		while(!edit.hasNextInt()){
			
			System.out.println("Wrong input. Try again");
			edit.next();
		}
		
		int id=edit.nextInt();
		
		Users toEdit = Users.loadById(conn, id);
		System.out.println(toEdit.toString());
		
		return toEdit;
	}
	
	/** Set attributes by words written in console.
	 * @param user with set attributes
	 * @return User to be saved in DB
	 */
	public static Users setAtt(Users user){
		
		Scanner add = new Scanner(System.in);
		
		while(true){
			System.out.println("Enter an username");
			String attribute = add.nextLine();
			if(Pattern.matches("^[a-zA-Z]+", attribute)){
				user.setUsername(attribute);
				break;
			}else {
				System.out.println("Wrong username. Try again");
			}
		}
		
		while(true){
			System.out.println("Enter password");
			String attribute = add.nextLine();
			if(Pattern.matches("^[a-zA-Z]+", attribute)){
				user.setPassword(attribute);
				break;
			}else{
				System.out.println("Wrong password. Try again");
			}
		}
		
		while(true){
			System.out.println("Enter email");
			String attribute = add.nextLine();
			if(Pattern.matches("^[a-zA-Z0-9\\._]+@{1}[a-z]+\\.{1}[a-z]+", attribute)){
				user.setEmail(attribute);
				break;
			}else{
				System.out.println("Wrong email. Try again");
			}
		}
		
		while(true){
			System.out.println("Enter group ID");
			String attribute = add.nextLine();
			if(Pattern.matches("^[0-9]+", attribute)){
				user.setPerson_group(Integer.parseInt(attribute));
				break;
			}else{
				System.out.println("Wrong id. Try again");
			}
		}
		return user;
	}
	
	public static void delete(Connection conn) throws SQLException{
		
		Scanner delete = new Scanner (System.in);
		
		System.out.println("Enter ID of existing user to delete");
		
		while(!delete.hasNextInt()){
			
			System.out.println("Wrong input. Try again");
			delete.next();
		}
		
		int id=delete.nextInt();
				
		Users.loadById(conn, id).deleteFromDb(conn);
	}

}
