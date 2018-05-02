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
	private int cardToDraw = 0;
	ChanceCard[] chanceCards = new ChanceCard[32];




	public Chance(int fieldNumber) {
		super(fieldNumber);


		String[] texts = Txt.fileString("CardText.txt");
		String[] prices = Txt.fileString("CardAmmounts.txt");


		//Adds the different chanceCards to the chanceCard[]
		for(int i = 0; i<chanceCards.length; i++){


			if(i<10) 
				chanceCards[i] = new CardReceive(i+1,texts[i],Integer.parseInt(prices[i]));

			else if(i<19) 
				chanceCards[i] = new CardPay(i+1,texts[i],Integer.parseInt(prices[i]));			

			else if(i<28)
				chanceCards[i] = new CardMove(i+1,texts[i],Integer.parseInt(prices[i]));

			else if (i<30)
				chanceCards[i] = new CardGetOutOfPrison(i+1,texts[i]);

			else
				chanceCards[i] = new CardPrison(i+1,texts[i]);


			//Shuffles the deck
			chanceCards = ChanceCard.shuffleDeck(chanceCards);
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

		chanceCards[cardToDraw].performAction(gameController);

		if(cardToDraw == 31)
			cardToDraw =0;
		//Evt lav shuffle en gang til.

	} 


}
