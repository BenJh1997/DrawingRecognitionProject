package helloworld;

import java.util.HashMap;

public class Connection {
	
	/* this class is used to connect many classes together */
	
	public static MainView mainview;
   public static DrawingPanel drawingPanel;
   public static NeuralNetwork neuralNetwork;
   public static ConversionWindow conversionWindow;
   public static DrawingWindow drawingWindow;
   private static HashMap<Integer, String> binary;

   //hashmap
   static {
           binary = new HashMap<Integer, String>();
           binary.put(0, "1 0 0 0 0 0 0 0 0 0");
           binary.put(1, "0 1 0 0 0 0 0 0 0 0");
           binary.put(2, "0 0 1 0 0 0 0 0 0 0");
           binary.put(3, "0 0 0 1 0 0 0 0 0 0");
           binary.put(4, "0 0 0 0 1 0 0 0 0 0");
           binary.put(5, "0 0 0 0 0 1 0 0 0 0");
           binary.put(6, "0 0 0 0 0 0 1 0 0 0");
           binary.put(7, "0 0 0 0 0 0 0 1 0 0");
           binary.put(8, "0 0 0 0 0 0 0 0 1 0");
           binary.put(9, "0 0 0 0 0 0 0 0 0 1");
   }

   public static String getBinary(int n) {
           return binary.get(n);
   }

}