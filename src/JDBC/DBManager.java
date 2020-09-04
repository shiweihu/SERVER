package JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
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
			String dbPass = "Server_Pass_123";
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
		Statement stmp = null;
		try {
			stmp = conn.createStatement();
			String sql = "create table userInfo("
					+ "gender varchar(1) not null,"
					+ "age decimal(3,0) not null,"
					+ "postcode varchar(20) not null"
					+ ")";
			stmp.execute(sql);
		} catch (SQLException e) {
            System.out.println("userInfo table already exsis");
			
		}finally
		{
			if(stmp!=null) 
			{
				try {
					stmp.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		try {
			stmp = conn.createStatement();
			String sql = "create table version_tab("
					+ "version decimal(10,0) not null"
					+ ")";
			stmp.execute(sql);
			sql = "insert into version_tab values(1)";
			stmp.execute(sql);
		} catch (SQLException e) {
			System.out.println("version_tab table already exsis");
		}finally
		{
			if(stmp!=null) 
			{
				try {
					stmp.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	
	public int getVersion() 
	{
		String sql = "select version from version_tab";
		Statement stmp = null;
		int version = 0;
		try {
			stmp = conn.createStatement();
			ResultSet resultSet = stmp.executeQuery(sql);
			while(resultSet.next()) 
			{
				version =  resultSet.getInt(1);
				return version;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		 {
			if(stmp!=null) 
			{
				try {
					stmp.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 }
		return version;
	}
	public boolean storeUserInfo(String gender,String age,String postcode) 
	{
		String sql = "insert into userInfo(gender,age,postcode) values("+gender+","+age+","+postcode+")";
		Statement stmp = null;
		 try {
			 stmp =  conn.createStatement();
			 int resultCount = stmp.executeUpdate(sql);
			  
			  if(resultCount ==1) 
			  {
				  return true;
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally 
		 {
			if(stmp!=null) 
			{
				try {
					stmp.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 }
		 return false;
	}
	
}
