package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Txt {

	public static String[] file(String fileName) {

		ArrayList<String> currentLine = new ArrayList<String>();

		try { //hvis der skulle opstå en fejl med tekstlæseren, vil programmet ikke crashe, men istedet udprinte 'error'
			BufferedReader reader1 = new BufferedReader(new FileReader(fileName));
			String line = "\r\n"; // næste linje
			while ((line = reader1.readLine()) != null) {
				currentLine.add(line);
			}

			reader1.close();
		} catch (IOException e) { // catch bruges for at spillet ikke crasher, når den ikke kan aflæse en linje fra while-loopet. 
			System.out.println("error"); // kører spillet videre efterfølgende 
		} finally { // bliver udført enten når try er kørt færdig eller når der er fundet et catch. 
			System.out.println(fileName + " read");
		}
		String[] line1 = new String[currentLine.size()];
		line1 = currentLine.toArray(line1); 

		return line1;
	}	
}

