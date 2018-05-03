package controller;

import java.awt.Color;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.Set;



import board.Gameboard;
import gui_main.GUI;
import model.ChanceCard;
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



	public int pawn(Player player, boolean pawnAll) {

		return 0;

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
		//		for(int i = 0; i<players.size(); i++) {
		//			if(currentPlayer.equals(players.get(i))) {
		//				currentIndex = i;
		//				break;
		//			}
		//		}
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

			Dice dice = new Dice();
			dice.rollDice();
			//			int[] faceValue = dice.getFaceValue();
			int[] faceValue = {2, 3};
			gui.setDice(faceValue[0], faceValue[1]);
			throwDouble = (faceValue[0]== faceValue[1]);
			int doubleCount=0;
			if(throwDouble && player.getInPrison()>0) {
				doubleCount++;
				player.setInPrison(0);
				gui.showMessage(player.getName() + ", du har kastet to ens, mens du var i fængsel, og kan "
						+ "forstætte ved at rykke " +(faceValue[0]+faceValue[1]) + "felter frem");
				if(doubleCount>2) {
					gui.showMessage(player.getName() + ", du har kastet to ens tre gange i træk, og skal derfor "
							+ "i fængsel");
					player.setPosition(10);
					player.setInPrison(1);
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

	public void moveToField(Player player, int position) {
		//		player.setPosition(30);
		player.setPosition(position % game.getFields().size());
		if(position>player.getPosition() && player.getInPrison()==0) {
			gui.showMessage(player.getName() + ", du har passeret start, og modtager 4000 kr");
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
			if(game.getFields().get(i) instanceof RealEstate) {
				if (game.getFields().get(i).getColourSystem().equals(realEstate.getColourSystem())){
					colourCount++;
					if (game.getFields().get(i).getOwner() != null && game.getFields().get(i).getOwner().equals(realEstate.getOwner())){
						ownerCount++;
					}
				}
			}
		}
		if(colourCount == ownerCount) {

			switch(realEstate.getHouses()){
			case 0: receiveMoney(realEstate.getOwner(), realEstate.getRent()*2); //PRIS FOR alle grunde
			payMoney(player, realEstate.getRent()*2); //MINUSPRIS FOR alle grunde
			gui.showMessage(player.getName() + ", du er landet på " + realEstate.getOwner().getName() +"'s ejendom og skal betale " + (rent[game.getFields().indexOf(realEstate)][0]*2) + " da " + realEstate.getOwner().getName() + " ejer alle felterne af denne type");
			break;
			case 1: receiveMoney(realEstate.getOwner(), 1); //PRIS FOR ET HUS
			payMoney(player, 1); //MINUSPRIS FOR ET HUS
			gui.showMessage(player.getName() + ", du er landet på " + realEstate.getOwner().getName() +"'s ejendom og skal betale " + rent[game.getFields().indexOf(realEstate)][1]+ " da " + realEstate.getOwner().getName() + " ejer alle felterne af denne type");
			break;
			case 2: receiveMoney(realEstate.getOwner(), 2); // PRIS FOR TO HUSE
			payMoney(player, 2); // MINUS PRIS FOR TO HUSE
			gui.showMessage(player.getName() + ", du er landet på " + realEstate.getOwner().getName() +"'s ejendom og skal betale " + rent[game.getFields().indexOf(realEstate)][2]+ " da " + realEstate.getOwner().getName() + " ejer alle felterne af denne type");
			break;
			case 3: receiveMoney(realEstate.getOwner(), 3);;
			payMoney(player, 3);
			gui.showMessage(player.getName() + ", du er landet på " + realEstate.getOwner().getName() +"'s ejendom og skal betale " + rent[game.getFields().indexOf(realEstate)][3]+ " da " + realEstate.getOwner().getName() + " ejer alle felterne af denne type");
			break;
			case 4: receiveMoney(realEstate.getOwner(), 4);
			payMoney(player, 4);
			gui.showMessage(player.getName() + ", du er landet på " + realEstate.getOwner().getName() +"'s ejendom og skal betale " + rent[game.getFields().indexOf(realEstate)][4]+ " da " + realEstate.getOwner().getName() + " ejer alle felterne af denne type");
			break;
			case 5: receiveMoney(realEstate.getOwner(), 5); // FOR HOTEL
			payMoney(player, 5); // MINUS HOTEL
			gui.showMessage(player.getName() + ", du er landet på " + realEstate.getOwner().getName() +"'s ejendom og skal betale " + rent[game.getFields().indexOf(realEstate)][5]+ " da " + realEstate.getOwner().getName() + " ejer alle felterne af denne type");
			break;
			}
		} else { 
			gui.showMessage(player.getName() + ", du er landet på " + realEstate.getOwner().getName() +"'s ejendom og skal betale " + rent[game.getFields().indexOf(realEstate)][0] + " i leje");
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
			if(game.getFields().get(i) instanceof Utility) {
				if (game.getFields().get(i).getColourSystem().equals(utility.getColourSystem())){
					colourCount++;
					if (game.getFields().get(i).getOwner() != null && game.getFields().get(i).getOwner().equals(utility.getOwner())){
						ownerCount++;
					}
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
	/**
	 * Method which is invoked if a player do not have the funds to pay what set person owes.
	 * It enables you to trade with , pawn properties and sell houses/hotels
	 * @param payingPlayer: the player who needs to generate cash to pay
	 * @param ammount: ammount to pay
	 * 
	 * @return returns the full ammount if player is able to raise funds or returns a lesser ammount if the player cannot raise cash and goes bankrupt.
	 */
	public int generateCash(Player payingPlayer, int ammount) {
		String choice = "";
		boolean done = false;
		boolean pawnAll = false;
		while (!done) {

			if(payingPlayer.getAccount().getCash()<ammount) 
				choice = gui.getUserButtonPressed("Du har ikke nok penge til at betale dit udestående. Hvordan vil du håndtere dette:", "Sælg huse/hoteller", "Pantsæt ejendomme", "Byt med medspiller", "Erklær dig konkurs");
			else
				choice = gui.getUserButtonPressed("Du har ikke nok penge til at betale dit udestående. Hvordan vil du håndtere dette:", "Sælg huse/hoteller", "Pantsæt ejendomme", "Byt med medspiller", "Afslut og betal");



			switch(choice) {
			case "Sælg huse/hoteller": 
				payingPlayer.addHouses(0);
				
				//array with a players owned houses
				int[] propsWithHouses = payingPlayer.getOwnedHouses();

				//Saves the color system (buddyfields reference)
				Set<String> colorSystem = new HashSet<String>();

				ArrayList<Fields> fieldsWithHouses = new ArrayList<Fields>();

				for(int i = 0 ; i<propsWithHouses.length; i++) {

					if(propsWithHouses[i]>1) { // if something is build on property - add name to houseToSell
						//				houseToSell.add(game.getFields().get(i).getFieldName());
						colorSystem.add(game.getFields().get(i).getColourSystem());
						fieldsWithHouses.add(game.getFields().get(i));

					}	
					//Konverterer ArrayList til Array[]
					//					String[] displayedPropsWithHouses = new String[houseToSell.size()]; 
					//					displayedPropsWithHouses = houseToSell.toArray(displayedPropsWithHouses);

					//Array der skal anvendes til userSelection
					String[] displayColorSystem = new String[colorSystem.size()];
					displayColorSystem = colorSystem.toArray(displayColorSystem);

					//Array med Fields
					Fields[] fieldArray = new Fields[fieldsWithHouses.size()];
					fieldsWithHouses.toArray(fieldArray);



					//Spiller vælger ejendomsfarve
					String sellPropsChoice = gui.getUserSelection("Vælg ejendomsfarve du vil sælge bygning på:", displayColorSystem);

					ArrayList<String> sameTypeProperties = new ArrayList<String>();
					for (int j = 0; j<fieldArray.length; j++) {

						if(sellPropsChoice.equals(fieldArray[j].getColourSystem())) {
							sameTypeProperties.add(fieldArray[j].getFieldName());
						}


					}
					String[] availableBuildings = new String[sameTypeProperties.size()];
					availableBuildings = sameTypeProperties.toArray(availableBuildings);
					
					boolean ableToSell = false;
					while(!ableToSell) {
					String removeBuilding = gui.getUserButtonPressed("Hvilken grund vil du sælge hus på?", availableBuildings);
						//availableBuildings is 
						//
					
					}
					
				}

				payingPlayer.removeHouses(0);
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
		if(payingPlayer.getAccount().getCash()>= ammount)
			return ammount;
		else
		{

			int allPlayerHas = payingPlayer.getAccount().getCash() + pawn(payingPlayer, pawnAll);
			//player goes bankrupt()
			return allPlayerHas;
		}

	}
	//	public void playerIsBankrupt(Player bankruptPlayer, Player receivingPlayer) {
	//		
	//	}


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
		gui.showMessage(chanceCard.toString());
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
			moveToField(game.getCurrentPlayer(), 25);
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
		game.getCurrentPlayer();
//		int arrayPositionOfFerry = 0;

		if ((position)<6 || (position)>=36) {
//			int oldPosition = position;
			moveToField(game.getCurrentPlayer(), 5);
			//			game.getCurrentPlayer().setPosition(5); // Obs skal lige høre hvordan position skal gemmes/ dvs. enten skal den være 5 eller 6 når der flyttes.
//			arrayPositionOfFerry = 5;
			//If player passes start
			//			if ((oldPosition)>(position))
			//				game.getCurrentPlayer().getAccount().updateCash(4000);
		}
		else if (position < 16) {
			moveToField(game.getCurrentPlayer(), 15);
			//			game.getCurrentPlayer().setPosition(15);
//			arrayPositionOfFerry = 15;
		}
		else if (position < 26) { 
			moveToField(game.getCurrentPlayer(), 25);
			//			game.getCurrentPlayer().setPosition(25);
//			arrayPositionOfFerry = 25;
		}
		else if ((position) < 36) {

			moveToField(game.getCurrentPlayer(), 35);
//			arrayPositionOfFerry = 35;
		}

		//		if(!game.getFields().get(arrayPositionOfFerry).getOwner().equals(null)) {//.equals or == null test
		//		gameController.ownedUtilitiesSameType((Utility) game.getFields().get(arrayPositionOfFerry), player);
		//		gameController.ownedUtilitiesSameType((Utility) game.getFields().get(arrayPositionOfFerry), player);
		//		}

		//		else {
		//			game.getFields().get(arrayPositionOfFerry).landOnField(gameController);
		//	}



	}


}


