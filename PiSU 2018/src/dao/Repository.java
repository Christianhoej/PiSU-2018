package dao;

public class Repository 
{
	private static Repository _instance;
    private Connector _connector;
	private GameDAO _GameOAD; 
	
	private Repository()
	{
		_connector = new Connector();
	}
	
	public IGameDAO sensorData()
	{		
		if(_GameOAD==null) 
			_GameOAD = new GameDAO();
		return _GameOAD;	
	}
	
	public static Repository getInstance()
	{
		if(_instance==null) 
			_instance = new Repository();
		return _instance;	
	}
}