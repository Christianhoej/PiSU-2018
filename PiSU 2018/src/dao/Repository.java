package dao;
import test.Connector;


public class Repository 
{
	private static Repository _instance;
    private Connector _connector;
	private GameOAD _GameOAD; 
	
	private Repository()
	{
		_connector = new Connector();
	}
	
	public IGameOAD sensorData()
	{		
		if(_GameOAD==null) 
			_GameOAD = new GameOAD(_connector);
		return _GameOAD;	
	}
	
	public static Repository getInstance()
	{
		if(_instance==null) 
			_instance = new Repository();
		return _instance;	
	}
}