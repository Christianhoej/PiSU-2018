package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.GameController;

public class Chance extends Fields {


	private Integer cardToDraw = 0;
	private List<ChanceCard> chanceCards = new ArrayList<ChanceCard>();
	private Map<Integer,ChanceCard> integer2ChanceCard = new HashMap<Integer,ChanceCard>();

	public Chance(int fieldNumber) {
		super(fieldNumber);


		String[] texts = Txt.fileString("CardText.txt");
		String[] prices = Txt.fileString("CardAmmounts.txt");


		//Adds the different chanceCards to the chanceCard[]
		for(Integer i = 0; i<32; i++){
			if(i<10) {
				chanceCards.add(new CardReceive(i+1,texts[i],Integer.parseInt(prices[i])));
				integer2ChanceCard.put(i, chanceCards.get(i));
			}
			else if(i<19) {
				chanceCards.add(new CardPay(i+1,texts[i],Integer.parseInt(prices[i])));	
				integer2ChanceCard.put(i, chanceCards.get(i));
			}
			else if(i<28) {
				chanceCards.add(new CardMove(i+1,texts[i],Integer.parseInt(prices[i])));
				integer2ChanceCard.put(i, chanceCards.get(i));
			}
			else if (i<30) {
				chanceCards.add(new CardGetOutOfPrison(i+1,texts[i]));
				integer2ChanceCard.put(i, chanceCards.get(i));
			}
			else {
				chanceCards.add(new CardPrison(i+1,texts[i]));
				integer2ChanceCard.put(i, chanceCards.get(i));
			}


		}
		//Shuffles the deck		
		Collections.shuffle(chanceCards);
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
		integer2ChanceCard.get(chanceCards.get(cardToDraw).getCardNumber()).performAction(gameController);
		cardToDraw++;
	} 
}
