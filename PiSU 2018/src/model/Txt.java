package model;
/** @author Yoss & Gunn
 * Dele af denne kode er genbrugt fra CDIO3
 * Dette er en klasse Txt, som henter vores tekstfiler, eksempelvis fieldName.txt 
 * og gør det muligt at bruge den i koden. 
 * Dette gør koden mere overskue, og undgår at bogstaver som æ, ø og å bliver "knækket"
 * 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Txt {

	public static String[] file(String fileName) {

		ArrayList<String> currentLine = new ArrayList<String>();

/** Anvender 'try' i det tilfælde at der skulle opstå en fejl med tekstlæseren. Vil der være en fejl, crasher programmet ikke
* men udprinter i stedet "error".
* 
*/
		try { 
			BufferedReader reader1 = new BufferedReader(new FileReader(fileName));
			String line = "\r\n"; 
			while ((line = reader1.readLine()) != null) {
				currentLine.add(line);
			}
/**
 * Catch funktionen bruges for at spillet ikke crasher, hvis programmet ikke kan aflæse en linje fra while-loopet. 
 * Den udprinter i stedet "error" og kører herefter spillet videre efterfølgende. 
 * Ved 'finally' udføres når 'try' er kørt færdig, eller hvis der findes et catch
 */
			
			reader1.close();
		} catch (IOException e) { 
			System.out.println("error"); 
		} finally {  
			System.out.println(fileName + " read");
		}
		String[] line1 = new String[currentLine.size()];
		line1 = currentLine.toArray(line1); 

		return line1;
	}	
	
	
	public static int[][] file2D(String fileName) {

		ArrayList<String[]> lines = new ArrayList<>();
		
		try {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		while((line = reader.readLine()) != null) {
			lines.add(line.split("\\s+"));
		}
		reader.close();
		} catch (IOException e) {
			System.out.println("Not able to read file");
		} finally {
			System.out.println(fileName + " read");
		}

		// Convert to a String[][]
		String[][] line1 = new String[lines.size()][];
		for(int i=0; i<lines.size(); i++) {
			
			line1[i] = lines.get(i).clone();
		}
		
		int[][] intArray = new int[lines.size()][];
		for(int i = 0 ; i<lines.size() ; i++) {
			intArray[i] = new int[lines.get(i).length];
			for(int j = 0; j<lines.get(i).length; j++) {
				intArray[i][j] = Integer.parseInt(line1[i][j]);
			}
			
		}
		
		
		return intArray;
	}
}

