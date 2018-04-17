package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TestGunnDatabase {
    private final String HOST     = "localhost";
    private final int    PORT     = 3306;
    private final String DATABASE = "matador1.2";
    private final String USERNAME = "root"; 
    private final String PASSWORD = "";
    private Connection connection;
     
    public TestGunnDatabase() {
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

	public void runProcedure(String functioname, Object... args) throws SQLException
	{
	    Statement stmt = connection.createStatement();
	    String query = String.format("call %s", functioname);
	    query += "(";
	    for(Object o : args)
	    {        	
	    	if(o.getClass()==String.class) query += "'";
	    	query += o.toString();
	    	if(o.getClass()==String.class) query += "'";
	    	query += ",";
	    }
	    query = query.substring(0, query.length()-1);
	    query += ");";
	    stmt.executeUpdate(query);
    }
}
