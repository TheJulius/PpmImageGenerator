package pdi;

import java.io.FileWriter;   // Import the FileWriter library
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Random; // Import the Random library

public class CemPorCem {
  public static void main(String[] args) {
	    
    try {
      FileWriter myWriter = new FileWriter("cemPorCem.ppm"); //creates the ppm file      
      Random rand = new Random(); //instance of random class
      
      System.out.println("Generate 100 x 100 PPM Image");
      myWriter.write(" P1 100 100 \n");
      
      for (int i = 0; i < 99; i++) {
    	  if (i==0) {
    		  myWriter.write("");
		} else {
			myWriter.write("\n");
		}
    	  for (int j = 0; j < 99; j++) {
    		  
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