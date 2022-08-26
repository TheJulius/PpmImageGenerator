package pdi;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

public class CemPorCemRGB {
	public static void main(String[] args) {
	 try {
	      FileWriter myWriter = new FileWriter("cemPorCemRGB.ppm"); //creates the ppm file
	      Random rand = new Random(); //instance of random class
	      
	      System.out.println("Generate 100 x 100 PPM RGB Image");
	      myWriter.write(" P3 100 100 \n 255 \n");
	      
	      for (int i = 0; i < 99; i++) {
	    	  if (i==0) {
	    		  myWriter.write("");
			} else {
				myWriter.write("\n");
			}
	    	  for (int j = 0; j < 99; j++) {
	    		  
	    		  for (int j2 = 0; j2 < 3; j2++) {
	    			  int upperbound = 255; //limit the random to 0-255
		    	      int int_random = rand.nextInt(upperbound); //generates the random number
		    	      
		    	      String random_converted = Integer.toString(int_random);//necessary conversion to write into the file
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
