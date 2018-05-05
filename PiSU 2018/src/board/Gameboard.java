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
import gui_fields.GUI_Ownable;

public class Gameboard {

	private GUI_Field[] fields = new GUI_Field[40];	
	private String[][] fieldName = Txt.fileString2D("FieldNames.txt");
	int[][] fieldPrices = Txt.fileInt2D("Prices.txt");
	private String[][] fieldMessage = Txt.fileString2D("fieldMessage.txt");
	private String[] message = {"Pris: ", "100 x [Terningslag]"};
	
	public GUI_Field[] makeBoard() {
		
		fields[0] = new GUI_Start(	fieldName[0][0],
									fieldName[0][0], 
									fieldMessage[1][0], 
									Color.RED, Color.BLACK);
        fields[1] = new GUI_Street(	fieldName[1][0], message[0] +
        		Integer.toString(	fieldPrices[1][0]), 
        							fieldName[1][0], 
        							fieldMessage[2][0], 
        							new Color(75, 155, 225), Color.BLACK);
        ((GUI_Ownable) fields[1]).setOwnerName("");
        fields[2] = new GUI_Chance(	fieldName[2][0],
        							fieldName[2][1], 
        							fieldMessage[3][0], 
        							new Color(204, 204, 204), Color.BLACK);
        fields[3] = new GUI_Street(	fieldName[3][0], message[0] +
        		Integer.toString(	fieldPrices[3][0]), 
        							fieldName[3][0], 
        							fieldMessage[4][0], 
        							new Color(75, 155, 225), Color.BLACK);
        ((GUI_Ownable) fields[3]).setOwnerName("");
        fields[4] = new GUI_Tax(	fieldName[4][0], 
        							fieldName[4][1], 
        							fieldMessage[5][0], 
        							Color.GRAY, Color.BLACK);
        fields[5] = new GUI_Shipping(fieldName[5][0], 
        							fieldName[5][1], message[0] +
        		Integer.toString(	fieldPrices[5][0]), 
        							fieldName[5][1], 
        							fieldMessage[6][0], Color.WHITE, Color.BLACK);
        ((GUI_Ownable) fields[5]).setOwnerName("");
        fields[6] = new GUI_Street(	fieldName[6][0], message[0] +
        		Integer.toString(	fieldPrices[6][0]), 
        							fieldName[6][0], 
        							fieldMessage[7][0], 
        							new Color(255, 135, 120), Color.BLACK);
        ((GUI_Ownable) fields[6]).setOwnerName("");
        fields[7] = new GUI_Chance(	fieldName[7][0], 
        							fieldName[7][1], 
        							fieldMessage[3][0], 
        							new Color(204, 204, 204), Color.BLACK);
        fields[8] = new GUI_Street(	fieldName[8][0], message[0] +
        		Integer.toString(	fieldPrices[8][0]), 
        							fieldName[8][0], 
        							fieldMessage[7][0], 
        							new Color(255, 135, 120), Color.BLACK);
        ((GUI_Ownable) fields[8]).setOwnerName("");
        fields[9] = new GUI_Street(	fieldName[9][0], message[0] +
        		Integer.toString(	fieldPrices[9][0]), 
        							fieldName[9][0], 
        							fieldMessage[8][0], 
        							new Color(255, 135, 120), Color.BLACK);
        ((GUI_Ownable) fields[9]).setOwnerName("");
        fields[10] = new GUI_Jail(	fieldName[10][0],
        							fieldName[10][1], 
        							fieldName[10][1], 
        							fieldMessage[9][0], 
        							new Color(125, 125, 125), 
        							Color.BLACK);
        fields[11] = new GUI_Street(fieldName[11][0], message[0] +
        		Integer.toString(	fieldPrices[11][0]), 
        							fieldName[11][0], 
        							fieldMessage[10][0], 
        							new Color(102, 204, 0), 
        							Color.BLACK);
        ((GUI_Ownable) fields[11]).setOwnerName("");
        fields[12] = new GUI_Brewery(fieldName[12][0], 
        							fieldName[12][1], message[0] +
        		Integer.toString(	fieldPrices[12][0]), 
        							fieldName[12][1], 
        							message[1], 
        							Color.BLACK, Color.WHITE) ;
        ((GUI_Ownable) fields[12]).setOwnerName("");
        fields[13] = new GUI_Street(fieldName[13][0], message[0] +
        		Integer.toString(	fieldPrices[13][0]), 
        							fieldName[13][0], 
        							fieldMessage[12][0], 
        							new Color(102, 204, 0), 
        							Color.BLACK);
        ((GUI_Ownable) fields[13]).setOwnerName("");
        fields[14] = new GUI_Street(fieldName[14][0], message[0] +
        		Integer.toString(	fieldPrices[14][0]), 
        							fieldName[14][0], 
        							fieldMessage[13][0], 
        							new Color(102, 204, 0), 
        							Color.BLACK);
        ((GUI_Ownable) fields[14]).setOwnerName("");
        fields[15] = new GUI_Shipping(fieldName[15][0], 
        							fieldName[15][1], message[0] +
        		Integer.toString(	fieldPrices[15][0]), 
        							fieldName[15][1], 
        							fieldMessage[14][0], 
        							Color.WHITE, Color.BLACK);
        ((GUI_Ownable) fields[15]).setOwnerName("");
        fields[16] = new GUI_Street(fieldName[16][0], message[0] +
        		Integer.toString(	fieldPrices[16][0]), 
        							fieldName[16][0], 
        							fieldMessage[15][0], 
        							new Color(153, 153, 153), 
        							Color.BLACK);
        ((GUI_Ownable) fields[16]).setOwnerName("");
        fields[17] = new GUI_Chance(fieldName[17][0], 
        							fieldName[17][1], 
        							fieldMessage[16][0], 
        							new Color(204, 204, 204), Color.BLACK);
        fields[18] = new GUI_Street(fieldName[18][0], message[0] + 
        		Integer.toString(	fieldPrices[18][0]), 
        							fieldName[18][0], 
        							fieldMessage[17][0], 
        							new Color(153, 153, 153), Color.BLACK);
        ((GUI_Ownable) fields[18]).setOwnerName("");
        fields[19] = new GUI_Street(fieldName[19][0], message[0] +
        		Integer.toString(	fieldPrices[19][0]), 
        							fieldName[19][0], 
        							fieldMessage[18][0], 
        							new Color(153, 153, 153), Color.BLACK);
        ((GUI_Ownable) fields[19]).setOwnerName("");
        fields[20] = new GUI_Refuge(fieldName[20][0], 
        							fieldName[20][1], 
        							fieldName[20][1], 
        							fieldMessage[19][0], 
        							Color.WHITE, Color.BLACK);
        fields[21] = new GUI_Street(fieldName[21][0], message[0] +
        		Integer.toString(	fieldPrices[21][0]),
        							fieldName[21][0], 
        							fieldMessage[20][0], 
        							Color.RED, Color.BLACK);
        ((GUI_Ownable) fields[21]).setOwnerName("");
        fields[22] = new GUI_Chance(fieldName[22][0], 
        							fieldName[22][1], 
        							fieldMessage[21][0], 
        							new Color(204, 204, 204), 
        							Color.BLACK);
        fields[23] = new GUI_Street(fieldName[23][0], message[0] +
        		Integer.toString(	fieldPrices[23][0]), 
        							fieldName[23][0], 
        							fieldMessage[22][0], 
        							Color.RED, Color.BLACK);
        ((GUI_Ownable) fields[23]).setOwnerName("");
        fields[24] = new GUI_Street(fieldName[24][0], message[0] +
        		Integer.toString(	fieldPrices[24][0]), 
        							fieldName[24][0], 
        							fieldMessage[23][0], 
        							Color.RED, Color.BLACK);
        ((GUI_Ownable) fields[24]).setOwnerName("");
        fields[25] = new GUI_Shipping(fieldName[25][0], 
        							fieldName[25][1], message[0] +
        		Integer.toString(	fieldPrices[25][0]), 
        							fieldName[25][1], 
        							fieldMessage[24][0], 
        							Color.WHITE, Color.BLACK);
        ((GUI_Ownable) fields[25]).setOwnerName("");
        fields[26] = new GUI_Street(fieldName[26][0], message[0] +
        		Integer.toString(	fieldPrices[26][0]), 
        							fieldName[26][0], 
        							fieldMessage[25][0], Color.WHITE, Color.BLACK);
        ((GUI_Ownable) fields[26]).setOwnerName("");
        fields[27] = new GUI_Street(fieldName[27][0], message[0] +
        		Integer.toString(	fieldPrices[27][0]),
        							fieldMessage[32][0], 
        							fieldMessage[26][0], Color.WHITE, Color.BLACK);
        ((GUI_Ownable) fields[27]).setOwnerName("");
        fields[28] = new GUI_Brewery(fieldName[28][0], 
        							fieldName[28][1], message[0] +
        		Integer.toString(	fieldPrices[28][0]), 
        							fieldName[28][2], 
        							fieldMessage[27][0], Color.BLACK, Color.WHITE);
        ((GUI_Ownable) fields[28]).setOwnerName("");
        fields[29] = new GUI_Street(fieldName[29][0], message[0] +
        		Integer.toString(	fieldPrices[29][0]), 
        							fieldName[29][0], 
        							fieldMessage[24][0], Color.WHITE, Color.BLACK);
        ((GUI_Ownable) fields[29]).setOwnerName("");
        fields[30] = new GUI_Jail(fieldName[30][0], 
        							fieldName[30][1], 
        							fieldName[30][2], 
        							fieldName[30][2], 
        							new Color(125, 125, 125), Color.BLACK);
        fields[31] = new GUI_Street(fieldName[31][0], message[0] +
        		Integer.toString(	fieldPrices[31][0]), 
        							fieldName[31][0], 
        							fieldMessage[31][0], new Color(255, 255, 50), Color.BLACK);
        ((GUI_Ownable) fields[31]).setOwnerName("");
        fields[32] = new GUI_Street(fieldName[32][0], message[0] +
        		Integer.toString(	fieldPrices[32][0]), 
        							fieldName[32][0], 
        							fieldMessage[32][0], new Color(255, 255, 50), Color.BLACK);
//        ((GUI_Ownable) fields[32]).setOwnerName("");
        fields[33] = new GUI_Chance(fieldName[33][0], 
        							fieldName[33][1], 
        							fieldMessage[30][0], new Color(204, 204, 204), Color.BLACK);
        fields[34] = new GUI_Street(fieldName[34][0], message[0] +
        		Integer.toString(	fieldPrices[34][0]), 
        							fieldName[34][0], 
        							fieldMessage[34][0], 
        							new Color(255, 255, 50), Color.BLACK);
//        ((GUI_Ownable) fields[34]).setOwnerName("");
        fields[35] = new GUI_Shipping(fieldName[35][0],
        							fieldName[35][1], message[0] +
        		Integer.toString(	fieldPrices[35][0]), 
        							fieldName[35][1], 
        							fieldMessage[32][0], Color.WHITE, Color.BLACK);
//        ((GUI_Ownable) fields[35]).setOwnerName("");
        fields[36] = new GUI_Chance(fieldName[36][0],
        							fieldName[36][1], 
        							fieldMessage[33][0], new Color(204, 204, 204), Color.BLACK);
        fields[37] = new GUI_Street(fieldName[37][0], message[0] +
        		Integer.toString(	fieldPrices[37][0]),
        							fieldName[37][0], 
        							fieldMessage[34][0], new Color(150, 60, 150), Color.WHITE);
//        ((GUI_Ownable) fields[37]).setOwnerName("");
        fields[38] = new GUI_Tax(	fieldName[38][0], 
        							fieldName[38][1], 
        							fieldMessage[35][0], Color.GRAY, Color.BLACK);
        fields[39] = new GUI_Street(fieldName[39][0], message[0] +
        		Integer.toString(	fieldPrices[39][0]), 
        							fieldName[39][0], 
        							fieldMessage[36][0], new Color(150, 60, 150), Color.WHITE);
//        ((GUI_Ownable) fields[39]).setOwnerName("");
        
        for(int i = 0; i<fields.length; i++) {
        	if(fields[i] instanceof GUI_Ownable) {
        		((GUI_Ownable) fields[i]).setOwnerName("");
        		((GUI_Ownable) fields[i]).setOwnableLabel("");
        	}
        }
        
        
		return fields;
	}
}
