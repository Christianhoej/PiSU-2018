package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.CallableStatement;


public class Connect {
	private final String HOST     = "localhost";
	private final int    PORT     = 3306;
	private final String DATABASE = "mydb1";
	private final String USERNAME = "root"; 
	private final String PASSWORD = "";
	private Connection connection;

	public Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
			System.out.println("Connected");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public ResultSet doQuery(String query) throws SQLException{
		Statement stmt = connection.createStatement();
		ResultSet res = stmt.executeQuery(query);
		return res;
	}

	public void doUpdate(String query) throws SQLException{
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(query);
	}

	public void runUpdateProcedure(String functioname, Object... args) throws SQLException
	{
		CallableStatement stmt = null;
		String query = String.format("{call %s", functioname);
		query += "(";
		for(Object o : args)
		{        	
			query += "?";
			query += ",";
		}
		query = query.substring(0, query.length()-1);
		query += ")};";
		stmt = (CallableStatement) connection.prepareCall(query);
		for(int i = 1; i<=args.length; i++) {
			stmt.setObject(i, args[i-1]);
		}
		stmt.execute();
	}
	
	public ResultSet runReadProcedure(String functioname, Object... args) throws SQLException
	{
		CallableStatement stmt = null;
		String query = String.format("{call %s", functioname);
		query += "(";
		System.out.println(query);
		System.out.println(args);
		for(Object o : args)
		{        	
			query += "?";
			query += ",";
		}
		
		query = query.substring(0, query.length()-1);
		if(args.length==0) {
			query += "(";
		}
		query += ")};";
		System.out.println(query);
		stmt = (CallableStatement) connection.prepareCall(query);
		for(int i = 1; i<=args.length; i++) {
			stmt.setObject(i, args[i-1]);
		}
		stmt.execute();
		ResultSet res = stmt.getResultSet();
		return res;
	}
}
