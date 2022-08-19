package pdi;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MilPorMil {
	public static void main(String[] args) {
	 try {
	      FileWriter myWriter = new FileWriter("milPorMil.ppm");
	      myWriter.write(" P1 1000 1000 \n");
	      
	      Random rand = new Random(); //instance of random class
	      
	      System.out.println("Generate 1000 x 1000 PPM Image");
	      
	      for (int i = 0; i < 999; i++) {
	    	  if (i==0) {
	    		  myWriter.write("");
			} else {
				myWriter.write("\n");
			}
	    	  for (int j = 0; j < 999; j++) {
	    		  
	    		  int upperbound = 2;
	    	      int int_random = rand.nextInt(upperbound);
	    	      
	    	      String random_converted = Integer.toString(int_random);
	    		  myWriter.write(random_converted);
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
