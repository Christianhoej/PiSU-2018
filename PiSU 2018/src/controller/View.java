package controller;

import java.awt.Color; 
import java.util.HashMap;
import java.util.Map;

import model.Player;
import model.Property;
import model.Account;
import model.Fields;
import model.Game;
import gui_fields.GUI_Brewery;
import gui_fields.GUI_Car;
import gui_fields.GUI_Car.Pattern;
import gui_fields.GUI_Car.Type;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Shipping;
import gui_fields.GUI_Street;
import gui_main.GUI;
import designpatterns.Observer;
import designpatterns.Subject;
import model.RealEstate;

/**
 * This clas takes information from model and shows it to the user. 
 * 
 * @author 
 *
 */


public class View implements Observer {
	private Game game;
	private GUI gui;

	private Map<Player,GUI_Player> player2GuiPlayer = new HashMap<Player,GUI_Player>();
	private Map<Player,Integer> player2position = new HashMap<Player,Integer>();
	private Map<Fields,GUI_Field> field2GuiField = new HashMap<Fields,GUI_Field>();

	private boolean disposed = false;

	/**
	 * Constructor 
	 * initializes the view. 
	 * 
	 * @param game
	 * @param gui
	 */
	public View(Game game, GUI gui) { 
		this.game = game;
		this.gui = gui;
		GUI_Field[] guiFields = gui.getFields();

		int i = 0;
		for(Fields field : game.getFields()) {
			field2GuiField.put(field, guiFields[i++]);
			field.attach(this);
		}

		for(Player player : game.getPlayers()) {
			GUI_Car guiCar = new GUI_Car(player.getColour(), Color.black, Type.CAR, Pattern.FILL);
			GUI_Player guiPlayer = new GUI_Player(player.getName(), player.getAccount().getCash(), guiCar);
			player2GuiPlayer.put(player, guiPlayer);
			gui.addPlayer(guiPlayer);

			player.attach(this);
			player.getAccount().attach(this);

			updatePlayer(player);
		}

	}

	/**
	 * Updates the subject. 
	 */
	@Override
	public void update(Subject subject) {
		if (!disposed) {
			if (subject instanceof Player) {
				updatePlayer((Player) subject);
			}
			else if (subject instanceof Property) {
				updateProperty((Property) subject);
			}
			else if (subject instanceof Account) {
				updateAccount((Account) subject);
			}
		}


		// TODO Auto-generated method stub

	}

	/**
	 * Updates the account 
	 * @param account
	 */
	private void updateAccount(Account account) {
		GUI_Player guiPlayer = this.player2GuiPlayer.get(account.getOwner());
		if(account!=null) {
			guiPlayer.setBalance(account.getCash());
		}
	}


	private void updateProperty(Property property) {
		if(property != null) {
			if(property.getColourSystem().equals("darkgreen")) {
				GUI_Brewery guiField = (GUI_Brewery) this.field2GuiField.get(property);
				if(guiField != null) {
					if(!property.getMortgage()&& property.getOwner()!=null) {
						guiField.setBorder(property.getOwner().getColour());
						guiField.setOwnerName(property.getOwner().getName());
					}
					else if(property.getOwner()!=null) {
						guiField.setOwnerName(property.getOwner().getName()+ ", pantsat");
					}
					else if(property.getOwner() == null) {
						guiField.setBorder(Color.black);
						guiField.setOwnerName("");
					}
				}
			}

			else if (property.getColourSystem().equals("ship")) {
				GUI_Shipping guiField = (GUI_Shipping) this.field2GuiField.get(property);

				if(guiField != null) {
					if(!property.getMortgage()&& property.getOwner()!=null) {
						guiField.setBorder(property.getOwner().getColour());
						guiField.setOwnerName(property.getOwner().getName());
					}
					else if(property.getOwner()!=null) {
						guiField.setOwnerName(property.getOwner().getName()+ ", pantsat");
						guiField.setBorder(property.getOwner().getColour());
					}
					else if(property.getOwner() == null) {
						guiField.setBorder(Color.black);
						guiField.setOwnerName("");
					}
				}

			}
			else {
				GUI_Street guiField = (GUI_Street) this.field2GuiField.get(property);

				if(guiField != null) {
					if(!property.getMortgage() && property.getOwner()!=null) {
						guiField.setBorder(property.getOwner().getColour());
						guiField.setOwnerName(property.getOwner().getName());
					}
					else if(property.getOwner()!=null) {
						guiField.setOwnerName(((RealEstate)property).getOwner().getName() + ", pantsat");
						guiField.setBorder(property.getOwner().getColour());
					}

					else if(property.getOwner() == null) {
						guiField.setBorder(Color.black);
						guiField.setOwnerName("");
					}


					if(((RealEstate)property).getHouses()==5) {
						guiField.setHotel(true);
					}
					else if(((RealEstate)property).getHouses()>=0 && ((RealEstate)property).getHouses()<5) {
						guiField.setHouses(((RealEstate)property).getHouses());
					}
				}
			}
		}
	}

	/**
	 * This method updates a player's state in the GUI. Right now, this
	 * concerns the players position and balance only. But, this should
	 * also include other information (being i prison, available cards,
	 * ...)
	 * 
	 * @param player the player who's state is to be updated
	 */
	private void updatePlayer(Player player) {
		GUI_Player guiPlayer = this.player2GuiPlayer.get(player);
		if (guiPlayer != null) {

			GUI_Field[] guiFields = gui.getFields();
			Integer oldPosition = player2position.get(player);
			if(player.isBroke()) {
				guiPlayer.setName(player.getName() + " (broke)");
				if (oldPosition != null && oldPosition < guiFields.length) {
					guiFields[oldPosition].setCar(guiPlayer, false);
				}
			}
			else {
				if (oldPosition != null && oldPosition < guiFields.length) {
					guiFields[oldPosition].setCar(guiPlayer, false);
				}

				int pos = player.getPosition();
				if (pos < guiFields.length) {
					player2position.put(player, pos);
					guiFields[pos].setCar(guiPlayer, true);
				} 
				else if (player.getInPrison()>0) {
					guiPlayer.setName(player.getName() + " (in prison)");
				} 
				else {
					guiPlayer.setName(player.getName());
				}	
			}
		}
	}
}
