package myResources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnection {
	private Connection con;
	
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public void connectToDb() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("JDBC Driver Loaded successfully!!!");
			this.con = DriverManager.getConnection("jdbc:mysql://localhost:3307/bank", "root", "bayero");
			
			System.out.println("Connected to Db Successfully!!!");
			
		} catch (ClassNotFoundException e) {
			System.err.println("Error in Loading JDBC Driver: "+e);
		} catch (SQLException e) {
			System.err.println("Error while trying to connect to the DB: "+e);
		}
		
	}//end of connectToDb

}
