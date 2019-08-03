import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TestJDBCConnection {

	static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	static final String dbUrl = "jdbc:mysql://localhost/mydb";
	
	// DB Credentials
	static final String dbUsername = "root";
	static final String dbPassword = "root";
	
	
	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		String sql = null;
		
		try {
			// Register JDBC Driver
			Class.forName(jdbcDriver);
			
			// Open a connection
			System.out.println("Connecting to database..");
			conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			
			// Execute a query
			System.out.println("Creating a statement...");
			stmt = conn.createStatement();
			
			sql = "Select * from employees";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");
				
				// Display values
				System.out.println("ID: " + id);
				System.out.println("Age: " + age);
				System.out.println("First: " + first);
				System.out.println("Last: " + last);
			}
			
			
		} catch (SQLException se) {
			// Handles errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null ) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
