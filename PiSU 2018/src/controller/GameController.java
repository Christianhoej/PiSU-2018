package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import board.Gameboard;
import gui_main.GUI;
import model.Dice;
import model.Fields;
import model.Game;
import model.Player;
import model.Property;
import model.RealEstate;
import model.Txt;
import model.Utility;
import model.Winner;
import model.Tax;


public class GameController {

	private Game game;
	private GUI gui;
	private String[] guiMessages = Txt.fileString("GameMessages.txt");
	private View view;
	private Random rand = new Random();

	public GameController(Game game) {
		this.game = game;
		Gameboard gameboard = new Gameboard();
		gui = new GUI(gameboard.makeBoard());
	}




	public void createPlayers() {

		//		GUI gui = new GUI(); // SKAL SLETTES --> bruger den bare til at teste metoden
		int playerAmount = Integer.parseInt(gui.getUserSelection("Hvor mange spillere skal i være?", "3", "4", "5", "6"));
		ArrayList<String> color = new ArrayList<String>();
		color.add("Blå");
		color.add("Grøn");
		color.add("Gul");
		color.add("Rød");
		color.add("Sort");
		color.add("Hvid");

		for(int i = 0; i<playerAmount; i++) {
			Player player = new Player();
			player.setName(gui.getUserString("Indsast navnet på spiller " + (i+1)));
			for(int k=i-1; k>=0; k--) { //Loop der checker om 2 spillere hedder det samme
				while(game.getPlayers().get(k).getName().equals(player.getName()))// tjekker om spillerne har samme navn
				{
					player.setName(gui.getUserString("You cant have the same name as another player. Please try again"));   //skulle have været med i oversætter klassen
					k=i-1; // tjekker forfra om der er ens navne
				}
			}		
			// Adds the player to game.
			game.addPlayer(player);
			String[] colorString = new String[color.size()];
			colorString = color.toArray(colorString); 
			String carColor = gui.getUserSelection("Hvilken farve bil vil du have?", colorString);
			switch(carColor) {
			case "Blå": player.setColour(Color.blue); break;
			case "Grøn": player.setColour(Color.green); break;
			case "Gul": player.setColour(Color.yellow); break;
			case "Rød": player.setColour(Color.red); break;
			case "Sort": player.setColour(Color.black); break;
			case "Hvid": player.setColour(Color.white); break;
			}
			color.remove(carColor);
			player.setPosition(0);
		}
	}

	public void createGUI() {
		this.view = new View(game, gui);
	}


	/**
	 * 
	 * @param player
	 * @param playerArray
	 * @param antalSpillere
	 *  
	 */
	private void trade(Player player) {
		int currentPlayer=-1;
		for (int i=0; i<game.getPlayers().size(); i++) {
			if (player.equals(game.getPlayers().get(i))) {
				currentPlayer=i;
			}
		}
		Player[] otherPlayer = new Player[game.getPlayers().size()-1];
		for(int i=0; i<otherPlayer.length; i++) {
			otherPlayer[i] = game.getPlayers().get(i);
			if(otherPlayer[i].equals(game.getPlayers().get(currentPlayer))) {
				i--;
			}
		}


		int tradingPlayer = -1;
		while(tradingPlayer==-1) {
			String input = gui.getUserString("Indtast navnet på den spiller, du gerne vil handle med: ");
			for(int i=0; i<game.getPlayers().size(); i++) {
				if (input.equals(game.getPlayers().get(i).getName())) {
					if(player.getName().equals(input)) {
						gui.showMessage("Du kan ikke handle med dig selv. Prøv igen");
					}
					else {
						tradingPlayer = i;
						gui.showMessage(player.getName() + "vil gerne handle med " + game.getPlayers().get(tradingPlayer).getName());
						ArrayList<String> currentOwnedProp = new ArrayList<String>();
						ArrayList<String> tradeOwnedProp = new ArrayList<String>();
						for(int j=0; j<player.getOwnedProperties().length; j++) {
							if(player.getOwnedProperties()[j] == 1) {
								currentOwnedProp.add(game.getFields().get(j).getFieldName());
							}
							if(game.getPlayers().get(tradingPlayer).getOwnedProperties()[j] == 1) {
								tradeOwnedProp.add(game.getFields().get(j).getFieldName());
							}

						}
						String[] tradePlayerOwnedProp = new String[tradeOwnedProp.size()];
						tradePlayerOwnedProp = tradeOwnedProp.toArray(tradePlayerOwnedProp); 

						String[] currentPlayerOwnedProp = new String[currentOwnedProp.size()];
						currentPlayerOwnedProp = currentOwnedProp.toArray(currentPlayerOwnedProp); 

						if(currentPlayerOwnedProp.length>0) {
							String propertyCurrent = gui.getUserSelection("Hvilken grund vil du bytte ", currentPlayerOwnedProp);
						}
						else {

							// Mangler
						}


					}

				}
			}
		}
		gui.showMessage("Du har ikke valgt et gyldigt navn. Prøv igen");
	}



	/**
	 * Method for when a property is up for auction.
	 * @param player
	 * @param playerArray
	 */
	public void auction(Player player, Property property) {
		int currentPlayer = -1;
		gui.showMessage(property.getFieldName() + " er sat på auktion!");	
		for (int i=0; i<game.getPlayers().size(); i++) {
			if (player.equals(game.getPlayers().get(i))) {
				currentPlayer=i;
			}
		}
		int currentBid=100;
		//New array with the player originally landing on the field, as the last. 
		Player[] auctionArray = new Player[game.getPlayers().size()];
		for(int i = currentPlayer+1; i<game.getPlayers().size();i++) {
			auctionArray[i]= game.getPlayers().get(i);
		}
		for(int i=0; i<=currentPlayer; i++) {
			auctionArray[i] = game.getPlayers().get(i);
		}
		//The amount of player withdrawn from the auction. 
		int playersOut=0;
		//index at the highest bidder at the current time. 
		int auctionWinner=-1;
		//Running as long as there are at least 2 players left
		while (playersOut<auctionArray.length-1) {
			for (int i=0; i<auctionArray.length; i++) {
				if (auctionArray[i]==null) {
					continue;
				}
				//Get user input, of the amount of which the player will bid over the current bid
				int bidOver = gui.getUserInteger(auctionArray[i].getName()+ " hvor meget vil du bydde over " + currentBid, 0, auctionArray[i].getAccount().getCash()-currentBid);
				//If the player bids 0, he is removed from the auction.
				if (bidOver==0) {
					auctionArray[i]=null;
					playersOut++;
				}
				//The player with the current highest bid, is set as winner, until the next bid.
				else {
					auctionWinner=i;
					currentBid += bidOver;
				}
			}
		}
		//Updates cash, assets and owned properties of the winner.
		//Updates the owner of the field. 
		if(auctionWinner != -1) {
			auctionArray[auctionWinner].getAccount().updateCash(-currentBid);
			auctionArray[auctionWinner].getAccount().updateAssetValue(property.getPrice());
			property.setOwner(auctionArray[auctionWinner]);
			auctionArray[auctionWinner].addOwnedProperties(property.getFieldNumber());
			gui.showMessage(auctionArray[auctionWinner].getName() + " har købt grunden for " + currentBid);
		}
		else {
			gui.showMessage("Ingen har valgt at bydde på grunden, spillet fortsættes.");
		}
	}


	public void pawn() {

	}

	private void loadGame() {
		//Kald til databaser om at loade
	}

	public void runGame() {
		setUpGame();

		ArrayList<Player> players = game.getPlayers();
		Player currentPlayer = game.getCurrentPlayer();

		// Getting the index for the currentPlayer - useful when loading a game.
		int currentIndex=0;
		for(int i = 0; i<players.size(); i++) {
			if(currentPlayer.equals(players.get(i))) {
				currentIndex = i;
				break;
			}
		}
		boolean noWinner=true;
		while(noWinner) {
			if(!currentPlayer.isBroke()) {
				playerTurn(currentPlayer);
			}

		}


	}

	public void playerTurn(Player player) {
		boolean throwDouble=false;
		do {
			if(throwDouble) {
				gui.showMessage("Du har kastet to ens og får et ekstra kast");
			}

			if(player.getInPrison()>0) {
				playerInPrison(player);
			}

			Dice dice = new Dice();
			dice.rollDice();
			int[] faceValue = dice.getFaceValue();
			gui.setDice(faceValue[0], faceValue[1]);
			throwDouble = (faceValue[0]== faceValue[1]);
			int doubleCount=0;
			if(throwDouble && player.getInPrison()>0) {
				doubleCount++;
				player.setInPrison(0);
				gui.showMessage("Du har kastet to ens, mens du var i fængsel, og kan "
						+ "forstætte ved at rykke " +(faceValue[0]+faceValue[1]) + "felter frem");
				if(doubleCount>2) {
					gui.showMessage("Du har kastet to ens tre gange i træk, og skal derfor "
							+ "i fængsel");
					player.setPosition(10);
					player.setInPrison(1);
					return;
				}
			}
			else if(player.getInPrison()>0) {
				gui.showMessage("Du kastede ikke to ens, og forbliver i fængsel en runde til");
				return;
			}
			int position = player.getPosition() + faceValue[0]+faceValue[1];
			moveToField(player, position);

		}while(throwDouble);
	}

	public void moveToField(Player player, int position) {
		player.setPosition(position % game.getFields().size());
		if(position>player.getPosition()) {
			gui.showMessage("Du har passeret start, og modtager 4000 kr");
			player.getAccount().updateCash(4000);
		}
		gui.showMessage(player.getName() + " har landet på " + game.getFields().get(player.getPosition()).getFieldName());
		game.getFields().get(player.getPosition()).landOnField(this);
	}

	public void playerInPrison(Player player) {
		String choice="";
		// if the player is in prison and has a get out of jail free card. 
		if(player.getAccount().getPrisonCard()>0) {
			// if the player is in prison, has a get out of jail free card and 
			// has over 1000kr
			if(player.getAccount().getCash()>=1000) {
				// the player can choose between using the get out of jail free
				// card, paying 1000 kr to the bank and throw the dices.
				choice = gui.getUserButtonPressed("Du er i fængsel. Du har følgende"
						+ " muligheder for at komme ud af fængslet", "Betal 1000 kr",
						"Brug fængselskort", "Kast terningerne");
			}
			else {
				// the player can choose between using the get out of jail free
				// card and throw the dices.
				choice = gui.getUserButtonPressed("Du er i fængsel. Du har følgende"
						+ " muligheder for at komme ud af fængslet", "Brug "
								+ "fængselskort", "Kast terningerne");
			}
		}
		// if the player has over 1000kr
		else if(player.getAccount().getCash()>=1000) {
			// the player can choose between paying 1000 kr to the bank and throw 
			// the dices.
			choice = gui.getUserButtonPressed("Du er i fængsel. Du har følgende "
					+ "muligheder for at komme ud af fængslet", "Betal 1000 kr", 
					"Kast terningerne");
		}
		else {
			// the player has to throw the dices.
			choice = gui.getUserButtonPressed("Du er i fængsel. Da du hverken har et"
					+ " fængselskort eller nok penge, må du kaste med terningerne", 
					"Kast terningerne");
		}

		switch(choice) {
		case "Betal 1000 kr": 	player.getAccount().updateCash(1000);
		player.setInPrison(0);						
		gui.showMessage("Du har betalt 1000 kr og er kommet "
				+ "ud af fængslet. Fortsæt turen ved at kaste"
				+ " med terningerne");
		break;
		case "Brug fængselskort": player.getAccount().updatePrisonCard(-1);
		player.setInPrison(0);
		gui.showMessage("Du har brugt et fængsels kort og er "
				+ "kommet ud af fængslet. Fortsæt turen ved at "
				+ "kaste med terningerne");
		break;
		case "Kast terningerne": break;
		}

	}



	//			gui.setDice(5, 6);
	//			System.out.println(currentPlayer.getPosition()+11);
	//			currentPlayer.setPosition(currentPlayer.getPosition()+11);
	//			game.getFields().get(currentPlayer.getPosition()).landOnField(this);
	//			currentPlayer.setPosition(13);
	//			game.getFields().get(currentPlayer.getPosition()).landOnField(this);
	//			currentPlayer.setPosition(14);
	//			game.getFields().get(currentPlayer.getPosition()).landOnField(this);
	//			game.getFields().get(currentPlayer.getPosition()).setHouses(4);



	//set player to start
	//		while (Winner.testIfWinner(player) == false){
	//				
	//				if(game.getCurrentPlayer().getInPrison()>0) {//player is in prison
	//					
	//if player wants to get out of jail before rolling dies
	//Mulighed for at anvende kort/betale for at komme ud af fængsel

	//else {
	//roll dices
	//if d1== d2
	//Update Player.InJail();
	//}
	//				}




	//Roll dices
	//player uses turn roll of dies to either move or not

	//move to Field //land on field
	//check if passes start


	//If dice1 == dice2
	//roll again //Recursion
	//update 


	//update currentPlayer


	//	}




	private void setUpGame() {

		String selection = gui.getUserButtonPressed("Vil du indlæse et gemt spil eller starte et nyt?", "Indlæs spil", "Start nyt spil");

		if(selection.equals("Indlæs spil")) {
			loadGame();
		}
		else {
			createPlayers();
			createGUI();
		}
	}

	/**
	 * Method, where a specific player pay an amount of money.
	 * @param player the player which is paying money
	 * @param amount the amount the player is paying
	 * 
	 * @author Gunn
	 */
	public void payMoney(Player player, int amount) {
		player.getAccount().updateCash(-amount);
	}


	/**
	 * Method, where a specific player receives an amount of money.
	 * @param player the player which is receiving money
	 * @param amount the amount the player is receiving
	 * 
	 * @author Gunn
	 */
	public void receiveMoney(Player player, int amount) {
		player.getAccount().updateCash(-amount);
	}


	/**
	 * This method implements activity of a player receiving the
	 * birthday chance card, where the player receives 200 kr from
	 * all the other players.
	 * 
	 * @param player the player receiving birthday money.
	 */
	public void playerBirthday(Player player) {
		for(int i = 0; i<game.getPlayers().size(); i++) {
			if(player.equals(game.getPlayers().get(i))) {
				continue;
			}
			else {
				receiveMoney(player, 200);
				payMoney(game.getPlayers().get(i), 200);
			}
		}
	}



	public void addOwnedProperties(Player player, int fieldNumber) {
		player.addOwnedProperties(fieldNumber);
	}


	/**
	 * Method, to find out, if an owner of a real estate, also owns
	 * the other real estates in the same color.
	 * 
	 * @param property the real estate the player has landed on
	 * @param player the player that has landed on the real estate
	 */
	public void ownedRealEstateSameColour(RealEstate realEstate, Player player) {
		int colourCount = 0;
		int ownerCount = 0;
		for (int i = 0; i<game.getFields().size(); i++) {
			if (game.getFields().get(i).getColourSystem().equals(realEstate.getColourSystem())){
				colourCount++;
				if (game.getFields().get(i).getOwner().equals(realEstate.getOwner())){
					ownerCount++;
				}
			}
		}
		if(colourCount == ownerCount) {

			switch(realEstate.getHouses()){
			case 0: receiveMoney(realEstate.getOwner(), realEstate.getRent()*2); //PRIS FOR ET HUS
			payMoney(player, realEstate.getRent()*2); //MINUSPRIS FOR ET HUS
			gui.showMessage("Du er landet på " + realEstate.getOwner() +"'s ejendom og skal betale " + (realEstate.getRent()*2));
			break;
			case 1: receiveMoney(realEstate.getOwner(), 1); //PRIS FOR ET HUS
			payMoney(player, 1); //MINUSPRIS FOR ET HUS
			gui.showMessage("Du er landet på " + realEstate.getOwner() +"'s ejendom og skal betale " + 1);
			break;
			case 2: receiveMoney(realEstate.getOwner(), 2); // PRIS FOR TO HUSE
			payMoney(player, 2); // MINUS PRIS FOR TO HUSE
			gui.showMessage("Du er landet på " + realEstate.getOwner() +"'s ejendom og skal betale " + 2);
			break;
			case 3: receiveMoney(realEstate.getOwner(), 3);;
			payMoney(player, 3);
			gui.showMessage("Du er landet på " + realEstate.getOwner() +"'s ejendom og skal betale " + 3);
			break;
			case 4: receiveMoney(realEstate.getOwner(), 4);
			payMoney(player, 4);
			gui.showMessage("Du er landet på " + realEstate.getOwner() +"'s ejendom og skal betale " + 4);
			break;
			case 5: receiveMoney(realEstate.getOwner(), 5); // FOR HOTEL
			payMoney(player, 5); // MINUS HOTEL
			gui.showMessage("Du er landet på " + realEstate.getOwner() +"'s ejendom og skal betale " + 5);
			break;
			}
		} else { 
			receiveMoney(player, realEstate.getRent());
			payMoney(player, realEstate.getRent());
		}	
	}

	/**
	 * Method, to find out, if an owner of an utility, also owns
	 * one or many other real estates in the same color.
	 * 
	 * @param property the real estate the player has landed on
	 * @param player the player that has landed on the real estate
	 */
	public void ownedUtilitiesSameType(Utility utility, Player player) {
		int colourCount = 0;
		int ownerCount = 0;
		for (int i = 0; i<game.getFields().size(); i++) {
			if (game.getFields().get(i).getColourSystem().equals(utility.getColourSystem())){
				colourCount++;
				if (game.getFields().get(i).getOwner().equals(utility.getOwner())){
					ownerCount++;
				}
			}
		}
		// If there only exist two types of this utility, it is a "bryggeri"
		if (colourCount == 2) {
			switch(ownerCount) {
			case 1: receiveMoney(utility.getOwner(), utility.getRent()/*gange med tærningeværdi*/); // PRIS FOR EN TYPE * øjenværdi --> HUSK DER SKAL ÆNDRES SÅ DER TÆLLES FOR TO TERNINGER
			payMoney(player, utility.getRent()/*gange med tærningeværdi*/);
			gui.showMessage(guiMessages[38] + utility.getOwner().getName() +guiMessages[39] + 1);
			break;
			case 2: receiveMoney(utility.getOwner(),utility.getRent()); // PRIS FOR TO TYPER
			payMoney(player, utility.getRent()/*gange med tærningeværdi*/);

			gui.showMessage(guiMessages[40] + utility.getOwner().getName() + guiMessages[41] + 2);
			break;
			}

		}
		// else it is a "rederi".
		else {
			switch(ownerCount){
			case 1: receiveMoney(utility.getOwner(), utility.getRent()); // PRIS FOR EN TYPE
			payMoney(player, utility.getRent());
			game.getCurrentPlayer().getAccount().updateCash(-1); //MINUSPRIS FOR EN TYPE
			gui.showMessage(guiMessages[42] + utility.getOwner().getName() + guiMessages[43] + 1);
			break;
			case 2: receiveMoney(utility.getOwner(), utility.getRent()); // PRIS FOR TO TYPE
			payMoney(player, utility.getRent());
			gui.showMessage(guiMessages[44] + utility.getOwner().getName() + guiMessages[45] + 2);
			break;
			case 3: receiveMoney(utility.getOwner(), utility.getRent()); // PRIS FOR TRE TYPE
			payMoney(player, utility.getRent());
			gui.showMessage(guiMessages[46] + utility.getOwner().getName() + guiMessages[47] + 3);
			break;
			case 4: receiveMoney(utility.getOwner(), utility.getRent()); // PRIS FOR FIRE TYPE
			payMoney(player, utility.getRent());
			gui.showMessage(guiMessages[48] + utility.getOwner().getName() +guiMessages[49] + 4);
			break;
			} // skal opdateret fra txt filen af rederileje ^^VIGTIGT
		}

	}
	public Game getGame() {
		return game;
	}


	public void offerToBuyProperty(Property property) {
		Player player = game.getCurrentPlayer();
		if (property.isForSale()) {
			//Vil spilleren købe den ellers skal den sættes på auktion 

			String playerChoice = gui.getUserButtonPressed(player.getName()+ " " + guiMessages[8] + property.getFieldName() + guiMessages[9] + property.getPrice(), "Nej", "Ja");

			if (playerChoice.equals("Ja")) {
				property.setForSale(false);
				property.setOwner(player);
				payMoney(player, property.getPrice());
				addOwnedProperties(player, property.getFieldNumber());
			}
			else auction(player, property);
		}

		//Hvis grunden ikke er til salg
		//Spilleren kan, når han lander på grunden:
		//Betale leje 
		//Ikke betale leje (Hvis ejeren er i fængsel, eller ved pansætning
		//Sætte ejendommen på auktion. 
		else if (!property.isForSale()) {
			if (property.getOwner().equals(player)) {
				gui.showMessage(property.toString() + guiMessages[13]);
			}
			else if (property.getOwner().getInPrison()!= 0) { 
				gui.showMessage(property.toString() + guiMessages[14]);
			}
			else if (property.getMortage()) {
				gui.showMessage(property.toString() + guiMessages[15]);
			}
			else {
				if(property.getColourSystem().equals("ship") || property.getColourSystem().equals("darkgreen")) {
					ownedUtilitiesSameType((Utility) property, player);
				}
				else {
					ownedRealEstateSameColour((RealEstate) property, player);
				}
			}
		}
	}
	
	public void payTax(Tax tax) {
		if(tax.getPrice() == 4000) {
			String playerChoice = gui.getUserSelection(game.getCurrentPlayer().getName()+ guiMessages[26] + tax.getPrice() + guiMessages[27] , guiMessages[28], guiMessages[29]);
			if(playerChoice.equals(Integer.toString(tax.getPrice()))) {
				payMoney(game.getCurrentPlayer(), tax.getPrice());

			} else {
				payMoney(game.getCurrentPlayer(), (int) (-game.getCurrentPlayer().getTotalValue()*0.1));
			}
		} else {
			payMoney(game.getCurrentPlayer(), tax.getPrice());
		}
	}
	

}
