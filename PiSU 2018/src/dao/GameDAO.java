package dao;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import model.Property;
import model.RealEstate;
import model.Utility;
import model.Player;
import model.Fields;
import model.Game;

public class GameDAO implements IGameDAO
{	
	private Connector connect = new Connector();
	final private String RED = "red";
	final private String BLUE = "blue";
	final private String BLACK = "black";
	final private String WHITE = "white";
	final private String YELLOW = "yellow";
	final private String GREEN = "green";

	@Override
	public void createGame(Game game) throws SQLException {
		Connection con = connect.getConnection();

		try {
			con.setAutoCommit(false);

			CallableStatement stmt =null;
			stmt = (CallableStatement) con.prepareCall("{call create_game(?)}");
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.execute();
			game.setGameID(stmt.getInt(1));
			createPlayers(game, con);
			createProperties(game, con);

			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			// TODO error handling
			e.printStackTrace();
			System.err.println("Some DB error");

			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO error handling
				e1.printStackTrace();
			}

		}
	}

	@Override
	public void createPlayers(Game game, Connection con) throws SQLException {	
		//Connection con = connect.getConnection();

		try {
			con.setAutoCommit(false);

			CallableStatement stmt = (CallableStatement) con.prepareCall("{call create_player(?,?,?,?)}");
			for (Player player: game.getPlayers()) {
				String color;
				if(player.getColour().equals(Color.black)) {
					color = BLACK;
				}
				else if(player.getColour().equals(Color.BLUE)) {
					color = BLUE;
				}
				else if(player.getColour().equals(Color.red)) {
					color = RED;
				}
				else if(player.getColour().equals(Color.WHITE)) {
					color = WHITE;
				}
				else if(player.getColour().equals(Color.yellow)) {
					color = YELLOW;
				}
				else {
					color = GREEN;
				}


				stmt.setInt(1, game.getGameID());
				stmt.setString(2, player.getName());
				stmt.setString(3, color);
				stmt.registerOutParameter(4, Types.INTEGER);
				stmt.execute();
				player.setPlayerID(stmt.getInt(4));
			}		
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Some DB error");

			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO error handling
				e1.printStackTrace();
			}

		}
	}

	@Override
	public void createProperties(Game game, Connection con) throws SQLException {
		//		Connection con = connect.getConnection();		

		try {
			con.setAutoCommit(false);

			CallableStatement stmt = (CallableStatement) con.prepareCall("{call create_property(?)}");
			stmt.setInt(1, game.getGameID());
			stmt.execute();
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Some DB error");

			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO error handling
				e1.printStackTrace();
			}

		}
	}

	// Nok overflødig
	@Override
	public int readGame(Game game) throws SQLException {
		//		Connection con = connect.getConnection();
		//		CallableStatement stmt = (CallableStatement) con.prepareCall("{call read_game(?,?)}");
		//		stmt.setTimestamp(1, gameDate);
		//		stmt.registerOutParameter(2, Types.INTEGER);
		//		stmt.execute();
		//		int gameID = stmt.getInt(2);
		//		return gameID;
		return 0;
	}

	@Override
	public ArrayList<Game> readAllGames() throws SQLException {
		Connection con = connect.getConnection();

		try {
			con.setAutoCommit(false);

			CallableStatement stmt = (CallableStatement) con.prepareCall("{call get_allGames()}");
			stmt.execute();
			ResultSet res = stmt.getResultSet();

			ArrayList<Game> array = new ArrayList<Game>();
			while(res.next()) {
				Game game = new Game();
				game.setGameID(res.getInt("gameID"));
				game.setPlayerAmount(res.getInt("players"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				String date  = dateFormat.format(res.getTimestamp("startDate"));
				game.setGameDate(date);
				array.add(game);
			}


			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Some DB error");

			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO error handling
				e1.printStackTrace();
			}

		}
		return array;
	}


	@Override
	public ArrayList<Player> readPlayers(Game game) throws SQLException {
		Connection con = connect.getConnection();

		try {
			con.setAutoCommit(false);


			CallableStatement stmt = (CallableStatement) con.prepareCall("{call read_player(?)}");
			stmt.setInt(1, game.getGameID());
			stmt.execute();
			ResultSet res = stmt.getResultSet();
			ArrayList<Player> array = new ArrayList<Player>();
			while(res.next()) {
				Player player = new Player();
				player.setName(res.getString("name"));
				player.setPlayerID(res.getInt("playerID"));
				String color = res.getString("carColour");
				switch(color) {
				case BLUE: player.setColour(Color.blue);
				break;
				case RED: player.setColour(Color.red);
				break;
				case BLACK: player.setColour(Color.black);
				break;
				case GREEN: player.setColour(Color.green);
				break;
				case WHITE: player.setColour(Color.white);
				break;
				case YELLOW: player.setColour(Color.yellow);
				break;
				}


				if(color.equals("blue")) {
					player.setColour(Color.blue);
				}
				else if(color.equals("red")) {
					player.setColour(Color.red);
				}
				else if(color.equals("white")) {
					player.setColour(Color.white);
				}
				else if(color.equals("black")) {
					player.setColour(Color.BLACK);
				}
				else if(color.equals("yellow")) {
					player.setColour(Color.YELLOW);
				}
				else {
					player.setColour(Color.GREEN);
				}
				player.setPosition(res.getInt("position"));
				player.setInPrison(res.getInt("prison"));
				player.setAccount(res.getInt("balance"));
				player.getAccount().setPrisonCard(res.getInt("getOutPrison")); 
				player.getAccount().setOwner(player);
				player.setBroke(res.getBoolean("broke"));
				boolean current = res.getBoolean("current");
				if(current) {
					game.setCurrentPlayer(player);
				}
				array.add(player);
			}
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Some DB error");

			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO error handling
				e1.printStackTrace();
			}

		}

		return array ;
	}


	@Override
	public ArrayList<Fields> readProperty(Game game) throws SQLException {
		Connection con = connect.getConnection();


		//Skal den være her eller lige før Callable statement
		try {
			con.setAutoCommit(false);

			ArrayList<Player> player = game.getPlayers();
			ArrayList<Fields> field = game.getFields();
			for(int i=0; i<player.size(); i++) {

				CallableStatement stmt = (CallableStatement) con.prepareCall("{call read_property(?, ?)}");
				stmt.setInt(1, game.getGameID());
				stmt.setInt(2, player.get(i).getPlayerID());
				stmt.execute();
				ResultSet res = stmt.getResultSet();

				while(res.next()) {
					if(field.get(res.getInt("fieldNumber")) instanceof RealEstate) {
						((RealEstate) field.get(res.getInt("fieldNumber"))).setHouses(res.getInt("houses"));
					}

					((Property) field.get(res.getInt("fieldNumber"))).setOwner(player.get(i));
					//				System.out.println(((Property) field.get(res.getInt("fieldNumber"))).setOwner(player.get(i)));

					if(res.getInt("houses")==-1) {
						((Property) field.get(res.getInt("fieldNumber"))).setMortgage(true);
					}
					else {
						((Property) field.get(res.getInt("fieldNumber"))).setMortgage(false);
					}
					player.get(i).addOwnedProperties(((Property) field.get(res.getInt("fieldNumber"))));
				}
			}

			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Some DB error");

			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO error handling
				e1.printStackTrace();
			}
		}

		return field;
	}

	@Override
	public void updatePlayer(Game game) throws SQLException {
		Connection con = connect.getConnection();	

		//Skal den være her eller lige før callable statement
		try {
			con.setAutoCommit(false);

			ArrayList<Player> player = game.getPlayers();
			for(int i = 0; i<player.size(); i++) {
				CallableStatement stmt = (CallableStatement) con.prepareCall("{call update_player(?,?,?,?,?,?,?,?)}");
				stmt.setInt(1, player.get(i).getPlayerID());
				stmt.setInt(2, game.getGameID());
				stmt.setInt(3, player.get(i).getPosition());
				stmt.setInt(4, player.get(i).getInPrison());
				stmt.setInt(5, player.get(i).getAccount().getPrisonCard());
				stmt.setInt(6, player.get(i).getAccount().getCash());
				stmt.setBoolean(7, player.get(i).isBroke());
				stmt.setBoolean(8, game.getCurrentPlayer().equals(player.get(i)));
				stmt.execute();
			}
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Some DB error");

			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO error handling
				e1.printStackTrace();
			}
		}
	}


	@Override
	public void updateProperties(Game game) throws SQLException {
		Connection con = connect.getConnection();

		//Skal den være her eller lige før callable statement 
		try {
			con.setAutoCommit(false);

			ArrayList<Fields> field = game.getFields();
			ArrayList<Player> player = game.getPlayers();

			for(int i = 0; i<player.size(); i++) {
				for(int j = 0; j<player.get(i).getOwnedProperties().size(); j++) {
					CallableStatement stmt = (CallableStatement) con.prepareCall("{call update_property(?,?,?,?)}");

					if(player.get(i).getOwnedProperties().size()>0) {
						System.out.println("Der er en EJER!!!!!");

						System.out.println(game.getGameID());
						stmt.setInt(1, game.getGameID());
						stmt.setInt(2, player.get(i).getOwnedProperties().get(j).getFieldNumber());
						stmt.setInt(3, player.get(i).getPlayerID());
						if(player.get(i).getOwnedProperties().get(j) instanceof RealEstate) {
							stmt.setInt(4, ((RealEstate)player.get(i).getOwnedProperties().get(j)).getHouses());
						}
						else {
							stmt.setInt(4, 0);
						}
						stmt.execute();
					}

				}
			}

			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Some DB error");

			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO error handling
				e1.printStackTrace();
			}
		}

	}



	@Override
	public void updateSaveDate(Game game) throws SQLException {
		Connection con = connect.getConnection();

		try {
			con.setAutoCommit(false);

			CallableStatement stmt = (CallableStatement) con.prepareCall("{call update_saveDate(?)}");
			stmt.setInt(1, game.getGameID());
			stmt.execute();

			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Some DB error");

			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO error handling
				e1.printStackTrace();
			}
		}	

	}

	// Husk at tjekke for endGame, når vi har et spil at tjekke for.
	@Override
	public void endGame(Game game) throws SQLException {
		Connection con = connect.getConnection();

		try {
			con.setAutoCommit(false);

			CallableStatement stmt = (CallableStatement) con.prepareCall("{call end_game(?)}");
			stmt.setInt(1, game.getGameID());
			stmt.execute();

			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Some DB error");

			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO error handling
				e1.printStackTrace();
			}
		}


	}

}

