package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import controller.GameController;

public class Chance extends Fields {
	

	private String text;//Hvad er den til for?
	private int cardToDraw = 1;
	ChanceCard[] chanceCards = new ChanceCard[32];
	



	public Chance(int fieldNumber) {
		super(fieldNumber);

		
		String[] texts = Txt.file("CardText.txt");
		String[] prices = Txt.file("CardAmmounts.txt");
		
		
		//Adds the different chanceCards to the chanceCard[]
		for(int i = 0; i<chanceCards.length; i++){
			
			
			if(i<=10) 
				chanceCards[i] = new CardReceive(i+1,texts[i],Integer.parseInt(prices[i]));
				
			else if(i<=19) 
			    chanceCards[i] = new CardPay(i+1,texts[i],Integer.parseInt(prices[i]));			
					
			else if(i<=28)
				chanceCards[i] = new CardMove(i+1,texts[i],Integer.parseInt(prices[i]));

			else if (i<=30)
				chanceCards[i] = new CardGetOutOfPrison(i+1,texts[i]);
				
			else
				chanceCards[i] = new CardPrison(i+1,texts[i]);

				
			//Shuffle cards
		}
	}

	@Override
	public String toString() {
		return null;
	}


		@Override
	public void landOnField(GameController GameController, Player player) {
//		int i = chanceCards[cardToDraw].cardNumber;

		
//		if(i<=10) 
			chanceCards[cardToDraw].performAction(game);//Skal ændres
//		
//		else if(i<=19) 
//		    chanceCards[cardToDraw].performAction(i+1,texts[i],Integer.parseInt(prices[i]));			
//				
//		else if(i<=28)
//			chanceCards[cardToDraw].performAction(i+1,texts[i],Integer.parseInt(prices[i]));
//
//		else if (i<=30)
//			chanceCards[cardToDraw].performAction(i+1,texts[i]);
//			
//		else
//			chanceCards[cardToDraw].performAction(i+1,texts[i]);
//		
//		cardToDraw=(cardToDraw++)%32;
//		
	} 
	

}
