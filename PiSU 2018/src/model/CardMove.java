package model;
/**
 * Inheriting methods & attributes from the ChanceCard class
 * Includes methods & attributes for chancecards that directs a player to move to a specific field.
 * @author Christian ++
 *
 */
import model.Game;
public class CardMove extends ChanceCard {

	private int moveTo;
	/**
	 * Constructor 
	 * @param cardNumber
	 * @param text
	 * @param test
	 */
	public CardMove(int cardNumber, String text,int fieldNumber) {
		super(cardNumber,text);
		moveTo = fieldNumber;
	}
	/**
	 * Override
	 * Sets the players position to the specific field from the chancecard.
	 */
	@Override
	public void performAction(Game game) {
		int position = game.getCurrentPlayer().getPosition();
		int docksOwned = 0;
		Player player = null;
		Player playerWhoOwnsField= null;
		
		for (int i = 0; i<game.getPlayerAmount(); i++) {
			player = game.getPlayers().get(i);
		
		if ((position%40)<6 || (position%40)>36) {
			docksOwned = moveToDocks(game,5, player);
			game.getCurrentPlayer().setPosition(5); // Obs skal lige høre hvordan position skal gemmes/ dvs. enten skal den være 5 eller 6 når der flyttes.
		}
		else if ((position%40) < 16) {
			docksOwned = moveToDocks(game,15, player);
			game.getCurrentPlayer().setPosition(15);
			}
		else if ((position%40) < 26) { 
			docksOwned = moveToDocks(game,25, player);
			game.getCurrentPlayer().setPosition(25);
		}
		
		 else if ((position%40) < 36) {
			docksOwned = moveToDocks(game,35, player);
			game.getCurrentPlayer().setPosition(35);
		 }
		//calculateRent
		//Færgepladserne skal tilføjes i en fil - evt. RentPrices.txt så prisen der skal betales kan udregnes
		int ammountToPay =0;
		// træk beløb fra spiller
		game.getCurrentPlayer().getAccount().updateCash(ammountToPay);
		//indsæt beløb hos ejer
		
		
		
//		if (docksOwned != 0) {
//			//Betal dobbelthusleje alt efter variablen
//	}
		}
		//Læg mulighed for at købe ejendommen her
		}
	

	/**
	 * Sets the players position to the nearest dock, from where the player is originally positioned. If a player owns a dock, then it is calculated how many docks set player owns.
	 * @param game
	 * @return int : number of docks owned
	 */
	private int moveToDocks(Game game,int placeOfNearestDock, Player player) { //Måske ikke nødvendig hvis GameControlleren anvendes
			
//			for ( int i = 0; i<game.getPlayerAmount();i++) {
				
			
			int[] d = player.getOwnedProperties();
			
			for (int j = 0 ; j < d.length ; j++) {
				if(d[placeOfNearestDock]!=0) {
					return d[5]+ d[15] + d[25] + d[35];
				}
			}
			
//			}
			return 0; //Will access this return if a player does not own the dock

	}
}

			