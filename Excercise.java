package warsztaty2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Excercise {
	
	private int id;
	private String title;
	private String description;

	
	public Excercise() {}
	
	


	public Excercise(int id, String title, String description) {
		
		setTitle(title);
		setDescription(description);
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getTitle()).append(this.getDescription());
		return sb.toString();
	}
	
	
	static public Excercise[] loadAll (Connection conn) throws SQLException{
		
		ArrayList<Excercise> excerciseList = new ArrayList<>();
		
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("select * from excercise");
		
		while(res.next()){
			
			Excercise tmpExcercise = new Excercise();
			tmpExcercise.setTitle(res.getString("title"));
			tmpExcercise.setDescription(res.getString("description"));
			tmpExcercise.setId(res.getInt("id"));
			
			excerciseList.add(tmpExcercise);
		}
		
		Excercise[] excerciseArr = new Excercise[excerciseList.size()];
		excerciseList.toArray(excerciseArr);
		return excerciseArr;
		
	}
	
	static public Excercise loadById(Connection conn, int id) throws SQLException{
		
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("select * from excercise where id="+id);
		
		Excercise loaded = new Excercise();
		
		while(res.next()){
			
			loaded.setId(res.getInt("id"));
			loaded.setTitle(res.getString("title"));
			
		}
		return loaded;
		
	}
	
	
	public void deleteFromDb(Connection conn, int id) throws SQLException{
		
		Excercise loaded = loadById(conn, id);
		
		if(loaded.id!=0){
			PreparedStatement prep = conn.prepareStatement("delete from excercise where id=?");
			prep.setInt(1, id);
			prep.executeUpdate();
			this.id=0;
		}
		
	}
	
	public Excercise saveToDb(Connection conn ) throws SQLException{
		
		
		if(this.getId()==0){
			
			String[] generatedCoulmns= {"id"};
			
			PreparedStatement prep = conn.prepareStatement("insert into excercise (title, description) values (?, ?)", generatedCoulmns);
			prep.setString(1, this.getTitle());
			prep.setString(2, this.getDescription());
			
			ResultSet rs = prep.getGeneratedKeys();
			
			if(rs.next()){
				this.setId(rs.getInt(1));
			}
			
		}else {
			PreparedStatement prep = conn.prepareStatement("update excercise set title=? description=? where id=?");
			prep.setString(1, this.getTitle());
			prep.setString(2, this.getDescription());
			prep.setInt(3, this.getId());
			
			prep.executeUpdate();
		}
		return this;
		
	}
	
	
}
