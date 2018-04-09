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
	public GUI_Field[] lavBr�t() {
		int i =0;
		fields[i++] = new GUI_Start("Start", "Modtag: 4000", "Modtag kr. 4000,-\nn�r de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("R�dovrevej", "Pris:  1200", "R�dovrevej", "Leje:  50", new Color(75, 155, 225), Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Pr�v lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street("Hvidovrevej", "Pris:  1200", "Hvidovrevej", "Leje:  50", new Color(75, 155, 225), Color.BLACK);
        fields[i++] = new GUI_Tax("Betal\nindkomst-\nskat", "10% el. 4000", "Betal indkomstskat\n10% eller kr. 4000,-", Color.GRAY, Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Lindinger", "Pris:  4000", "Rederiet Lindinger A/S", "Leje:  500", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street("Roskildevej", "Pris:  2000", "Roskildevej", "Leje:  100", new Color(255, 135, 120), Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Pr�v lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street("Valby\nLanggade", "Pris:  2000", "Valby Langgade", "Leje:  100", new Color(255, 135, 120), Color.BLACK);
        fields[i++] = new GUI_Street("All�gade", "Pris:  2400", "All�gade", "Leje:  150", new Color(255, 135, 120), Color.BLACK);
        fields[i++] = new GUI_Jail("default", "F�ngsel", "F�ngsel", "P� bes�g i f�ngslet", new Color(125, 125, 125), Color.BLACK);
        fields[i++] = new GUI_Street("Frederiks-\nberg All�", "Pris:  2800", "Frederiksberg All�", "Leje:  200", new Color(102, 204, 0), Color.BLACK);
        fields[i++] = new GUI_Brewery("default", "Coca-Cola", "Pris:  3000", "Coca-Cola", "200 x [Terningslag]", Color.BLACK, Color.WHITE);
        fields[i++] = new GUI_Street("B�lowsvej", "Pris:  2800", "B�lowsvej", "Leje:  200", new Color(102, 204, 0), Color.BLACK);
        fields[i++] = new GUI_Street("Gammel Kongevej", "Pris:  3200", "Gammel Kongevej", "Leje:  250", new Color(102, 204, 0), Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Grenaa-Hundested", "Pris:  4000", "Grenaa-Hundested", "Leje:  500", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street("Bernstorffsvej", "Pris:  3600", "Bernstorffsvej", "Leje:  300", new Color(153, 153, 153), Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Pr�v lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street("Hellerupvej", "Pris:  3600", "Hellerupvej", "Leje:  300", new Color(153, 153, 153), Color.BLACK);
        fields[i++] = new GUI_Street("Strandvejen", "Pris:  4000", "Strandvejen", "Leje:  350", new Color(153, 153, 153), Color.BLACK);
        fields[i++] = new GUI_Refuge("default", "Parleromg", "Parkering", "G�r noget ved dine dr�mme", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street("Trianglen", "Pris:  4400", "Trianglen", "Leje:  350", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Pr�v lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street("�sterbro-\ngade", "Pris:  4400", "�sterbrogade", "Leje:  350", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("Gr�nningen", "Pris:  4800", "Gr�nningen", "Leje:  400", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Mols-Linien", "Pris:  4000", "Mols-Linien A/S", "Leje:  500", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street("Bredgade", "Pris:  5200", "Bredgade", "Leje:  450", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street("Kgs. Nytorv", "Pris:  5200", "Kongens Nytorv", "Leje:  450", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Brewery("default", "Faxe", "Pris:  3000", "Faxe Bryggeri", "200 x [Terningslag]", Color.BLACK, Color.WHITE);
        fields[i++] = new GUI_Street("�stergade", "Pris:  5600", "�stergade", "Leje:  500", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Jail("default", "G� i f�ngsel", "G� i f�ngsel", "De f�ngsles\nSl� to ens for at komme ud", new Color(125, 125, 125), Color.BLACK);
        fields[i++] = new GUI_Street("Amagertorv", "Pris:  6000", "Amagertorv", "Leje:  550", new Color(255, 255, 50), Color.BLACK);
        fields[i++] = new GUI_Street("Vimmel-\nskaftet", "Pris:  6000", "Vimmelskaftet", "Leje:  550", new Color(255, 255, 50), Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Pr�v lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street("Nygade", "Pris:  6400", "Nygade", "Leje:  600", new Color(255, 255, 50), Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Skandinavisk Linietrafik", "Pris:  4000", "Skandinavisk Linietrafik", "Leje:  500", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Pr�v lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street("Frederiks-\nberggade", "Pris:  7000", "Frederiksberggade", "Leje:  700", new Color(150, 60, 150), Color.WHITE);
        fields[i++] = new GUI_Tax("Ekstra-\nordin�r\nstatsskat", "Betal 2000", "Betal ekstraordin�r\nstatsskat: kr. 2000,-", Color.GRAY, Color.BLACK);
        fields[i++] = new GUI_Street("R�dhuspladsen", "Pris:  8000", "R�dhuspladsen", "Leje:  1000", new Color(150, 60, 150), Color.WHITE);
		return fields;
	}
}
