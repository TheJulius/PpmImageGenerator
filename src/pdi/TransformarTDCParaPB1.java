package pdi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; // Import the IOException class to handle errors

public class TransformarTDCParaPB1 {
	public static void main(String[] args) throws Exception {
		int imgData[];
		int count = 2;
		imgData = new int[640004];

		File inputFile = new File("./Entrada_EscalaCinza.pgm");
		File outputFile = new File("./Saida_EscalaCinzaPB1.pgm");

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

		try (reader) {

			String lineToRemove1 = "P2";
			String lineToRemove2 = "800 800";
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
			
			BufferedReader reader2 = new BufferedReader(new FileReader(outputFile));

			while ((line = reader2.readLine()) != null) {
				count++;
				imgData[count] = Integer.parseInt(line);
			}

			for (int i = 0; i < imgData.length; i++) {
				if (imgData[i] < 128) {
					imgData[i] = 0;
				} else {
					imgData[i] = 1;
				}
			}

			imgData.toString();
			
			reader2.close();
			BufferedWriter writer2 = new BufferedWriter(new FileWriter(outputFile));
			
			writer2.write("P2\n800 800 \n1\n");
			for (int i = 0; i < imgData.length; i++) {
				writer2.write(imgData[i]+"\n");
			}
			
			writer2.close();
			System.out.println("Concluido!");

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}