package me.tr.survival.util;

public class Util {

	  public static boolean isFloat(String s){
     	 try {
     	       Float.parseFloat(s);
     	    } catch (NumberFormatException nfe) {
     	        return false;
     	    }
     	    return true;
     }
     
     public static boolean isInt(String s) {
         try {
             Integer.parseInt(s);
         } catch (NumberFormatException nfe) {
             return false;
         }
         return true;
     }
     
     public static boolean isDouble(String s) {
         try {
             Double.parseDouble(s);
         } catch (NumberFormatException nfe) {
             return false;
         }
         return true;
     }
	
}
