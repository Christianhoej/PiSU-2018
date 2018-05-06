package controller;

import java.awt.Color;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.Set;

import board.Gameboard;
import gui_main.GUI;
import model.ChanceCard;
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
	private int[][] rent = Txt.fileInt2D("RentPrices.txt");
	private int[][] housePrice = Txt.fileInt2D("BuildingPrices.txt");
	private View view;

	public GameController(Game game) {
		this.game = game;
		Gameboard gameboard = new Gameboard();
		gui = new GUI(gameboard.makeBoard());
	}




	public void createPlayers() {

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
				while(game.getPlayers().get(k).getName().equals(player.getName())) {// checker om spillerne har samme navn

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
		ArrayList<String> tradePlayers = new ArrayList<String>();

		for(int i=0; i<game.getPlayers().size(); i++) {
			if(!player.equals(game.getPlayers().get(i))) {
				tradePlayers.add(game.getPlayers().get(i).getName());
			}
		}
		tradePlayers.add("Tilbage");
		String[] tradingPlayers = new String[tradePlayers.size()];
		tradingPlayers = tradePlayers.toArray(tradingPlayers); 


		String tradeName = gui.getUserButtonPressed(player.getName() + ", hvilken spiller vil du bytte med?", tradingPlayers);

		if(!tradeName.equals("Tilbage")) {
			Player tradingPlayer = null;
			for(int i=0; i<game.getPlayers().size(); i++) {
				if(tradeName.equals(game.getPlayers().get(i).getName())) {
					tradingPlayer = game.getPlayers().get(i);
					break;
				}
			}

			gui.showMessage(player.getName() + " vil gerne handle med " + tradingPlayer.getName());

			ArrayList<String> currentOwnedProp = new ArrayList<String>();
			ArrayList<String> currentOwnedMortgageProp = new ArrayList<String>();
			ArrayList<String> currentOptions = new ArrayList<String>();

			if(player.getOwnedProperties().size()>0) {
				for(int i=0; i<player.getOwnedProperties().size(); i++) {
					if(player.getOwnedProperties().get(i).getMortgage()) {
						currentOwnedMortgageProp.add(player.getOwnedProperties().get(i).getFieldName());
					}
					else {
						currentOwnedProp.add(player.getOwnedProperties().get(i).getFieldName());
					}
				}
			}

			if(currentOwnedMortgageProp.size()>0) {
				currentOptions.add("Pantsatte grunde");
				currentOwnedMortgageProp.add("Annuller");
			}
			if(currentOwnedProp.size()>0) {
				currentOptions.add("Grunde");
				currentOwnedProp.add("Annuller");
			}
			if(player.getAccount().getPrisonCard()>0) {
				currentOptions.add("Ud af fængsel lykke kort");
			}
			if(player.getAccount().getCash()>0) {
				currentOptions.add("Penge");
			}
			currentOptions.add("Annuller");

			String[] currentPlayerOwnedProp = new String[currentOwnedProp.size()];
			currentPlayerOwnedProp = currentOwnedProp.toArray(currentPlayerOwnedProp); 

			String[] currentPlayerOwnedMort = new String[currentOwnedMortgageProp.size()];
			currentPlayerOwnedMort = currentOwnedMortgageProp.toArray(currentPlayerOwnedMort);

			String[] currentPlayerOptions = new String[currentOptions.size()];
			currentPlayerOptions = currentOptions.toArray(currentPlayerOptions);

			String currentChoice = gui.getUserButtonPressed(player.getName() + ", hvad vil du bytte? ", currentPlayerOptions);

			String currentChoice1="";
			switch(currentChoice) {
			case "Grunde": 
				currentChoice1 = gui.getUserSelection(player.getName() + ", hvilken grund vil du bytte ", currentPlayerOwnedProp);
				if(!currentChoice1.equals("Annuller")) {
					boolean trade = tradePlayerOption(player, tradingPlayer, currentChoice1);
					if(trade) {
						for(int i = 0; i<player.getOwnedProperties().size(); i++) {
							if(currentChoice1.equals(player.getOwnedProperties().get(i).getFieldName())) {
								player.getOwnedProperties().get(i).setOwner(tradingPlayer);
								tradingPlayer.addOwnedProperties(player.getOwnedProperties().get(i));
								player.removeOwnedProperties(player.getOwnedProperties().get(i));
							}
						}
					}
					break;
				}
				else {
					return;
				}
			case "Pantsatte grunde":
				currentChoice1 = gui.getUserSelection(player.getName() + ", hvilken pantsat grund vil du bytte ", currentPlayerOwnedMort);
				if(!currentChoice1.equals("Annuller")) {
					boolean trade = tradePlayerOption(player, tradingPlayer, currentChoice1);
					if(trade) {
						for(int i = 0; i<player.getOwnedProperties().size(); i++) {
							if(currentChoice1.equals(player.getOwnedProperties().get(i).getFieldName())) {
								player.getOwnedProperties().get(i).setOwner(tradingPlayer);
								tradingPlayer.addOwnedProperties(player.getOwnedProperties().get(i));
								player.removeOwnedProperties(player.getOwnedProperties().get(i));

							}
						}
					}
					break;
				}
				else {
					return;
				}
			case "Penge":
				int money = gui.getUserInteger(player.getName() + ", hvor mange penge vil du bytte? Tryk 0 for at annullere", 0, player.getAccount().getCash());
				if(money!=0) {
					boolean trade = tradePlayerOption(player, tradingPlayer, Integer.toString(money));
					if(trade) {
						payMoneyToPlayer(player, money, tradingPlayer);

					}
					break;
				}
				else {
					return;
				}
			case "Ud af fængsel lykke kort":
				boolean trade = tradePlayerOption(player, tradingPlayer, "Ud af fængsel lykke kort");
				if(trade) {
					player.getAccount().updatePrisonCard(-1);
					tradingPlayer.getAccount().updatePrisonCard(1);

				}
				break;
			case "Annuller":
				return;
			}
		}
	}

	public boolean tradePlayerOption(Player currentPlayer, Player tradingPlayer, String currentCoice) { 
		ArrayList<String> tradeOwnedProp = new ArrayList<String>();
		ArrayList<String> tradeOwnedMortgageProp = new ArrayList<String>();
		ArrayList<String> tradeOptions = new ArrayList<String>();

		if(tradingPlayer.getOwnedProperties().size()>0) {
			for(int i=0; i<tradingPlayer.getOwnedProperties().size(); i++) {
				if(tradingPlayer.getOwnedProperties().get(i).getMortgage()) {
					tradeOwnedMortgageProp.add(tradingPlayer.getOwnedProperties().get(i).getFieldName());
				}
				else {
					tradeOwnedProp.add(tradingPlayer.getOwnedProperties().get(i).getFieldName());
				}
			}
		}


		if(tradeOwnedMortgageProp.size()>0) {
			tradeOptions.add("Pantsatte grunde");
			tradeOwnedMortgageProp.add("Annuller");
		}
		if(tradeOwnedProp.size()>0) {
			tradeOptions.add("Grunde");
			tradeOwnedProp.add("Annuller");
		}
		if(tradingPlayer.getAccount().getPrisonCard()>0) {
			tradeOptions.add("Ud af fængsel lykke kort");
		}
		if(tradingPlayer.getAccount().getCash()>0) {
			tradeOptions.add("Penge");
		}
		tradeOptions.add("Annuller");

		String[] tradePlayerOwnedProp = new String[tradeOwnedProp.size()];
		tradePlayerOwnedProp = tradeOwnedProp.toArray(tradePlayerOwnedProp); 

		String[] tradePlayerOwnedMort = new String[tradeOwnedMortgageProp.size()];
		tradePlayerOwnedMort = tradeOwnedMortgageProp.toArray(tradePlayerOwnedMort);

		String[] tradePlayerOptions = new String[tradeOptions.size()];
		tradePlayerOptions = tradeOptions.toArray(tradePlayerOptions);

		String tradeChoice = gui.getUserButtonPressed(tradingPlayer.getName() + ", hvad vil du bytte? ", tradePlayerOptions);
		String tradeChoice1="";
		if(!tradeChoice.equals("Annuller")) {
			switch(tradeChoice) {
			case "Grunde": 
				tradeChoice1 = gui.getUserSelection(tradingPlayer.getName() + ", hvilken grund vil du bytte for " + currentCoice+"?", tradePlayerOwnedProp);
				if(!tradeChoice.equals("Annuller")) {
					gui.showMessage(currentPlayer.getName() + " har valgt at bytte " + currentCoice + " med " + tradeChoice1 + " fra " + tradingPlayer.getName());
					for(int i = 0; i<tradingPlayer.getOwnedProperties().size(); i++) {
						if(tradeChoice1.equals(tradingPlayer.getOwnedProperties().get(i).getFieldName())) {
							tradingPlayer.getOwnedProperties().get(i).setOwner(currentPlayer);
							currentPlayer.addOwnedProperties(tradingPlayer.getOwnedProperties().get(i));
							tradingPlayer.removeOwnedProperties(tradingPlayer.getOwnedProperties().get(i));
						}
					}
					break;
				}
				else return false;
			case "Pantsatte grunde":
				tradeChoice1 = gui.getUserSelection(tradingPlayer.getName() + ", hvilken pantsat grund vil du bytte for "+ currentCoice+"?", tradePlayerOwnedMort);
				if(!tradeChoice.equals("Annuller")) {
					gui.showMessage(currentPlayer.getName() + " har valgt at bytte " + currentCoice + " med " + tradeChoice1 + " fra " + tradingPlayer.getName());
					for(int i = 0; i<tradingPlayer.getOwnedProperties().size(); i++) {
						if(tradeChoice1.equals(tradingPlayer.getOwnedProperties().get(i).getFieldName())) {
							tradingPlayer.getOwnedProperties().get(i).setOwner(currentPlayer);
							currentPlayer.addOwnedProperties(tradingPlayer.getOwnedProperties().get(i));
							tradingPlayer.removeOwnedProperties(tradingPlayer.getOwnedProperties().get(i));
						}
					}
					break;
				}
				else return false;
			case "Penge":
				int tradeMoney = gui.getUserInteger(tradingPlayer.getName() + ", hvor mange penge vil du bytte for " + currentCoice+"? Tryk 0 for Annuller", 0, tradingPlayer.getAccount().getCash());
				if(tradeMoney!=0) {


					gui.showMessage(currentPlayer.getName() + " har valgt at bytte " + currentCoice + " med " + tradeMoney + " fra " + tradingPlayer.getName());
					payMoneyToPlayer(tradingPlayer, tradeMoney, currentPlayer);
					break;
				}
				else return false;
			case "Ud af fængsel lykke kort":
				gui.showMessage(currentPlayer.getName() + " har valgt at bytte " + currentCoice + " med et et \"Ud af fængsel lykke kort\" fra " + tradingPlayer.getName());
				tradingPlayer.getAccount().updatePrisonCard(-1);
				currentPlayer.getAccount().updatePrisonCard(1);
				break;
			}
			return true;
		}
		else return false;
	}


	/**
	 * Method for when a property is up for auction.
	 * @param player
	 * @param property
	 */
	public void auction(Player player, Property property) {
		ArrayList<Player> players = game.getPlayers();
		ArrayList<Player> biddingPlayers = new ArrayList<Player>();

		//Eliminerer spillere der er broke
		for( int i = 0; i<players.size(); i++) {
			if(players.get(i).isBroke() == false)
				biddingPlayers.add(players.get(i));
		}

		//Person der starter buddet sættes til at være personen efter player
		int bidder = players.indexOf(player)+1;

		int highestBid= 0;
		int newBid=0;

		boolean firstRound = true;
		boolean auctionOver = false;

		gui.showMessage(property.getFieldName() + " er sat på auktion.");

		while(!auctionOver) {
			//Hvis 
			if(bidder >= biddingPlayers.size()) {
				bidder=0;
			}
			String choice ="";

			//giver spiller mulighed for at være med i auktion
			choice = gui.getUserButtonPressed(biddingPlayers.get(bidder).getName()+": Vil du gerne byde på " + property.getFieldName() + "? - Højeste bud er nu " + highestBid + ",-", "ja","nej");
			if(choice.equals("ja"))
				newBid = gui.getUserInteger("Højeste bud er nu: " + highestBid +",-. Hvor meget ønsker du at byde " +  biddingPlayers.get(bidder).getName() + "? \n - Byder du det samme som højeste bud trækker du dig fra auktionen.", highestBid ,biddingPlayers.get(bidder).getAccount().getCash());


			//Tjekker budene
			if (newBid <= highestBid) {
				gui.showMessage(biddingPlayers.get(bidder).getName() + " er ude af auktionen.");
				biddingPlayers.remove(bidder);
			}
			else {
				highestBid = newBid;
				bidder++;
			}

			//Hvis en spiller vinder auktionen
			if((biddingPlayers.size() == 1) && highestBid > 0) {
				gui.showMessage("Tillykke " + biddingPlayers.get(0).getName() + "! \n Du har købt " + property.getFieldName()+ " for " + highestBid + ",-.");
				biddingPlayers.get(0).addOwnedProperties(property);
				property.setOwner(biddingPlayers.get(0));
				payMoneyToBank(biddingPlayers.get(0), highestBid);
				auctionOver = true;
				return;
			}

			else if(biddingPlayers.size() == 0) {//null?
				gui.showMessage("Der var ingen der ville købe " + property.getFieldName() + ". Ejendommen beholdes af Banken og spillet fortsætter.");
				auctionOver = true;
			}


		}

		
	}



	public void mortgage(Player player) {
		//		En spiller skal kunne pantsætte sin ejendom, for at modtage lån af banken. 
		//		Renten er 10 % og betales samtidigt med tilbagebetalingen af lånet. 
		//		Pantsætningen ophæves efterfølgende.
		boolean done = false;
		while(!done) {
			String choice = "";

			ArrayList<Property> fields = player.getOwnedProperties();
			ArrayList<Property> propsWithoutHouses = new ArrayList<Property>();
			ArrayList<Property> propsWithHouses = new ArrayList<Property>();

			for( int i = 0; i<fields.size(); i++) {
				//&& fields.

				if(!fields.get(i).getMortgage()) {
					if(fields.get(i) instanceof RealEstate) {
						if((((RealEstate)fields.get(i)).getHouses()==0)) {
							propsWithoutHouses.add((Property)fields.get(i));
						}

					}

					if(fields.get(i) instanceof Utility) {
						
						propsWithoutHouses.add((Property)fields.get(i));
						
					}

					if(fields.get(i) instanceof RealEstate) {
						if((((RealEstate)fields.get(i)).getHouses()>0)) {
							propsWithHouses.add((Property)fields.get(i));
						}
					}
				}

			}

			for (int j = 0; j<propsWithoutHouses.size();j++) {

				for(int j1 = 0; j1<propsWithHouses.size();j1++) {

					if(propsWithoutHouses.get(j).getColourSystem().equals(propsWithHouses.get(j1).getColourSystem()))
						propsWithoutHouses.remove(j);
				}

			}

			String[] propUserSelection = new String[propsWithoutHouses.size()+1];
			int mortgagePrice = 0;
			for (int i = 0; i<propsWithoutHouses.size(); i++) {
				propUserSelection[i] = propsWithoutHouses.get(i).getFieldName();
				mortgagePrice = propsWithoutHouses.get(i).getMortgagePrice();
			}
			propUserSelection[propUserSelection.length-1] = "tilbage";

			choice = gui.getUserSelection("Vælg grund du gerne vil pantsætte:", propUserSelection);

			if(!choice.equals("tilbage")) {

				String choice2 = gui.getUserButtonPressed("Er du sikker på du vil pantsætte " + choice + "?" , "ja" , "nej");

				if(choice2.equals("ja")) {
					for(int k = 0; k<propsWithoutHouses.size(); k++) {
						if(propsWithoutHouses.get(k).getFieldName().equals(choice)) {

							gui.showMessage(player.getName() + ", du har valgt at pansætte " + propsWithoutHouses.get(k).getFieldName() + " for " + propsWithoutHouses.get(k).getMortgagePrice());
							propsWithoutHouses.get(k).setMortgage(true);
							receiveMoney(player, propsWithoutHouses.get(k).getMortgagePrice());
							propsWithoutHouses.remove(k);
						}
					}
				}

				if(propsWithoutHouses.size()>0) {
					choice = gui.getUserButtonPressed(player.getName() + ", vil du fortsætte med at pantsætte?","ja","nej");


					if(choice.equals("nej")) {
						done= true;
					}
				}
				else {
					gui.showMessage(player.getName() + ", du har ikke flere grunde at pantsætte");
					done = true;
				}
			}
			else {
				done = true;
			}
		}
	}

	public void unMortgage(Player player) {
		boolean done = false;
		while(!done){
			String choice = "";
			ArrayList<Property> ownedProps = player.getOwnedProperties();
			ArrayList<Property> mortgageedProps = new ArrayList<Property>();
			ArrayList<String> mortgageedPropsNames = new ArrayList<String>();
			for(int i = 0; i< ownedProps.size();i++) {
				if(ownedProps.get(i).getMortgage()) {
					mortgageedProps.add((Property)ownedProps.get(i));
					mortgageedPropsNames.add(ownedProps.get(i).getFieldName());
				}
			}
			String[] usNames = new String[mortgageedPropsNames.size()+1];
			usNames = mortgageedPropsNames.toArray(usNames);
			usNames[usNames.length-1] = "Annuller";

			choice = gui.getUserSelection("Vælg ejendom du ønsker at købe fri af banken. Vælg ejendom og se pris: ", usNames);
			if(choice.equals("Annuller")) {
				done = true;
			}
			else {
				double unMortgagePrice=0;
				for(int i = 0 ; i < mortgageedProps.size(); i++) {
					if(mortgageedProps.get(i).getFieldName().equals(choice)) {
						unMortgagePrice =(int)((mortgageedProps.get(i).getMortgagePrice())*1.1);
					}
				}
				String choice2 = gui.getUserButtonPressed("Vil du gerne betale banken " + unMortgagePrice + " for at købe " + choice + " fri af banken?", "ja", "nej");

				if(choice2.equals("ja")) { //Unmortgage Property
					for(int i = 0 ; i < mortgageedProps.size(); i++) {
						if(mortgageedProps.get(i).getFieldName().equals(choice)) {
							mortgageedProps.get(i).setMortgage(false);
							payMoneyToBank(player, (int)unMortgagePrice);
							mortgageedProps.remove(i);
						}
					}
				}
			}


			if(mortgageedProps.size()>0) {
				choice = gui.getUserButtonPressed(player.getName() + ", vil du fortsætte med at købe grunde fri af banken?","ja","nej");


				if(choice.equals("nej")) {
					done= true;
				}
			}
			else {
				gui.showMessage(player.getName() + ", du har ikke flere grunde at købe fri af banken");
				done = true;
			}
		}
	}


	private void loadGame() {
		
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
				System.out.println(getAssetValue(currentPlayer));
			}

			currentIndex = (currentIndex+1)%players.size();
			game.setCurrentPlayer(players.get(currentIndex));
		}
	}

	public void playerTurn(Player player) {
		boolean throwDouble=false;
		int doubleCount=0;
		do {
			if(player.getInPrison()>0) {
				playerInPrison(player);
			}

			game.getDice().setRolled(false);
			if(player.getInPrison()==0) {
				generateCash(game.getCurrentPlayer(), 0);
			}

			game.getDice().rollDice();
			int[] faceValue = game.getDice().getFaceValue();
			gui.setDice(faceValue[0], faceValue[1]);
			gui.showMessage(player.getName() + ", du har slået " + game.getDice().getSum());
			throwDouble = (game.getDice().isEqual(faceValue));

			if(throwDouble) {
				doubleCount++;
			}

			if(throwDouble && player.getInPrison()>0) {
				player.setInPrison(0);
				gui.showMessage(player.getName() + ", du har kastet to ens, mens du var i fængsel, og kan "
						+ "forstætte ved at rykke " +game.getDice().getSum() + " felter frem");
			}
			else if(doubleCount>2) {
				gui.showMessage(player.getName() + ", du har kastet to ens tre gange i træk, og skal derfor "
						+ "i fængsel");
				player.setInPrison(1);
				player.setPosition(10);
				return;
			}
			else if(player.getInPrison()>0 && player.getInPrison()<3) {
				gui.showMessage(player.getName() + ", du slog ikke to ens, og forbliver i fængsel en runde til");
				player.setInPrison(player.getInPrison()+1);
				return;
			}
			else if(player.getInPrison()==3){
				gui.showMessage(player.getName() + ", du slog ikke to ens, og har allerede prøvet at slå med terningerne 3 gange. Du må nu betale 1000 kr for at komme ud og rykke " + game.getDice().getSum());
				player.setInPrison(0);
			}
			int position = player.getPosition() + game.getDice().getSum();
			moveToField(player, position, false);

			if(throwDouble) {
				gui.showMessage(player.getName() + ", du slog to ens og får et ekstra kast");
			}

			if(player.getInPrison()==0) {
				generateCash(player, 0);
			}
		}while(throwDouble);
	}


	/**
	 * This method moves the player to another field.
	 * The method is used in the "cardMoveToField" and the "moveToFerryDouble" methods. 
	 * @author 
	 * @param player
	 * @param position
	 */
	public void moveToField(Player player, int position, boolean moveBack) {
		position = position % game.getFields().size();
		if(moveBack) {
			player.setPosition(position % game.getFields().size());
		}
		else if(position<player.getPosition() && player.getInPrison()==0) {
			player.setPosition(position % game.getFields().size());
			gui.showMessage(player.getName() + ", du har passeret start, og modtager 4000 kr");
			receiveMoney(player, 4000);
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
		ArrayList<String> options = new ArrayList<>();
		// if the player is in prison and has a get out of jail free card. 		


		if(player.getAccount().getPrisonCard()>0) {
			options.add("Brug fængselskort");
		}
		// if the player has over 1000 kr
		if(player.getAccount().getCash()>=1000) {
			options.add("Betal 1000 kr");
		}
		options.add("Kast terningerne");
		String[] option = new String[options.size()];
		option = options.toArray(option);

		choice = gui.getUserButtonPressed(player.getName() + ", du er i fængsel. Du har følgende muligheder for at komme ud af fængslet", option);


		switch(choice) {
		case "Betal 1000 kr": 	payMoneyToBank(player, 1000);
		player.setInPrison(0);						
		gui.showMessage(player.getName() + ", du har betalt 1000 kr og er kommet ud af fængslet. Fortsæt turen ved at kaste med terningerne");
		break;
		case "Brug fængselskort": player.getAccount().updatePrisonCard(-1);
		player.setInPrison(0);
		gui.showMessage(player.getName() + ", du har brugt et fængsels kort og er kommet ud af fængslet. Fortsæt turen ved at kaste med terningerne");
		break;
		case "Kast terningerne": break;
		}

	}

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
	public void payMoneyToBank(Player player, int amount) {
		if(player.getAccount().getCash()<amount) {
			boolean bankrupt = generateCash(player, amount);
			if(bankrupt) {
				bankruptToBank(player);
			}
			return;
		}
		player.getAccount().updateCash(-amount);
	}

	public void payMoneyToPlayer(Player payingPlayer, int amount, Player receivingPlayer) {
		if(payingPlayer.getAccount().getCash()<amount) {
			boolean bankrupt = generateCash(payingPlayer, amount);
			if(bankrupt) {
				bankruptToPlayer(payingPlayer, receivingPlayer);
			}
			return;
		}
		payingPlayer.getAccount().updateCash(-amount);
		receivingPlayer.getAccount().updateCash(amount);
	}

	/**
	 * Method, where a specific player receives an amount of money.
	 * @param player the player which is receiving money
	 * @param amount the amount the player is receiving
	 * 
	 * @author Gunn
	 */
	public void receiveMoney(Player player, int amount) {
		player.getAccount().updateCash(amount);
	}






	public void addOwnedProperties(Player player, Property property) {
		player.addOwnedProperties(property);
	}

	public void offerToBuyProperty(Property property) {
		Player player = game.getCurrentPlayer();
		if (property.isForSale()) {
			//Vil spilleren købe den ellers skal den sættes på auktion 

			String playerChoice = gui.getUserButtonPressed(player.getName()+ " vil du købe " + property.getFieldName() + " for " + property.getPrice(), "Nej", "Ja");

			if (playerChoice.equals("Ja")) {
				property.setForSale(false);
				property.setOwner(player);
				payMoneyToBank(player, property.getPrice());
				addOwnedProperties(player, property);
			}
			else auction(player, property);
		}

		//Hvis grunden ikke er til salg
		//Spilleren kan, når han lander på grunden:
		//Betale leje 
		//Ikke betale leje (Hvis ejeren er i fængsel, eller ved pansætning
		//Sætte ejendommen på auktion. 
		else {
			if (property.getOwner().equals(player)) {
				gui.showMessage(property.toString() + ", du er selv ejer af dette felt, og skal selvfølgelig ikke betale");
			}
			else if (property.getOwner().getInPrison()!= 0) { 
				gui.showMessage(property.toString() + ", ejeren er i fængsel, du slipper denne gang!");
			}
			else if (property.getMortgage()) {
				gui.showMessage(property.toString() + ", grunden er pantsat, du slipper denne gang!");
			}
			else {
				if(property.getColourSystem().equals("ship") || property.getColourSystem().equals("darkgreen")) {
					ownedUtilitiesSameType((Utility) property, player);
				}
				else {
					if(ownedRealEstateSameColour((RealEstate) property, player)) {

						paySameTypeRealEstate((RealEstate)property, player);

					} else { 
						gui.showMessage(player.getName() + ", du er landet på " + property.getOwner().getName() +"'s ejendom og skal betale " + rent[property.getFieldNumber()][0] + " i leje");
						payMoneyToPlayer(player, rent[property.getFieldNumber()][0], property.getOwner());
					}
				}
			}
		}
	}

	public void paySameTypeUtility(Property property, Player player, int[] count) {
		// If there only exist two types of this utility, it is a "bryggeri"
		if (count[0] == 2) {
			switch(count[1]) {
			case 1: payMoneyToPlayer(player, property.getRent()*game.getDice().getSum(), property.getOwner());
			gui.showMessage("Du er landet på" + property.getOwner().getName() +"s ejendom og skal betale" + 1);
			break;
			case 2: payMoneyToPlayer(player, 2*property.getRent()*game.getDice().getSum(), property.getOwner());
			gui.showMessage("Du er landet på" + property.getOwner().getName() + "s ejendom og skal betale " + 2);
			break;
			}

		}
		// else it is a "rederi".
		else {
			switch(count[1]){
			case 1: payMoneyToPlayer(player, rent[property.getFieldNumber()][0], property.getOwner());
			gui.showMessage("Du er landet på" + property.getOwner().getName() + "s ejendom og skal betale " + 1);
			break;
			case 2: payMoneyToPlayer(player, rent[property.getFieldNumber()][1], property.getOwner());
			gui.showMessage("Du er landet på" + property.getOwner().getName() + "s ejendom og skal betale " + 2);
			break;
			case 3: payMoneyToPlayer(player, rent[property.getFieldNumber()][2], property.getOwner());
			gui.showMessage("Du er landet på" + property.getOwner().getName() + "s ejendom og skal betale " + 3);
			break;
			case 4: payMoneyToPlayer(player, rent[property.getFieldNumber()][3], property.getOwner());
			gui.showMessage("Du er landet på" + property.getOwner().getName() +"s ejendom og skal betale " + 4);
			break;
			} // skal opdateret fra txt filen af rederileje ^^VIGTIGT
		}

	}


	public void paySameTypeRealEstate(RealEstate realEstate, Player player) {
		switch(realEstate.getHouses()){
		case 0: payMoneyToPlayer(player, rent[realEstate.getFieldNumber()][0]*2, realEstate.getOwner());
		gui.showMessage(player.getName() + ", du er landet på " + realEstate.getOwner().getName() +"'s ejendom og skal betale " + (rent[realEstate.getFieldNumber()][5]*2) + " da " + realEstate.getOwner().getName() + " ejer alle ejendommene af denne type");
		break;
		case 1: payMoneyToPlayer(player, rent[realEstate.getFieldNumber()][1], realEstate.getOwner());
		gui.showMessage(player.getName() + ", du er landet på " + realEstate.getOwner().getName() +"'s ejendom og skal betale " + rent[realEstate.getFieldNumber()][1]+ " da " + realEstate.getOwner().getName() + " har et hus på denne ejendom");
		break;
		case 2: payMoneyToPlayer(player, rent[realEstate.getFieldNumber()][2], realEstate.getOwner());
		gui.showMessage(player.getName() + ", du er landet på " + realEstate.getOwner().getName() +"'s ejendom og skal betale " + rent[realEstate.getFieldNumber()][2]+ " da " + realEstate.getOwner().getName() + " har to huse på denne ejendom");
		break;
		case 3: payMoneyToPlayer(player, rent[realEstate.getFieldNumber()][3], realEstate.getOwner());
		gui.showMessage(player.getName() + ", du er landet på " + realEstate.getOwner().getName() +"'s ejendom og skal betale " + rent[realEstate.getFieldNumber()][3]+ " da " + realEstate.getOwner().getName() + " har tre huse på denne ejendom");
		break;
		case 4: payMoneyToPlayer(player, rent[realEstate.getFieldNumber()][4], realEstate.getOwner());
		gui.showMessage(player.getName() + ", du er landet på " + realEstate.getOwner().getName() +"'s ejendom og skal betale " + rent[realEstate.getFieldNumber()][4]+ " da " + realEstate.getOwner().getName() + " har fire huse på denne ejendom");
		break;
		case 5: payMoneyToPlayer(player, rent[realEstate.getFieldNumber()][5], realEstate.getOwner());
		gui.showMessage(player.getName() + ", du er landet på " + realEstate.getOwner().getName() +"'s ejendom og skal betale " + rent[realEstate.getFieldNumber()][5]+ " da " + realEstate.getOwner().getName() + " har hotel på denne ejendom");
		break;
		}
	}
	public void bankruptToBank(Player player) {
		//Invoke Auction on properties owned by bankrupt Player.
		player.setBroke(true);
		ArrayList<Property> propertiesForAuction =player.getOwnedProperties();
		for(int i = 0; i<propertiesForAuction.size(); i++) {
			propertiesForAuction.get(i).setMortgage(false);
			auction(player, propertiesForAuction.get(i));
			propertiesForAuction.get(i).setOwner(null);

			//Evt. game.getPlayersArrayList.remove(player).
		}
	}
	public void bankruptToPlayer(Player bankruptPlayer, Player receivingPlayer) {
		ArrayList<Property> propsForTransfers = bankruptPlayer.getOwnedProperties();
		int cashForReceiver = bankruptPlayer.getAccount().getCash();
		for(int i = 0; i<propsForTransfers.size(); i++) {
			if(propsForTransfers.get(i).getMortgage()==false) {
				cashForReceiver += propsForTransfers.get(i).getMortgagePrice();
				propsForTransfers.get(i).setMortgage(true);
			}
			receivingPlayer.addOwnedProperties(propsForTransfers.get(i));
		}
		payMoneyToPlayer(bankruptPlayer, cashForReceiver, receivingPlayer);

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
				if ((((Property) (game.getFields().get(i))).getColourSystem()).equals(realEstate.getColourSystem())){
					colourCount++;
					if (((Property) (game.getFields().get(i))).getOwner() != null && (((Property) (game.getFields().get(i))).getOwner()).equals(realEstate.getOwner())){
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
				if (((Property) game.getFields().get(i)).getColourSystem().equals(utility.getColourSystem())){
					colourCount++;
					if (((Property) game.getFields().get(i)).getOwner() != null && ((Property) game.getFields().get(i)).getOwner().equals(utility.getOwner())){
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
	 * It enables you to trade with , mortgage properties and sell houses/hotels
	 * @param payingPlayer: the player who needs to generate cash to pay
	 * @param ammount: ammount to pay
	 * 
	 * @return returns the full ammount if player is able to raise funds or returns a lesser ammount if the player cannot raise cash and goes bankrupt.
	 */
	public boolean generateCash(Player player, int ammount) {
		String choice = "";
		boolean done = false;
		boolean bankrupt = false;
		while (!done) {

			boolean byHouses = false;
			boolean sellHouses = false;
			boolean morgtageProperty = false;
			boolean unMortgageProperty = false;
			boolean trade = false;
			boolean throwDice = false;
			boolean endTurn = false;
			// If the player hasn't thrown the dice
			if(!game.getDice().isRolled()) {
				throwDice = true;
			}
			else if(game.getDice().isEqual(game.getDice().getFaceValue())) {
				throwDice = true;
			}
			else {
				throwDice = false;
				endTurn = true;
			}

			// If the player has properties
			if(player.getOwnedProperties().size()>0) {  
				// Player can mortgage property and trade with other players
				for(int i = 0; i<game.getPlayers().size(); i++) {
					if(game.getPlayers().get(i).getOwnedProperties().size()>0 && !game.getPlayers().get(i).equals(player)) {
						trade = true;
						break;
					}
				}



				for(int i=0; i<player.getOwnedProperties().size(); i++) {
					// If the property is morgtaged
					if(player.getOwnedProperties().get(i).getMortgage()) {
						// player can unmorgtage property
						unMortgageProperty = true;
					}
					else {
						morgtageProperty = true;
					}
					// if the property the player owns is of type realEstate
					if(player.getOwnedProperties().get(i) instanceof RealEstate) {

						// if the player owns all properties of the same type
						if(ownedRealEstateSameColour((RealEstate) player.getOwnedProperties().get(i), player)) {

							// if the player doesn't have hotel on property
							if(((RealEstate) player.getOwnedProperties().get(i)).getHouses() <= 4 && !player.getOwnedProperties().get(i).getMortgage()) {
								// Player can by houses
								byHouses = true;
							}
							// if the player has houses on the property
							if(((RealEstate) player.getOwnedProperties().get(i)).getHouses()>0) {
								// Player can sell houses
								sellHouses = true;
							}
						}
					}
				}
			}



			ArrayList<String> option = new ArrayList<>();
			if(player.getAccount().getCash()<ammount) {
				option.add("Erklær dig konkurs");
			}
			if(byHouses) {
				option.add("Køb hus");
			}
			if(sellHouses) {
				option.add("Sælg hus/hotel");
			}
			if(morgtageProperty) {
				option.add("Pantsæt grund");
			}
			if(unMortgageProperty) {
				option.add("Tilbagebetaling af pantsæt grund");
			}
			if(trade) {
				option.add("Byt med medspiller");
			}
			if(throwDice) {
				option.add("Kast med terningen");
				game.getDice().setRolled(true);
			}
			if(endTurn) {
				option.add("Afslut tur");
			}

			String[]optionStrings = new String[option.size()];
			optionStrings = option.toArray(optionStrings);

			if(player.getAccount().getCash()<ammount) {
				choice = gui.getUserButtonPressed(game.getCurrentPlayer().getName() + ", du har ikke nok penge til at betale dit udestående. Hvordan vil du håndtere dette:", optionStrings);
			}
			else {
				choice = gui.getUserButtonPressed(game.getCurrentPlayer().getName() + ", hvordan vil du fortsætte?", optionStrings);
			}


			switch(choice) {
			case "Sælg hus/hotel": 
				sellHousesAndHotels(player, ammount);
				break;
			case "Køb hus":
				buyHousesAndHotels(player);
				break;
			case "Pantsæt grund":
				mortgage(player);
				break;
			case "Tilbagebetaling af pantsæt grund":	
				unMortgage(player);
				break;
			case "Byt med medspiller":
				trade(player);
				break;
			case "Erklær dig konkurs":
				bankrupt = true;
				done = true;
				break;
			case "Kast med terningen":
				done = true;
				break;

			case "Afslut tur":
				done=true;
				break;
			}
		}
		return bankrupt;


	}
	public void buyHousesAndHotels(Player player) {
		String choice = "";
		boolean done = false;

		while(!done) {
			ArrayList<Property> ownedFields = player.getOwnedProperties();
			HashSet<String> fieldColors = new HashSet<String>();

			//
			for (int i = 0; i<ownedFields.size(); i++) {
				//Hvis spilleren ejer alle felter i samme farve
				if(ownedFields.get(i) instanceof RealEstate) {
					if(ownedRealEstateSameColour((RealEstate)ownedFields.get(i), player)) {

						String testIfMortgageed = ownedFields.get(i).getColourSystem();
						int numberOfMortgageed=0;

						for(int i2 = 0; i2<ownedFields.size(); i2++) {
							//Hvis farven på grundene matcher og grunden er pantsat tælles numberOfMortgageed op.
							if(testIfMortgageed.equals(ownedFields.get(i2).getColourSystem()) && (ownedFields.get(i2).getMortgage() == true)) {
								numberOfMortgageed ++;
							}

						}
						//Hvis antallet af pantsatte ejendomme i en given farve er større end 0, så fjern alle i den frave fra ownedFields
						if(numberOfMortgageed > 0)
							for(int i3 = 0; i3<ownedFields.size(); i3++) {
								if (testIfMortgageed.equals(ownedFields.get(i3).getColourSystem())){
									ownedFields.remove(i3);
								}
							}
						else
							fieldColors.add(ownedFields.get(i).getColourSystem());

					}

					else {
						ownedFields.remove(i);
					}
				}
			}

			String[] guiChoice = new String[fieldColors.size()+1];
			guiChoice = fieldColors.toArray(guiChoice);
			guiChoice[guiChoice.length-1] = "Annuller";

			choice = gui.getUserSelection("Vælg farve på felt du vil bygge på: ", guiChoice);
			if(choice.equals("Annuller")) {
				done = true;
			}
			else {
				ArrayList<String> guiPropertyNames = new ArrayList<String>();
				ArrayList<Property> propertiesInColor = new ArrayList<Property>();

				for(int i = 0; i<ownedFields.size(); i++) {
					//Hvis spilleren har valgt en given farve, så lægges de forskellige farver in i en arrayListe.
					if(choice.equals(ownedFields.get(i).getColourSystem())){
						propertiesInColor.add((Property)ownedFields.get(i));
						guiPropertyNames.add(ownedFields.get(i).getFieldName());
					}
				}

				//Konverter til array så det kan bruges i gui
				String[] guiChoice2 = new String[guiPropertyNames.size()+1];
				guiChoice2 = guiPropertyNames.toArray(guiChoice2);
				guiChoice2[guiChoice2.length-1] = "Annuller";

				//Herunder begynder loopet som holder spilleren i "samme farve" hvis spilleren ønsker at købe mere end ét hus
				boolean doMore = true;
				while(doMore) {

					//Vælger grund
					choice = gui.getUserButtonPressed("", guiChoice2);


					if(!choice.equals("Annuller")) {
						int totalHousesInColor = 0;
						int housesOnChoice = 0;
						int buildPrice = 0;
						//Loop gemmer samlet antal huse og huse på valgt grund i variable , samt prisen for at bygge +1 på grund
						for(int i = 0; i<propertiesInColor.size(); i++) {
							totalHousesInColor+=((RealEstate) propertiesInColor.get(i)).getHouses();

							if (propertiesInColor.get(i).getFieldName().equals(choice)) {
								housesOnChoice = ((RealEstate) propertiesInColor.get(i)).getHouses();
								buildPrice = ((RealEstate) propertiesInColor.get(i)).getBuildingPrice();
							}
						}
						double avgBuildings = totalHousesInColor/propertiesInColor.size();
						//Hvis gennemsnittet af byggede huse er større end på den valgte grund, må der bygges.

						//Hvis muligt at bygge:
						if(avgBuildings>=housesOnChoice) {

							//Hvis spilleren ikke har nok penge til at købe et hus
							if(((RealEstate) propertiesInColor.get(0)).getBuildingPrice()> player.getAccount().getCash()) {
								gui.showMessage("Du har ikke penge til at købe et hus på den valgte ejendom.");

							}
							//Ellers er der allerede hotel på grunden
							else if(housesOnChoice==5) {
								gui.showMessage("Du kan har allerede et hotel på grunden. Du kan ikke bygge mere på den valgte grund.");
							}
							//Ellers så har spilleren nok til at bygge hus/hotel og der ligger ikke hotel i forvejen
							else {
								String choice2 = "";
								choice2 = gui.getUserButtonPressed("Er du sikker på du vil bygge på " + choice + " for " + buildPrice + "?" , "Ja" , "Nej");
								//Byg
								if (choice2.equals("Ja")) {
									//Bygger huset på valgt RealEstate
									for(int i = 0; i<propertiesInColor.size(); i++) {

										if (propertiesInColor.get(i).getFieldName().equals(choice)) {
											((RealEstate)propertiesInColor.get(i)).buyHouse();
											//Evt. lægge et nyt hus til i arrayet
										}


									}
									//Træk beløb fra konto.
									payMoneyToBank(player, buildPrice);

								}




							}


						}else {
							gui.showMessage("Du kan ikke bygge på den valgte ejendom, før du har bygget lige mange huse på de andre ejendomme med samme farve");
						}
					}
					choice = gui.getUserButtonPressed("Hvad vil du gøre nu?", "Køb huse på andre felt-farver", "købe flere huse på samme felt-farve", "Afslut \"køb huse/hoteller\"");
					if (choice.equals("Køb huse på andre felt-farver"))
						doMore = false;
					else if(choice.equals("købe flere huse på samme felt-farve"))
						doMore = true;
					else
						doMore = false;
					done = true;
				}
			}
		}
	}

	public void sellHousesAndHotels(Player player, int ammount) {
		String choice = "";
		boolean done = false;

		while(!done) {

			ArrayList<Property> propsWithHouses = player.getOwnedProperties();

			//Saves the color system (buddyfields reference)
			Set<String> colorSystem = new HashSet<String>();

			ArrayList<Property> fieldsWithHouses = new ArrayList<Property>();

			for(int i = 0 ; i<propsWithHouses.size(); i++) {
				if(propsWithHouses.get(i) instanceof RealEstate) {
					if(((RealEstate) propsWithHouses.get(i)).getHouses()>0) { // if something is build on property - add name to houseToSell and Field to fieldsWithHouses
						colorSystem.add(((Property) propsWithHouses.get(i)).getColourSystem());
						fieldsWithHouses.add((Property) propsWithHouses.get(i));
					}
				}
			}


			//List og set skal konverteres for at kunne gives som 
			//Array der skal anvendes til userSelection
			String[] displayColorSystem = new String[colorSystem.size()];
			displayColorSystem = colorSystem.toArray(displayColorSystem);

			//Array med Fields
			Fields[] fieldArray = new Fields[fieldsWithHouses.size()];
			fieldArray = fieldsWithHouses.toArray(fieldArray);


			//Evt start loop her der holder spilleren i ejendomsfarven
			//Spiller vælger ejendomsfarve
			choice = gui.getUserSelection("Vælg ejendomsfarve du vil sælge bygning på:", displayColorSystem);

			ArrayList<String> sameTypePropertiesNames = new ArrayList<String>();
			ArrayList<Fields> sameTypePropertiesFields = new ArrayList<Fields>();
			for (int j = 0; j<fieldArray.length; j++) {

				if(choice.equals(((Property) fieldArray[j]).getColourSystem())) {
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
				int chosen = 0;

				for(int h = 0; h< availableBuildings.length; h++) {
					h2+=((RealEstate) sameTypePropertiesFields.get(h)).getHouses();//lægger det totale antal huse i en bestemt farve sammen. Disse kan divideres med antallet af huse som er valgt af brugeren.
					if(choice.equals(availableBuildings[h])) {
						h1 = ((RealEstate) sameTypePropertiesFields.get(h)).getHouses();
						chosen = h;
					}
				}

				
				for(int i = 0; i<fieldsWithHouses.size(); i++) {
					System.out.println("Navn : " + fieldsWithHouses.get(i).getFieldName() + ", antal huse: " + ((RealEstate) fieldsWithHouses.get(i)).getHouses());
					
				}
				
				
				if (h1 >= (h2/sameTypePropertiesFields.size()) && (((RealEstate) fieldsWithHouses.get(chosen)).getHouses() >0)) {

					//remove house on Fields that player owns
					System.out.println(h1); 
					((RealEstate)sameTypePropertiesFields.get(chosen)).sellHouse();
					//remove houses from the array the used to sell houses from:
					//((RealEstate) sameTypePropertiesFields.get(h1)).sellHouse();
					//remove house on players houseArray()
					//					game.getFields().get(sameTypePropertiesFields.get(h1).getFieldNumber()).sellHouse();
					// remove getHouseBuildingPrice from players account (assets)
//					player.getAccount().updateAssetValue(-(((RealEstate) game.getFields().get(sameTypePropertiesFields.get(h1).getFieldNumber())).getBuildingPrice()));
					//return half of HouseBuildingPrice to Players cash in account.

					System.out.println("Kom her til"); //ReceiveMoney
					player.getAccount().updateCash((((RealEstate) game.getFields().get(sameTypePropertiesFields.get(chosen).getFieldNumber())).getBuildingPrice())/2);


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
			String playerChoice = gui.getUserButtonPressed(game.getCurrentPlayer().getName()+ tax.toString() +tax.getPrice() + "?", "4000", "10%");
			if(playerChoice.equals(Integer.toString(tax.getPrice()))) {
				payMoneyToBank(game.getCurrentPlayer(), tax.getPrice());

			} else {
				payMoneyToBank(game.getCurrentPlayer(), getAssetValue(game.getCurrentPlayer()));
			}
		} else {
			payMoneyToBank(game.getCurrentPlayer(), tax.getPrice());
		}
	}

	public void neutralField(Fields field) {
		gui.showMessage(game.getCurrentPlayer().getName() + field.toString());
	}


	public void cardMoveToField(ChanceCard chanceCard) {
		gui.displayChanceCard(chanceCard.toString());
		gui.showMessage(chanceCard.toString());
		if(chanceCard.getCardNumber()<=21) {
			//nearest ferry + double rent
			moveToFerryDouble();
		}
		else if(chanceCard.getCardNumber()<=23) {
			//Move -3
			if(game.getCurrentPlayer().getPosition()==2) {
				moveToField(game.getCurrentPlayer(), 39, false);
			}
			else {
				moveToField(game.getCurrentPlayer(), game.getCurrentPlayer().getPosition()-3, true);
			}

		}
		else if(chanceCard.getCardNumber()==24) {
			//Start
			moveToField(game.getCurrentPlayer(), 0, false);
		}
		else if(chanceCard.getCardNumber() == 25) {
			//rådhuspladsen
			moveToField(game.getCurrentPlayer(), 39, false);

		}
		else if(chanceCard.getCardNumber()==26) {
			//molslinje
			moveToField(game.getCurrentPlayer(), 15, false);

		}
		else if (chanceCard.getCardNumber() == 27) {
			//Move to Grønningen
			moveToField(game.getCurrentPlayer(), 24, false);

		}
		else {
			//Move to Frederiksberg Allé
			moveToField(game.getCurrentPlayer(), 11, false);
		}

	}

	private void moveToFerryDouble() {
		int position = game.getCurrentPlayer().getPosition();

		int arrayPositionOfFerry = 0;

		if ((position)<6 || (position)>=36) {
			arrayPositionOfFerry = 5;
		}
		else if (position < 16) {
			arrayPositionOfFerry = 15;
		}
		else if (position < 26) { 
			arrayPositionOfFerry = 25;
		}
		else if ((position) < 36) {

			arrayPositionOfFerry = 35;
		}

		if(((Property) game.getFields().get(arrayPositionOfFerry)).getOwner() !=null ) {
			moveToField(game.getCurrentPlayer(), arrayPositionOfFerry, false);
			ownedUtilitiesSameType((Utility) game.getFields().get(arrayPositionOfFerry), game.getCurrentPlayer());
		}

		else {
			moveToField(game.getCurrentPlayer(), arrayPositionOfFerry, false);
		}
	}

	public void playerGetsPrisonCard(ChanceCard chanceCard) {
		gui.displayChanceCard(chanceCard.toString());
		gui.showMessage(chanceCard.toString());
		game.getCurrentPlayer().getAccount().updatePrisonCard(1);
	}

	public void cardGoToJail(ChanceCard chanceCard) {
		gui.displayChanceCard(chanceCard.toString());
		gui.showMessage(chanceCard.toString());
		game.getCurrentPlayer().setInPrison(1);		
		game.getCurrentPlayer().setPosition(10);
	}

	public void cardPay(ChanceCard chanceCard) {
		gui.displayChanceCard(chanceCard.toString());
		gui.showMessage(chanceCard.toString());
		if(chanceCard.getCardNumber()==18) { //kortet betaler per hus og hotel
			int housePrice = 800;
			int hotelPrice = 2300;

			payMoneyToBank(game.getCurrentPlayer(), payPerHouseAndHotel(housePrice,hotelPrice));

		}
		else if(chanceCard.getCardNumber()==19) { //kortet betaler per hus og hotel
			int housePrice = 500;
			int hotelPrice = 2000;

			payMoneyToBank(game.getCurrentPlayer(), payPerHouseAndHotel(housePrice,hotelPrice));;
		}

		else
			payMoneyToBank(game.getCurrentPlayer(),chanceCard.getAmount());

	}

	private int payPerHouseAndHotel( int housePrice, int hotelPrice) {
		int houses=0;
		int hotels=0;
		Player player = game.getCurrentPlayer();

		ArrayList<Fields> fields = game.getFields(); 
		int[]array = new int[fields.size()];
		for(int i = 0; i<array.length; i++) {
			if(fields.get(i) instanceof RealEstate) {
				if(player.equals(((RealEstate) fields.get(i)).getOwner()))
					array[i]=((RealEstate) fields.get(i)).getHouses();
			}
		}

		for (int i = 0; i< array.length; i++) {//antal huse og hoteller findes
			if (array[i]==5)
				hotels++;
			else
				houses+= array[i];
		}
		return (houses*housePrice + hotels*hotelPrice);
	}


	public void cardReceiveMoney(ChanceCard chanceCard) {
		gui.displayChanceCard(chanceCard.toString());
		gui.showMessage(chanceCard.toString());
		if (chanceCard.getCardNumber() == 1) { //Fødselsdag - Modtag 200 fra hver spiller.
			for(int i = 0; i<game.getPlayers().size(); i++) {
				if(game.getCurrentPlayer().equals(game.getPlayers().get(i))) {
					continue;
				}
				else {
					payMoneyToPlayer(game.getPlayers().get(i), 200, game.getCurrentPlayer());
				}
			}			
		} 
		else if(chanceCard.getCardNumber() == 10) { // Matador legatet: assetValue<15.000 Modtager 40.000
			//Hensigten med assets/cash Det antages at cash/assets opdateres hver for sig og at assets også opdateres når der købes huse/hoteller.
			int totValue = getAssetValue(game.getCurrentPlayer()) + game.getCurrentPlayer().getAccount().getCash(); // Gets cash and assets
			if (totValue < 15000) {
				receiveMoney(game.getCurrentPlayer(), chanceCard.getAmount());
			}
		}
		else {
			payMoneyToBank(game.getCurrentPlayer(), chanceCard.getAmount());
		}
	}

	public int getAssetValue(Player player) {
		int asset = 0;
		for(int i = 0; i<player.getOwnedProperties().size(); i++) {
			if(player.getOwnedProperties().get(i).getMortgage()) {
				asset += player.getOwnedProperties().get(i).getMortgagePrice();
			}
			else {
				asset += player.getOwnedProperties().get(i).getPrice();
				if(player.getOwnedProperties().get(i) instanceof RealEstate) {
					if(((RealEstate)player.getOwnedProperties().get(i)).getHouses()>0) {
						asset += ((RealEstate)player.getOwnedProperties().get(i)).getHouses()*((RealEstate) player.getOwnedProperties().get(i)).getBuildingPrice();

					}
				}
			}
		}
		return asset + player.getAccount().getCash();
	}
}

