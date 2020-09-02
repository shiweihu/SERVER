package JDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;



public class DBManager {
    private  static DBManager instance = null;
    private Connection  conn  = null;
    public static DBManager getInstance() 
	{
		if(instance == null) 
		{
			instance = new DBManager();
		}
		return instance;
	}
	private DBManager() 
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.DriverManager.deregisterDriver(new com.mysql.jdbc.Driver());
			
			String dbUri = "jdbc:mysql://localhost:3306/healthcare?serverTimezone=UTC";
			String dbuser = "serveUser";
			String dbPass = "123456";
			conn = java.sql.DriverManager.getConnection(dbUri, dbuser, dbPass);
			createTables();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createTables() 
	{
		Statement stmp;
		try {
			stmp = conn.createStatement();
			String sql = "create table userInfo("
					+ "gender varchar(1) not null,"
					+ "age decimal(3,0) not null,"
					+ "postcode varchar(20) not null"
					+ ")";
			stmp.execute(sql);
			stmp.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
		
		
	}
	
	public boolean storeUserInfo(String gender,String age,String postcode) 
	{
		String sql = "insert into userInfo(gender,age,postcode) values("+gender+","+age+","+postcode+")";
		 try {
			 Statement stmp =  conn.createStatement();
			 int resultCount = stmp.executeUpdate(sql);
			  stmp.close();
			  if(resultCount ==1) 
			  {
				  return true;
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return false;
	}
	
}
