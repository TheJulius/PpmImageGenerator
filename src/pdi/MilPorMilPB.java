package pdi;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MilPorMilPB {
	public static void main(String[] args) {
	 try {
	      FileWriter myWriter = new FileWriter("milPorMilPB.ppm"); //creates the ppm file
	      Random rand = new Random(); //instance of random class
	      
	      System.out.println("Generate 1000 x 1000 PPM Black and White Image");
	      myWriter.write(" P1 1000 1000 \n");
	      
	      for (int i = 0; i < 999; i++) {
	    	  if (i==0) {
	    		  myWriter.write("");
			} else {
				myWriter.write("\n");
			}
	    	  for (int j = 0; j < 999; j++) {
	    		  
	    		  int upperbound = 2; //limit the random to 0-1
	    	      int int_random = rand.nextInt(upperbound); //generates the random number
	    	      
	    	      String random_converted = Integer.toString(int_random);//necessary conversion to write into the file
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
