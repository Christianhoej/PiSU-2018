package controller;

import java.awt.Color;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.Set;

import board.Gameboard;
import gui_main.GUI;
import model.CardPay;
import model.ChanceCard;
import model.Dice;
import model.Fields;
import model.Game;
import model.Player;
import model.Property;
import model.RealEstate;
import model.Txt;
import model.Utility;
import model.Tax;


public class GameController {

	private Game game;
	private GUI gui;
	private String[] guiMessages = Txt.fileString("GameMessages.txt");
	private int[][] rent = Txt.fileInt2D("RentPrices.txt");
	private View view;

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
			if(player.getName().equals("")){  // hvis der ikke er indtastet noget, sættes et standard spillernavn
				player.setName("Spiller " + (i+1));
			}
			for(int k=i-1; k>=0; k--) { //Loop der checker om 2 spillere hedder det samme
				while(game.getPlayers().get(k).getName().equals(player.getName())) {// tjekker om spillerne har samme navn

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
			player.setPosition(37); // SKAL LAVES OM TIL 0!!!!!!
			player.getAccount().setOwner(player);
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

		currentPlayer = game.getPlayers().indexOf(player);
		int currentBid=100;
		//New array with the player originally landing on the field, as the last. 

		Player[] auctionArray = new Player[game.getPlayers().size()];
		int j = 0;
		for(int i = currentPlayer+1; i<game.getPlayers().size();i++) {
			auctionArray[0+j++]= game.getPlayers().get(i);
		}
		for(int i=0; i<=currentPlayer; i++) {
			auctionArray[0+j++] = game.getPlayers().get(i);
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
					if(playersOut>=auctionArray.length-1) {
						break;
					}
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



	public void pawn(Player player) {
		//		En spiller skal kunne pantsætte sin ejendom, for at modtage lån af banken. 
		//		Renten er 10 % og betales samtidigt med tilbagebetalingen af lånet. 
		//		Pantsætningen ophæves efterfølgende.
		String choice = "";
		ArrayList<Fields> fields = game.getFields();

		ArrayList<Fields> propsWithoutHouses = new ArrayList<Fields>();
		ArrayList<Fields> propsWithHouses = new ArrayList<Fields>();



		for( int i = 0; i<fields.size(); i++) {
			//&& fields.
			if(fields.get(i).getOwner().equals(player) && (fields.get(i).getHouses()==0)) {
				propsWithoutHouses.add(fields.get(i));

			}
			if(fields.get(i).getOwner().equals(player) && (fields.get(i).getHouses()>0))
				propsWithHouses.add(fields.get(i));
		}

		for (int j = 0; j<propsWithoutHouses.size();j++) {

			for(int j1 = 0; j1<propsWithHouses.size();j1++) {

				if(propsWithoutHouses.get(j).getColourSystem().equals(propsWithHouses.get(j1).getColourSystem()))
					propsWithoutHouses.remove(j);
			}
		
		}
		String[] propUserSelection = new String[propsWithoutHouses.size()];
		for (int i = 0; i<propsWithoutHouses.size(); i++) {
			propUserSelection[i] = propsWithoutHouses.get(i).getFieldName();
		}
		
		choice = gui.getUserSelection("Vælg grund du gerne vil pantsætte:", propUserSelection);
		
		
		// Find

	}






	private void loadGame() {
		//Kald til databaser om at loade
	}

	public void runGame() {
		setUpGame();


		ArrayList<Player> players = game.getPlayers();
		Player currentPlayer = game.getCurrentPlayer();

		// Getting the index for the currentPlayer - useful when loading a game.
		int currentIndex= game.getPlayers().indexOf(currentPlayer);

		boolean noWinner=true;
		while(noWinner) {
			currentPlayer = game.getCurrentPlayer();
			if(!currentPlayer.isBroke()) {
				playerTurn(currentPlayer);
			}

			currentIndex = (currentIndex+1)%players.size();
			game.setCurrentPlayer(players.get(currentIndex));

		}


	}

	public void playerTurn(Player player) {
		boolean throwDouble=false;
		do {
			if(player.getInPrison()>0) {
				playerInPrison(player);
			}

			if(player.getInPrison()==0) {
				playerOption(game.getCurrentPlayer());
			}




			game.getDice().rollDice();
			int[] faceValue = game.getDice().getFaceValue();
			//			int[] faceValue = {2, 3};
			gui.setDice(faceValue[0], faceValue[1]);
			throwDouble = (game.getDice().isEqual(faceValue));
			int doubleCount=0;
			if(throwDouble && player.getInPrison()>0) {
				doubleCount++;
				player.setInPrison(0);
				gui.showMessage(player.getName() + ", du har kastet to ens, mens du var i fængsel, og kan "
						+ "forstætte ved at rykke " +(faceValue[0]+faceValue[1]) + "felter frem");
				if(doubleCount>2) {
					gui.showMessage(player.getName() + ", du har kastet to ens tre gange i træk, og skal derfor "
							+ "i fængsel");
					player.setInPrison(1);
					player.setPosition(10);

					return;
				}
			}
			else if(player.getInPrison()>0) {
				gui.showMessage(player.getName() + ", du kastede ikke to ens, og forbliver i fængsel en runde til");
				return;
			}
			int position = player.getPosition() + faceValue[0]+faceValue[1];
			moveToField(player, position);

			if(throwDouble) {
				gui.showMessage(player.getName() + ", du har kastet to ens og får et ekstra kast");
			}

		}while(throwDouble);
	}

	public void playerOption(Player player) {





	}

	public void moveToField(Player player, int position) {
		position = position % game.getFields().size();
		if(position<player.getPosition() && player.getInPrison()==0) {
			player.setPosition(position % game.getFields().size());
			gui.showMessage(player.getName() + ", du har passeret start, og modtager 4000 kr");
			player.getAccount().updateCash(4000);
		}
		else if (player.getInPrison()>0) {
			player.setPosition(position % game.getFields().size());
		}
		else {
			player.setPosition(position % game.getFields().size());
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
				choice = gui.getUserButtonPressed(player.getName() + ", du er i fængsel. Du har følgende"
						+ " muligheder for at komme ud af fængslet", "Betal 1000 kr",
						"Brug fængselskort", "Kast terningerne");
			}
			else {
				// the player can choose between using the get out of jail free
				// card and throw the dices.
				choice = gui.getUserButtonPressed(player.getName() + ", du er i fængsel. Du har følgende"
						+ " muligheder for at komme ud af fængslet", "Brug "
								+ "fængselskort", "Kast terningerne");
			}
		}
		// if the player has over 1000kr
		else if(player.getAccount().getCash()>=1000) {
			// the player can choose between paying 1000 kr to the bank and throw 
			// the dices.
			choice = gui.getUserButtonPressed(player.getName() + ", du er i fængsel. Du har følgende "
					+ "muligheder for at komme ud af fængslet", "Betal 1000 kr", 
					"Kast terningerne");
		}
		else {
			// the player has to throw the dices.
			choice = gui.getUserButtonPressed(player.getName() + ", du er i fængsel. Da du hverken har et"
					+ " fængselskort eller nok penge, må du kaste med terningerne", 
					"Kast terningerne");
		}

		switch(choice) {
		case "Betal 1000 kr": 	player.getAccount().updateCash(1000);
		player.setInPrison(0);						
		gui.showMessage(player.getName() + ", du har betalt 1000 kr og er kommet "
				+ "ud af fængslet. Fortsæt turen ved at kaste"
				+ " med terningerne");
		break;
		case "Brug fængselskort": player.getAccount().updatePrisonCard(-1);
		player.setInPrison(0);
		gui.showMessage(player.getName() + ", du har brugt et fængsels kort og er "
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
	public void playerBirthday(Player player) { // Overflødig metode - Bliver håndteret i model
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
					if(ownedRealEstateSameColour((RealEstate) property, player)) {
						paySameTypeRealEstate(property, player);
					} else { 
						gui.showMessage(player.getName() + ", du er landet på " + property.getOwner().getName() +"'s ejendom og skal betale " + rent[game.getFields().indexOf(property)][0] + " i leje");
						receiveMoney(player, property.getRent());
						payMoney(player, property.getRent());
					}
				}
			}
		}
	}

	public void paySameTypeUtility(Property property, Player player, int[] count) {
		// If there only exist two types of this utility, it is a "bryggeri"
		if (count[0] == 2) {
			switch(count[1]) {
			case 1: receiveMoney(property.getOwner(), property.getRent()/*gange med tærningeværdi*/); // PRIS FOR EN TYPE * øjenværdi --> HUSK DER SKAL ÆNDRES SÅ DER TÆLLES FOR TO TERNINGER
			payMoney(player, property.getRent()/*gange med tærningeværdi*/);
			gui.showMessage(guiMessages[38] + property.getOwner().getName() +guiMessages[39] + 1);
			break;
			case 2: receiveMoney(property.getOwner(),property.getRent()); // PRIS FOR TO TYPER
			payMoney(player, property.getRent()/*gange med tærningeværdi*/);

			gui.showMessage(guiMessages[40] + property.getOwner().getName() + guiMessages[41] + 2);
			break;
			}

		}
		// else it is a "rederi".
		else {
			switch(count[1]){
			case 1: receiveMoney(property.getOwner(), property.getRent()); // PRIS FOR EN TYPE
			payMoney(player, property.getRent());
			game.getCurrentPlayer().getAccount().updateCash(-1); //MINUSPRIS FOR EN TYPE
			gui.showMessage(guiMessages[42] + property.getOwner().getName() + guiMessages[43] + 1);
			break;
			case 2: receiveMoney(property.getOwner(), property.getRent()); // PRIS FOR TO TYPE
			payMoney(player, property.getRent());
			gui.showMessage(guiMessages[44] + property.getOwner().getName() + guiMessages[45] + 2);
			break;
			case 3: receiveMoney(property.getOwner(), property.getRent()); // PRIS FOR TRE TYPE
			payMoney(player, property.getRent());
			gui.showMessage(guiMessages[46] + property.getOwner().getName() + guiMessages[47] + 3);
			break;
			case 4: receiveMoney(property.getOwner(), property.getRent()); // PRIS FOR FIRE TYPE
			payMoney(player, property.getRent());
			gui.showMessage(guiMessages[48] + property.getOwner().getName() +guiMessages[49] + 4);
			break;
			} // skal opdateret fra txt filen af rederileje ^^VIGTIGT
		}

	}


	public void paySameTypeRealEstate(Property property, Player player) {
		switch(property.getHouses()){
		case 0: receiveMoney(property.getOwner(), property.getRent()*2); //PRIS FOR alle grunde
		payMoney(player, property.getRent()*2); //MINUSPRIS FOR alle grunde
		gui.showMessage(player.getName() + ", du er landet på " + property.getOwner().getName() +"'s ejendom og skal betale " + (rent[game.getFields().indexOf(property)][0]*2) + " da " + property.getOwner().getName() + " ejer alle felterne af denne type");
		break;
		case 1: receiveMoney(property.getOwner(), 1); //PRIS FOR ET HUS
		payMoney(player, 1); //MINUSPRIS FOR ET HUS
		gui.showMessage(player.getName() + ", du er landet på " + property.getOwner().getName() +"'s ejendom og skal betale " + rent[game.getFields().indexOf(property)][1]+ " da " + property.getOwner().getName() + " ejer alle felterne af denne type");
		break;
		case 2: receiveMoney(property.getOwner(), 2); // PRIS FOR TO HUSE
		payMoney(player, 2); // MINUS PRIS FOR TO HUSE
		gui.showMessage(player.getName() + ", du er landet på " + property.getOwner().getName() +"'s ejendom og skal betale " + rent[game.getFields().indexOf(property)][2]+ " da " + property.getOwner().getName() + " ejer alle felterne af denne type");
		break;
		case 3: receiveMoney(property.getOwner(), 3);;
		payMoney(player, 3);
		gui.showMessage(player.getName() + ", du er landet på " + property.getOwner().getName() +"'s ejendom og skal betale " + rent[game.getFields().indexOf(property)][3]+ " da " + property.getOwner().getName() + " ejer alle felterne af denne type");
		break;
		case 4: receiveMoney(property.getOwner(), 4);
		payMoney(player, 4);
		gui.showMessage(player.getName() + ", du er landet på " + property.getOwner().getName() +"'s ejendom og skal betale " + rent[game.getFields().indexOf(property)][4]+ " da " + property.getOwner().getName() + " ejer alle felterne af denne type");
		break;
		case 5: receiveMoney(property.getOwner(), 5); // FOR HOTEL
		payMoney(player, 5); // MINUS HOTEL
		gui.showMessage(player.getName() + ", du er landet på " + property.getOwner().getName() +"'s ejendom og skal betale " + rent[game.getFields().indexOf(property)][5]+ " da " + property.getOwner().getName() + " ejer alle felterne af denne type");
		break;
		}
	}


	/**
	 * Method, to find out, if an owner of a real estate, also owns
	 * the other real estates in the same color.
	 * 
	 * @param property the real estate the player has landed on
	 * @param player the player that has landed on the real estate
	 */
	public boolean ownedRealEstateSameColour(RealEstate realEstate, Player player) {
		int colourCount = 0;
		int ownerCount = 0;
		for (int i = 0; i<game.getFields().size(); i++) {
			if(game.getFields().get(i) instanceof RealEstate) {
				if (game.getFields().get(i).getColourSystem().equals(realEstate.getColourSystem())){
					colourCount++;
					if (game.getFields().get(i).getOwner() != null && game.getFields().get(i).getOwner().equals(realEstate.getOwner())){
						ownerCount++;
					}
				}
			}
		}
		return (colourCount == ownerCount);
	}

	/**
	 * Method, to find out, if an owner of an utility, also owns
	 * one or many other real estates in the same color.
	 * 
	 * @param property the real estate the player has landed on
	 * @param player the player that has landed on the real estate
	 */
	public int[] ownedUtilitiesSameType(Utility utility, Player player) {
		int colourCount = 0;
		int ownerCount = 0;
		for (int i = 0; i<game.getFields().size(); i++) {
			if(game.getFields().get(i) instanceof Utility) {
				if (game.getFields().get(i).getColourSystem().equals(utility.getColourSystem())){
					colourCount++;
					if (game.getFields().get(i).getOwner() != null && game.getFields().get(i).getOwner().equals(utility.getOwner())){
						ownerCount++;
					}
				}
			}
		}
		int[] count = {colourCount, ownerCount}; 
		return count;
	}
	public Game getGame() {
		return game;
	}
	/**
	 * Method which is invoked if a player do not have the funds to pay what set person owes.
	 * It enables you to trade with , pawn properties and sell houses/hotels
	 * @param payingPlayer: the player who needs to generate cash to pay
	 * @param ammount: ammount to pay
	 * 
	 * @return returns the full ammount if player is able to raise funds or returns a lesser ammount if the player cannot raise cash and goes bankrupt.
	 */
	public int generateCash(Player player, int ammount) {
		String choice = "";
		boolean done = false;
		while (!done) {

			gui.getUserButtonPressed(game.getCurrentPlayer() + ", hvad vil du nu?", "Afslut og gem spil", 
					"Pantsæt grund", "Køb hus", "Sælg hus", "Byt med medspiller", "Kast terningerne");
			String[] option;

			if(!game.getDice().isRolled() && player.getOwnedProperties() == null) {
				option = new String[]{"Kast terningerne"};
			}
			else if(!game.getDice().isRolled() && player.getOwnedProperties() != null) {
				boolean owned = false;
				for(int i = 0; i<player.getOwnedHouses().length; i++) {
					if(player.getOwnedHouses()[i]>0) {
						owned = true;
						break;
					}
				}
				if(owned) {
					option = new String[] {"Pantsæt grunde", "Kast terningerne"};
				}
			}



			else if(player.getAccount().getCash()<ammount) {
				choice = gui.getUserButtonPressed("Du har ikke nok penge til at betale dit udestående. Hvordan vil du håndtere dette:", "Sælg huse/hoteller", "Pantsæt ejendomme", "Byt med medspiller", "Erklær dig konkurs");
			}
			else {
				choice = gui.getUserButtonPressed("Du har ikke nok penge til at betale dit udestående. Hvordan vil du håndtere dette:", "Sælg huse/hoteller", "Pantsæt ejendomme", "Byt med medspiller", "Afslut og betal");
			}


			switch(choice) {
			case "Sælg huse/hoteller": 
				sellHousesAndHotels(player, ammount);




				break;
			case "Pantsæt ejendomme":

				break;
			case "Byt med medspiller":

				break;
			case "Erklær dig konkurs":

				done= true;

				break;
			case "Afslut og betal":

				done=true;
				break;
			}


		}
		if(player.getAccount().getCash()>= ammount)
			return ammount;
		else
		{

			int allPlayerHas = player.getAccount().getCash() + pawn(player);
			//player goes bankrupt()
			return allPlayerHas;
		}

	}
	public void sellHousesAndHotels(Player payingPlayer, int ammount) {
		String choice = "";


		boolean done = false;
		while(!done) {
			//initial array with a players owned houses
			int[] propsWithHouses = payingPlayer.getOwnedHouses();
			//Saves the color system (buddyfields reference)
			Set<String> colorSystem = new HashSet<String>();

			ArrayList<Fields> fieldsWithHouses = new ArrayList<Fields>();

			for(int i = 0 ; i<propsWithHouses.length; i++) {

				if(propsWithHouses[i]>1) { // if something is build on property - add name to houseToSell and Field to fieldsWithHouses
					colorSystem.add(game.getFields().get(i).getColourSystem());
					fieldsWithHouses.add(game.getFields().get(i));
				}	
			}

			//List og set skal konverteres for at kunne gives som 
			//Array der skal anvendes til userSelection
			String[] displayColorSystem = new String[colorSystem.size()];
			displayColorSystem = colorSystem.toArray(displayColorSystem);

			//Array med Fields
			Fields[] fieldArray = new Fields[fieldsWithHouses.size()];
			fieldsWithHouses.toArray(fieldArray);


			//Evt start loop her der holder spilleren i ejendomsfarven
			//Spiller vælger ejendomsfarve
			choice = gui.getUserSelection("Vælg ejendomsfarve du vil sælge bygning på:", displayColorSystem);

			ArrayList<String> sameTypePropertiesNames = new ArrayList<String>();
			ArrayList<Fields> sameTypePropertiesFields = new ArrayList<Fields>();
			for (int j = 0; j<fieldArray.length; j++) {

				if(choice.equals(fieldArray[j].getColourSystem())) {
					sameTypePropertiesNames.add(fieldArray[j].getFieldName());
					sameTypePropertiesFields.add(fieldArray[j]);
				}


			}
			// converts to array. Array contains names of properties of a type with houses on it
			String[] availableBuildings = new String[sameTypePropertiesNames.size()];
			availableBuildings = sameTypePropertiesNames.toArray(availableBuildings);


			boolean ableToSell = false;

			while(!ableToSell) {

				choice = gui.getUserButtonPressed("Hvilken grund vil du sælge hus på?", availableBuildings);
				int h1=0;
				double h2=0;


				for(int h = 0; h< availableBuildings.length; h++) {
					h2+=sameTypePropertiesFields.get(h).getHouses();//lægger det totale antal huse i en bestemt farve sammen. Disse kan divideres med antallet af huse som er valgt af brugeren.
					if(choice.equals(availableBuildings[h])) {
						h1 = sameTypePropertiesFields.get(h).getHouses();
					}
				}
				if (h1 >= (h2/sameTypePropertiesFields.size()) && (propsWithHouses[h1] >0)) {
					//remove house on field(nr)
					payingPlayer.removeHouses(sameTypePropertiesFields.get(h1).getFieldNumber());
					//remove houses from the array the used to sell houses from:
					sameTypePropertiesFields.get(h1).sellHouse();
					//remove house on players houseArray()
					game.getFields().get(sameTypePropertiesFields.get(h1).getFieldNumber()).sellHouse();
					// remove getHouseBuildingPrice from players account (assets)
					payingPlayer.getAccount().updateAssetValue(-(game.getFields().get(sameTypePropertiesFields.get(h1).getFieldNumber()).getBuildingPrice()));
					//return half of HouseBuildingPrice to Players cash in account.
					payingPlayer.getAccount().updateCash((game.getFields().get(sameTypePropertiesFields.get(h1).getFieldNumber()).getBuildingPrice())/2);

					ableToSell = true;

				}
				else {
					gui.showMessage("Du kan enten ikke sælge flere huse i denne farve eller ikke sælge hus på denne grund før du har solgt på anden grund. \n Huse og hoteller på ejendomme skal fordeles jævnt.");
				}
				//Buttons to either continue or finish selling
				choice = gui.getUserButtonPressed("Vil du afslutte eller forsætte med at sælge huse/hoteller?", "Fortsæt med at sælge i farven","Fortsæt med anden farve" ,"afslut");

				switch(choice) {
				case "Fortsæt med at sælge i farven" :

					break;
				case "Fortsæt med anden farve" :
					ableToSell = true;
					break;
				case "afslut":
					ableToSell = true;
					done = true;
					break;
				}
			}
		}

	}

	public void payTax(Tax tax) {
		if(tax.getPrice() == 4000) {
			String playerChoice = gui.getUserSelection(game.getCurrentPlayer().getName()+ tax.toString() + "?", "4000", "10%");
			if(playerChoice.equals(Integer.toString(tax.getPrice()))) {
				payMoney(game.getCurrentPlayer(), tax.getPrice());

			} else {
				payMoney(game.getCurrentPlayer(), (int) (-game.getCurrentPlayer().getTotalValue()*0.1));
			}
		} else {
			payMoney(game.getCurrentPlayer(), tax.getPrice());
		}
	}

	public void neutralField(Fields field) {
		gui.showMessage(game.getCurrentPlayer().getName() + field.toString());
	}


	public void cardMoveToField(ChanceCard chanceCard) {
		gui.displayChanceCard(chanceCard.toString());
		if(chanceCard.getCardNumber()<=21) {
			//nearest ferry + double rent
			moveToFerryDouble();
		}
		else if(chanceCard.getCardNumber()<=23) {
			//Move -3
			moveToField(game.getCurrentPlayer(), game.getCurrentPlayer().getPosition()-3);
			//			getGame().getCurrentPlayer().setPosition(getGame().getCurrentPlayer().getPosition()-3);
			//			//land on field
			//			getGame().getFields().get(getGame().getCurrentPlayer().getPosition()).landOnField(this);;
		}
		else if(chanceCard.getCardNumber()==24) {
			//Start
			//			getGame().getCurrentPlayer().setPosition(0);//1 alt efter hvordan position kalkuleres
			moveToField(game.getCurrentPlayer(), 0);
			//landOnField
			//			getGame().getFields().get(0).landOnField(this);
		}
		else if(chanceCard.getCardNumber() == 25) {
			//rådhuspladsen
			//			getGame().getCurrentPlayer().setPosition(39);//40 alt efter hvordan position kalkuleres
			moveToField(game.getCurrentPlayer(), 39);
			// landOnField
			//			getGame().getFields().get(39).landOnField(this);

		}
		else if(chanceCard.getCardNumber()==26) {
			//molslinje
			//			getGame().getCurrentPlayer().setPosition(25);//26 alt efter hvordan position kalkuleres
			moveToField(game.getCurrentPlayer(), 15);
			// landOnField
			//			getGame().getFields().get(25).landOnField(this);

		}
		else if (chanceCard.getCardNumber() == 27) {
			//Move to Grønningen
			//			getGame().getCurrentPlayer().setPosition(24);//25 alt efter hvordan position kalkuleres
			moveToField(game.getCurrentPlayer(), 24);
			//landOnField
			//			getGame().getFields().get(24).landOnField(this);

		}
		else {
			//Move to Frederiksberg Allé
			//			getGame().getCurrentPlayer().setPosition(11);//12 alt efter hvordan position kalkuleres
			moveToField(game.getCurrentPlayer(), 11);
			//landOnField
			//			getGame().getFields().get(11).landOnField(this);
		}

	}

	private void moveToFerryDouble() {
		int position = game.getCurrentPlayer().getPosition();

		int arrayPositionOfFerry = 0;

		if ((position)<6 || (position)>=36) {
			//			int oldPosition = position;
			//			moveToField(game.getCurrentPlayer(), 5);
			//			game.getCurrentPlayer().setPosition(5); // Obs skal lige høre hvordan position skal gemmes/ dvs. enten skal den være 5 eller 6 når der flyttes.
			arrayPositionOfFerry = 5;
			//If player passes start
			//			if ((oldPosition)>(position))
			//				game.getCurrentPlayer().getAccount().updateCash(4000);
		}
		else if (position < 16) {
			//			moveToField(game.getCurrentPlayer(), 15);
			//			game.getCurrentPlayer().setPosition(15);
			arrayPositionOfFerry = 15;
		}
		else if (position < 26) { 
			//			moveToField(game.getCurrentPlayer(), 25);
			//			game.getCurrentPlayer().setPosition(25);
			arrayPositionOfFerry = 25;
		}
		else if ((position) < 36) {

			//			moveToField(game.getCurrentPlayer(), 35);
			arrayPositionOfFerry = 35;
		}

		if(game.getFields().get(arrayPositionOfFerry).getOwner() !=null ) {//.equals or == null test
			moveToField(game.getCurrentPlayer(), arrayPositionOfFerry);
			//			ownedUtilitiesSameType((Utility) game.getFields().get(arrayPositionOfFerry), game.getCurrentPlayer());
			ownedUtilitiesSameType((Utility) game.getFields().get(arrayPositionOfFerry), game.getCurrentPlayer());
		}

		else {
			moveToField(game.getCurrentPlayer(), arrayPositionOfFerry);
		}
	}

	public void playerGetsPrisonCard(ChanceCard chanceCard) {
		gui.displayChanceCard(chanceCard.toString());
		game.getCurrentPlayer().getAccount().updatePrisonCard(1);
	}

	public void cardGoToJail(ChanceCard chanceCard) {
		gui.displayChanceCard(chanceCard.toString());
		game.getCurrentPlayer().setInPrison(1);		
		game.getCurrentPlayer().setPosition(10);
	}

	public void cardPay(ChanceCard chanceCard) {
		gui.displayChanceCard(chanceCard.toString());
		if(chanceCard.getCardNumber()==18) { //kortet betaler per hus og hotel
			int housePrice = 800;
			int hotelPrice = 2300;

			game.getCurrentPlayer().getAccount().updateCash(payPerHouseAndHotel(housePrice,hotelPrice));

		}
		else if(chanceCard.getCardNumber()==19) { //kortet betaler per hus og hotel
			int housePrice = 500;
			int hotelPrice = 2000;

			game.getCurrentPlayer().getAccount().updateCash(payPerHouseAndHotel(housePrice,hotelPrice));
		}

		else
			game.getCurrentPlayer().getAccount().updateCash(chanceCard.getAmount());

	}

	private int payPerHouseAndHotel(int housePrice, int hotelPrice) {
		int houses=0;
		int hotels=0;
		int[]array = game.getCurrentPlayer().getOwnedHouses();
		for (int i = 0; i< array.length; i++) {//antal huse og hoteller findes
			if (array[i]==5)
				hotels++;
			else
				houses+= array[i];
		}
		return -(houses*housePrice + hotels*hotelPrice);
	}

	public void cardReceiveMoney(ChanceCard chanceCard) {
		gui.displayChanceCard(chanceCard.toString());
		if (chanceCard.getCardNumber() == 1) { //Fødselsdag - Modtag 200 fra hver spiller.
			game.getCurrentPlayer().getAccount().updateCash(game.getPlayers().size()*chanceCard.getAmount()+chanceCard.getAmount()); //all players are deducted 200, therefore the player to receive gets the extra "amount" which are then deducted in the loop below
			for(int i = 0; i<(game.getPlayers().size()); i++) {
				int ammountPaid = 0;
				if(game.getPlayers().get(i).getAccount().getCash()>=200)
					game.getPlayers().get(i).getAccount().updateCash(-chanceCard.getAmount());

				else 
					ammountPaid = generateCash(game.getPlayers().get(i), 200); //MANGLER NOGET
			}
		} 
		else if(chanceCard.getCardNumber() == 10) { // Matador legatet: assetValue<15.000 Modtager 40.000
			//Hensigten med assets/cash Det antages at cash/assets opdateres hver for sig og at assets også opdateres når der købes huse/hoteller.
			int totValue = game.getCurrentPlayer().getTotalValue(); // Gets cash and assets
			if (totValue < 15000) {
				game.getCurrentPlayer().getAccount().updateCash(chanceCard.getAmount());
			}

		}
		else {
			game.getCurrentPlayer().getAccount().updateCash(chanceCard.getAmount());
		}
	}
}


