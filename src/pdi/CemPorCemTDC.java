package pdi;

import java.io.FileWriter;   // Import the FileWriter library
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Random; // Import the Random library

public class CemPorCemTDC {
  public static void main(String[] args) {
	    
    try {
      FileWriter myWriter = new FileWriter("cemPorCemTDC.pgm"); //creates the pgm file      
      Random rand = new Random(); //instance of random class
      
      System.out.println("Generate 100 x 100 PPM Shades of Gray Image");
      myWriter.write(" P2 100 100 \n 255 \n");
      
      for (int i = 0; i < 99; i++) {
    	  if (i==0) {
    		  myWriter.write("");
		} else {
			myWriter.write("\n");
		}
    	  for (int j = 0; j < 99; j++) {
    		  
    		  int upperbound = 255; //limit the random to 0-255
    	      int int_random = rand.nextInt(upperbound); //generates the random number
    	      
    	      String random_converted = Integer.toString(int_random);//necessary conversion to write into the file
    		  myWriter.write(random_converted + " ");
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