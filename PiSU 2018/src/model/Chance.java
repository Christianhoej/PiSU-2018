package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.GameController;
import gui_fields.GUI_Field;

public class Chance extends Fields {


	private String text;//Hvad er den til for?
	private Integer cardToDraw = 0;
	ChanceCard[] chanceCards = new ChanceCard[32];
	private Map<Integer,ChanceCard> integer2ChanceCard = new HashMap<Integer,ChanceCard>();

	public Chance(int fieldNumber) {
		super(fieldNumber);


		String[] texts = Txt.fileString("CardText.txt");
		String[] prices = Txt.fileString("CardAmmounts.txt");


		//Adds the different chanceCards to the chanceCard[]
		for(Integer i = 0; i<chanceCards.length; i++){
			if(i<10) {
				chanceCards[i] = new CardReceive(i+1,texts[i],Integer.parseInt(prices[i]));
				integer2ChanceCard.put(i, chanceCards[i]);
				System.out.println();
			}
			else if(i<19) {
				chanceCards[i] = new CardPay(i+1,texts[i],Integer.parseInt(prices[i]));	
				integer2ChanceCard.put(i, chanceCards[i]);			
			}
			else if(i<28) {
				chanceCards[i] = new CardMove(i+1,texts[i],Integer.parseInt(prices[i]));
				integer2ChanceCard.put(i, chanceCards[i]);
			}
			else if (i<30) {
				chanceCards[i] = new CardGetOutOfPrison(i+1,texts[i]);
				integer2ChanceCard.put(i, chanceCards[i]);
			}
			else {
				chanceCards[i] = new CardPrison(i+1,texts[i]);
				integer2ChanceCard.put(i, chanceCards[i]);
			}

			//Shuffles the deck
			 
			 
//			 
		}
		chanceCards = ChanceCard.shuffleDeck(chanceCards);
		for(int j=0; j<chanceCards.length; j++) {
			System.out.println(j + ": " + chanceCards[j].toString());
		}
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


	@Override
	public void landOnField(GameController gameController) {
		
//		System.out.println(chanceCards[cardToDraw].getCardNumber());
//		System.out.println(chanceCards[cardToDraw].getCardNumber());
		
		integer2ChanceCard.get(chanceCards[cardToDraw].getCardNumber()).performAction(gameController);
//		System.out.println(chanceCards[cardToDraw].getCardNumber());
		cardToDraw++;
//		if(cardToDraw == 31)
//			cardToDraw =0;
//		//Evt lav shuffle en gang til.

	} 


}
