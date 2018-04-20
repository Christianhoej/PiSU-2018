package board;
import java.awt.Color;

import model.Txt;
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
	String[] fieldName = Txt.file("FieldNames.txt");
	String[] fieldPrices = Txt.file("Prices.txt");
	
	public GUI_Field[] makeBoard() {
		System.out.println(fieldName.length);

		int i =0;
		fields[i++] = new GUI_Start(fieldName[0], "Modtag: 4000", "Modtag kr. 4000,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[1], fieldPrices[0], "Rødovrevej", "Leje:  50", new Color(75, 155, 225), Color.BLACK);
        fields[i++] = new GUI_Chance(fieldName[2],fieldName[3], "Tag et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[4], fieldPrices[1], "Hvidovrevej", "Leje:  50", new Color(75, 155, 225), Color.BLACK);
        fields[i++] = new GUI_Tax(fieldName[5], fieldName[6], "Betal indkomstskat\n10% eller kr. 4000,-", Color.GRAY, Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Helsingør - Helsingborg", fieldPrices[2], "Helsingør - Helsingborg", "Leje:  500", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[8], fieldPrices[3], "Roskildevej", "Leje:  100", new Color(255, 135, 120), Color.BLACK);
        fields[i++] = new GUI_Chance(fieldName[9], fieldName[10] , "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[11] , fieldPrices[4], "Valby Langgade", "Leje:  100", new Color(255, 135, 120), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName [12], fieldPrices[5], "Allégade", "Leje:  150", new Color(255, 135, 120), Color.BLACK);
        fields[i++] = new GUI_Jail("default", "Fængsel", "Fængsel", "På besøg i fængslet", new Color(125, 125, 125), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[14], fieldPrices[6], "Frederiksberg Allé", "Leje:  200", new Color(102, 204, 0), Color.BLACK);
        fields[i++] = new GUI_Brewery("default", "Coca-Cola", fieldPrices[7], "Coca-Cola", "200 x [Terningslag]", Color.BLACK, Color.WHITE);
        fields[i++] = new GUI_Street(fieldName[16], fieldPrices[8], "Bülowsvej", "Leje:  200", new Color(102, 204, 0), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[17], fieldPrices[9], "Gammel Kongevej", "Leje:  250", new Color(102, 204, 0), Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Mols Linjen", fieldPrices[10], "Mols Linjen", "Leje:  500", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[19], fieldPrices[11], "Bernstorffsvej", "Leje:  300", new Color(153, 153, 153), Color.BLACK);
        fields[i++] = new GUI_Chance(fieldName[20], fieldName[21], "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[22], fieldPrices[12], "Hellerupvej", "Leje:  300", new Color(153, 153, 153), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[23], fieldPrices[13], "Strandvejen", "Leje:  350", new Color(153, 153, 153), Color.BLACK);
        fields[i++] = new GUI_Refuge("default", "Parleromg", "Parkering", "Gør noget ved dine drømme", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[25], fieldPrices[14], "Trianglen", "Leje:  350", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance(fieldName[26], fieldName[27], "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[28], fieldPrices[15], "Østerbrogade", "Leje:  350", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[29], fieldPrices[16], "Grønningen", "Leje:  400", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Gedser-Rostock", fieldPrices[17], "Gedser-Rostock", "Leje:  500", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[31], fieldPrices[18], "Bredgade", "Leje:  450", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[32], fieldPrices[19], "Kongens Nytorv", "Leje:  450", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Brewery("default", "Faxe", fieldPrices[20], "Faxe Bryggeri", "200 x [Terningslag]", Color.BLACK, Color.WHITE);
        fields[i++] = new GUI_Street(fieldName[34], fieldPrices[21], "Østergade", "Leje:  500", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Jail("default", "Gå i fængsel", "Gå i fængsel", "De fængsles\nSlå to ens for at komme ud", new Color(125, 125, 125), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[36], fieldPrices[22], "Amagertorv", "Leje:  550", new Color(255, 255, 50), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[37], fieldPrices[23], "Vimmelskaftet", "Leje:  550", new Color(255, 255, 50), Color.BLACK);
        fields[i++] = new GUI_Chance(fieldName[38], fieldName[39], "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[40], fieldPrices[24], "Nygade", "Leje:  600", new Color(255, 255, 50), Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Rødby-Puttgarden", fieldPrices[25], "Rødby-Puttgarden", "Leje:  500", Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Prøv lykken", "Ta' et chancekort.", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[43], fieldPrices[26], "Frederiksberggade", "Leje:  700", new Color(150, 60, 150), Color.WHITE);
        fields[i++] = new GUI_Tax(fieldName[44], fieldName[45], "Betal ekstraordinær\nstatsskat: kr. 2000,-", Color.GRAY, Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[46], fieldPrices[27], "Rådhuspladsen", "Leje:  1000", new Color(150, 60, 150), Color.WHITE);
		return fields;
	}
}
