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

public class Gameboard2 {

	private GUI_Field[] fields = new GUI_Field[40];	
	String[] fieldName = Txt.file("FieldNames.txt");
	String[] fieldPrices = Txt.file("Prices.txt");
	String[] fieldMessage = Txt.file("fieldMessage.txt");
	
	public GUI_Field[] makeBoard() {
		
		System.out.println(fieldName.length);
		System.out.println(fieldPrices.length);
		System.out.println(fieldMessage.length);
		int i =7;
		fields[0] = new GUI_Start("Hey", "Gunn", fieldMessage[1], Color.RED, Color.BLACK);
        fields[1] = new GUI_Street(fieldName[1], fieldPrices[0], fieldName[1], fieldMessage[2], new Color(75, 155, 225), Color.BLACK);
        fields[2] = new GUI_Chance(fieldName[2],fieldName[3], fieldMessage[3], new Color(204, 204, 204), Color.BLACK);
        fields[3] = new GUI_Street(fieldName[4], fieldPrices[1], fieldName[4], fieldMessage[4], new Color(75, 155, 225), Color.BLACK);
        fields[4] = new GUI_Tax(fieldName[5], fieldName[6], fieldMessage[5], Color.GRAY, Color.BLACK);
        fields[5] = new GUI_Shipping("default", "Helsingør - Helsingborg", fieldPrices[2], "Helsingør - Helsingborg", fieldMessage[6], Color.WHITE, Color.BLACK);
        fields[6] = new GUI_Street(fieldName[8], fieldPrices[3], fieldName[8], fieldMessage[7], new Color(255, 135, 120), Color.BLACK);
        fields[i++] = new GUI_Chance(fieldName[9], fieldName[10] , "Yoss", new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[11] , fieldPrices[4], fieldName[11], fieldMessage[7], new Color(255, 135, 120), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName [12], fieldPrices[5], fieldName[12], fieldMessage[8], new Color(255, 135, 120), Color.BLACK);
        fields[i++] = new GUI_Jail("default", "Fængsel", "Fængsel", fieldMessage[9], new Color(125, 125, 125), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[14], fieldPrices[6], fieldName[14], fieldMessage[10], new Color(102, 204, 0), Color.BLACK);
        fields[i++] = new GUI_Brewery("default", "Coca-Cola", fieldPrices[7], "Coca-Cola", "200 x [Terningslag]", Color.BLACK, Color.WHITE);
        fields[i++] = new GUI_Street(fieldName[16], fieldPrices[8], fieldName[16], fieldMessage[12], new Color(102, 204, 0), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[17], fieldPrices[9], fieldName[17], fieldMessage[13], new Color(102, 204, 0), Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Mols Linjen", fieldPrices[10], "Mols Linjen", fieldMessage[14], Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[19], fieldPrices[11], fieldName[19], fieldMessage[15], new Color(153, 153, 153), Color.BLACK);
        fields[i++] = new GUI_Chance(fieldName[20], fieldName[21], fieldMessage[16], new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[22], fieldPrices[12], fieldName[22], fieldMessage[17], new Color(153, 153, 153), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[23], fieldPrices[13], fieldName[23], fieldMessage[18], new Color(153, 153, 153), Color.BLACK);
        fields[i++] = new GUI_Refuge("default", "Parleromg", "Parkering", fieldMessage[19], Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[25], fieldPrices[14], fieldName[25], fieldMessage[20], Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance(fieldName[26], fieldName[27], fieldMessage[21], new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[28], fieldPrices[15], fieldName[28], fieldMessage[22], Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[29], fieldPrices[16], fieldName[29], fieldMessage[23], Color.RED, Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Gedser-Rostock", fieldPrices[17], "Gedser-Rostock", fieldMessage[24], Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[31], fieldPrices[18], fieldName[31], fieldMessage[25], Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[32], fieldPrices[19], fieldMessage[32], fieldMessage[26], Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Brewery("default", "Faxe", fieldPrices[20], "Faxe Bryggeri", fieldMessage[27], Color.BLACK, Color.WHITE);
        fields[i++] = new GUI_Street(fieldName[34], fieldPrices[21], fieldName[34], fieldMessage[24], Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Jail("default", "Gå i fængsel", "Gå i fængsel", "De fængsles\nSlå to ens for at komme ud", new Color(125, 125, 125), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[36], fieldPrices[22], fieldName[36], fieldMessage[28], new Color(255, 255, 50), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[37], fieldPrices[23], fieldName[37], fieldMessage[29], new Color(255, 255, 50), Color.BLACK);
        fields[i++] = new GUI_Chance(fieldName[38], fieldName[39], fieldMessage[30], new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[40], fieldPrices[24], fieldName[40], fieldMessage[31], new Color(255, 255, 50), Color.BLACK);
        fields[i++] = new GUI_Shipping("default", "Rødby-Puttgarden", fieldPrices[25], "Rødby-Puttgarden", fieldMessage[32], Color.WHITE, Color.BLACK);
        fields[i++] = new GUI_Chance(fieldName[42], fieldName[43], fieldMessage[33], new Color(204, 204, 204), Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[43], fieldPrices[26], fieldName[43], fieldMessage[34], new Color(150, 60, 150), Color.WHITE);
        fields[i++] = new GUI_Tax(fieldName[44], fieldName[45], fieldMessage[35], Color.GRAY, Color.BLACK);
        fields[i++] = new GUI_Street(fieldName[46], fieldPrices[26], fieldName[46], fieldMessage[36], new Color(150, 60, 150), Color.WHITE);
		return fields;
	}
}
