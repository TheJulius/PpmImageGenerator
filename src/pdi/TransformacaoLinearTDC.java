package pdi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; // Import the IOException class to handle errors
import java.util.Arrays;

public class TransformacaoLinearTDC {
	public static void main(String[] args) throws Exception {
		int imgData[] = new int[640004];
		int max = 0;
		int min = 0;
		int count = 0;

		File inputFile = new File("./Entrada_EscalaCinza.pgm");
		File outputFile = new File("./Saida_EscalaCinza_Transformacao_Linear.pgm");

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

		BufferedWriter writer2 = new BufferedWriter(new FileWriter(outputFile));
		BufferedReader reader2 = new BufferedReader(new FileReader(outputFile));

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
			while ((line = reader2.readLine()) != null) {
				count++;
				imgData[count] = Integer.parseInt(line);
			}

			for (int i = 0; i < imgData.length; i++) {

				if (imgData[i] > max) {
					max = imgData[i];
				}

				if (imgData[i] < min) {
					min = imgData[i];
				}
			}

			int a = 255 / (max - min);
			int b = -a * min;

			for (int i = 0; i < imgData.length; i++) {
				imgData[i] = a * imgData[i] + b;
			}

			String[] strArray = Arrays.stream(imgData).mapToObj(String::valueOf).toArray(String[]::new);

			writer2.write("P2\n800 800 \n255\n");
			for (int i = 0; i < strArray.length; i++) {
				writer2.write(strArray[i] + " ");
				writer2.write("\n");
			}

			System.out.println("Concluido!");
			System.out.println("Min: "+min);
			System.out.println("Max: "+max);
			System.out.println("A: "+a);
			System.out.println("B: "+b);
			writer2.close();
			reader2.close();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}