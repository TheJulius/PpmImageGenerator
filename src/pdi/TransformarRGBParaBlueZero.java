package pdi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; // Import the IOException class to handle errors
import java.util.Arrays;

public class TransformarRGBParaBlueZero {
	public static void main(String[] args) throws Exception {
		int imgData[] = new int[4622500];
		int imgData4[] = new int[4622500];
		
		int count=0;

		File inputFile = new File("./Fig4.ppm");
		File outputFile = new File("./Saida_Blue_0.ppm");

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		
		BufferedWriter writer2 = new BufferedWriter(new FileWriter(outputFile));
		BufferedReader reader2 = new BufferedReader(new FileReader(outputFile));

		try (reader) {

			String lineToRemove1 = "P3";
			String lineToRemove2 = "481 321";
			String lineToRemove3 = "255";

			String currentLine;

			while ((currentLine = reader.readLine()) != null) {
				// trim newline when comparing with lineToRemove
				String trimmedLine = currentLine.trim();
				if (trimmedLine.equals(lineToRemove1))
					continue;
				if (trimmedLine.equals(lineToRemove2))
					continue;
				if (trimmedLine.equals(lineToRemove3))
					continue;
				writer.write(currentLine + System.getProperty("line.separator"));
			}

			writer.close();
			reader.close();

			outputFile.renameTo(inputFile);

			String line;

			while ((line = reader2.readLine()) != null) {

				String[] splited = line.split(" ");
				
				for (int i = 0; i < splited.length; i++) {
					imgData[count] = Integer.parseInt(splited[i]);
					count++;
				}
			}
			
			for (int i = 0; i < imgData.length; i ++) {
				
				//BLUE
				if (i % 3 == 2 ) {
					imgData4[i] = imgData[i];
					if (imgData4[i]>0) {
						imgData4[i-1] = 0;
						imgData4[i+1] = 0;	
					}
				}
			}
			
			String[] strArray = Arrays.stream(imgData4)
                    .mapToObj(String::valueOf)
                    .toArray(String[]::new);
			
			writer2.write("P3\n481 321 \n255\n");
			for (int i = 0; i < strArray.length; i++) {
				writer2.write(strArray[i] + " ");
				if (i % 24 == 23) {
					writer2.write("\n");
				}
			}
			
			System.out.println("Concluido!");
			writer2.close();
			reader2.close();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}