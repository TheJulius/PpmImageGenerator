package pdi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; // Import the IOException class to handle errors

public class TransformarRGBParaTDC {
	public static void main(String[] args) throws Exception {
		int imgData[] = new int[462240];
		int imgData2[] = new int[154110];

		File inputFile = new File("./Fig4.ppm");
		File outputFile = new File("./Saida_RGBEscalaCinza255.pgm");

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
				//System.out.println(line);

				String[] splited = line.split(" ");

				for (int i = 0; i < splited.length; i++) {
					imgData[i] = Integer.parseInt(splited[i]);
				}
				
				for (int i = 0; i < imgData2.length; i ++) {

					imgData2[i] = (imgData[i] + imgData[i + 1] + imgData[i + 2]) / 3;

				}
			}
			

			writer2.write("P2\n481 321 \n255\n");
			for (int i = 0; i < imgData2.length; i++) {
				writer2.write(imgData2[i]);			
				writer2.write("\n");
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}