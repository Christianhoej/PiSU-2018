package board;
import java.awt.Color;

import gui_fields.GUI_Brewery;
import gui_fields.GUI_Chance;
import gui_fields.GUI_Field;
import gui_fields.GUI_Jail;
import gui_fields.GUI_Refuge;
import gui_fields.GUI_Shipping;
import gui_fields.GUI_Start;
import gui_fields.GUI_Street;
import gui_fields.GUI_Tax;

public class Gameboard {

	private GUI_Field[] fields = new GUI_Field[40];	
	public GUI_Field[] lavBræt() {
		int i =0;
		fields[i++] = new GUI_Start("Start", "Modtag: 4000", "Modtag kr. 4000,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("Rødovrevej", "Pris:  1200", "Rødovrevej", "Leje:  50", new Color(75, 155, 225), Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street("Hvidovrevej", "Pris:  1200", "Hvidovrevej", "Leje:  50", new Color(75, 155, 225), Color.BLACK);
        fields[i++] = new GUI_Tax("Betal\nindkomst-\nskat", "10% el. 4000", "Betal indkomstskat\n10% eller kr. 4000,-", Color.GRAY, Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Lindinger", "Pris:  4000", "Rederiet Lindinger A/S", "Leje:  500", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street("Roskildevej", "Pris:  2000", "Roskildevej", "Leje:  100", new Color(255, 135, 120), Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street("Valby\nLanggade", "Pris:  2000", "Valby Langgade", "Leje:  100", new Color(255, 135, 120), Color.BLACK);
        fields[i++] = new GUI_Street("Allégade", "Pris:  2400", "Allégade", "Leje:  150", new Color(255, 135, 120), Color.BLACK);
        fields[i++] = new GUI_Jail("default", "Fængsel", "Fængsel", "På besøg i fængslet", new Color(125, 125, 125), Color.BLACK);
        fields[i++] = new GUI_Street("Frederiks-\nberg Allé", "Pris:  2800", "Frederiksberg Allé", "Leje:  200", new Color(102, 204, 0), Color.BLACK);
        fields[i++] = new GUI_Brewery("default", "Coca-Cola", "Pris:  3000", "Coca-Cola", "200 x [Terningslag]", Color.BLACK, Color.WHITE);
        fields[i++] = new GUI_Street("Bülowsvej", "Pris:  2800", "Bülowsvej", "Leje:  200", new Color(102, 204, 0), Color.BLACK);
        fields[i++] = new GUI_Street("Gammel Kongevej", "Pris:  3200", "Gammel Kongevej", "Leje:  250", new Color(102, 204, 0), Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Grenaa-Hundested", "Pris:  4000", "Grenaa-Hundested", "Leje:  500", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street("Bernstorffsvej", "Pris:  3600", "Bernstorffsvej", "Leje:  300", new Color(153, 153, 153), Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street("Hellerupvej", "Pris:  3600", "Hellerupvej", "Leje:  300", new Color(153, 153, 153), Color.BLACK);
        fields[i++] = new GUI_Street("Strandvejen", "Pris:  4000", "Strandvejen", "Leje:  350", new Color(153, 153, 153), Color.BLACK);
        fields[i++] = new GUI_Refuge("default", "Parleromg", "Parkering", "Gør noget ved dine drømme", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street("Trianglen", "Pris:  4400", "Trianglen", "Leje:  350", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street("Østerbro-\ngade", "Pris:  4400", "Østerbrogade", "Leje:  350", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("Grønningen", "Pris:  4800", "Grønningen", "Leje:  400", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Mols-Linien", "Pris:  4000", "Mols-Linien A/S", "Leje:  500", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street("Bredgade", "Pris:  5200", "Bredgade", "Leje:  450", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street("Kgs. Nytorv", "Pris:  5200", "Kongens Nytorv", "Leje:  450", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Brewery("default", "Faxe", "Pris:  3000", "Faxe Bryggeri", "200 x [Terningslag]", Color.BLACK, Color.WHITE);
        fields[i++] = new GUI_Street("Østergade", "Pris:  5600", "Østergade", "Leje:  500", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Jail("default", "Gå i fængsel", "Gå i fængsel", "De fængsles\nSlå to ens for at komme ud", new Color(125, 125, 125), Color.BLACK);
        fields[i++] = new GUI_Street("Amagertorv", "Pris:  6000", "Amagertorv", "Leje:  550", new Color(255, 255, 50), Color.BLACK);
        fields[i++] = new GUI_Street("Vimmel-\nskaftet", "Pris:  6000", "Vimmelskaftet", "Leje:  550", new Color(255, 255, 50), Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street("Nygade", "Pris:  6400", "Nygade", "Leje:  600", new Color(255, 255, 50), Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Skandinavisk Linietrafik", "Pris:  4000", "Skandinavisk Linietrafik", "Leje:  500", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street("Frederiks-\nberggade", "Pris:  7000", "Frederiksberggade", "Leje:  700", new Color(150, 60, 150), Color.WHITE);
        fields[i++] = new GUI_Tax("Ekstra-\nordinær\nstatsskat", "Betal 2000", "Betal ekstraordinær\nstatsskat: kr. 2000,-", Color.GRAY, Color.BLACK);
        fields[i++] = new GUI_Street("Rådhuspladsen", "Pris:  8000", "Rådhuspladsen", "Leje:  1000", new Color(150, 60, 150), Color.WHITE);
		return fields;
	}
}
