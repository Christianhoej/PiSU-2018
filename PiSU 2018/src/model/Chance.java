package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

public class Chance extends Fields {
	

	private String text;
	ChanceCard[] chanceCards;
	static String[] aL = Txt.file("CardMove.txt");
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
//		Chance chance = new Chance(1, "");
//		String file = "CardMove.txt";
//		List<String> aL= chance.fileLoad(file);
		
		for(int i = 0; aL.length> i ; i++) {
			System.out.println(aL.length);
		}
	}

	public Chance(int fieldNumber) {
		super(fieldNumber);
		
		int i = 0; //Styrer hvor i arrayet der skrives til og giver kortnummer
		//While txt.file. has nextLine - load from txt.
		//Opret objekt af given nedarvet type.
		i++;
		//end loop
		
		}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void landOnField(Game game) {
		//land on field
		
	}
	private ArrayList<String> fileLoad(String file) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader (file));
		String line = "";
		ArrayList<String> aList = new ArrayList<String>();
		int k = 0;
		while((line = reader.readLine()) != null) {
		aList.add(k, line);
		k++;
		}
		return aList;
	} 


}
