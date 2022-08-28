package pdi;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MilPorMilRGB {
	public static void main(String[] args) {
		try {
			FileWriter myWriter = new FileWriter("milPorMilRGB.ppm"); // creates the ppm file
			Random rand = new Random(); // instance of random class

			System.out.println("Generate 1000 x 1000 PPM RGB Image");
			myWriter.write(" P3 1000 1000 \n 255 \n");

			for (int i = 0; i < 999; i++) {
				if (i == 0) {
					myWriter.write("");
				} else {
					myWriter.write("\n");
				}
				for (int j = 0; j < 999; j++) {

					for (int j2 = 0; j2 < 3; j2++) {
						int upperbound = 255; // limit the random to 0-255
						int int_random = rand.nextInt(upperbound); // generates the random number

						String random_converted = Integer.toString(int_random);// necessary conversion to write into the
																				// file
						myWriter.write(random_converted + " ");

					}
				}
			}
			myWriter.close();
			System.out.println("Successfully wrote to the file.");

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
