package org.codedifferently.helpers;

import java.util.Scanner;

public class InputHandler {

    public static int handleIntegerInput() {
        Scanner scan = new Scanner(System.in);
        int scanInput = 0;
        boolean validScanInput = false;
        //While loop to make sure user puts in the correct input
        while(!validScanInput) {
            //Call Scanner methods
            try {
                //Scanner method to collect input
                scanInput = scan.nextInt();
                validScanInput = true;
            }
            catch (Exception e) {
                //If user enters invalid input, the catch block will prevent errors.
                System.out.println("Invalid input! Try typing a number instead of a String!");
                scan.next();
            }
        }
        return scanInput;
    }

    public static String handleStringInput() {
        Scanner scan = new Scanner(System.in);
        String scanInput = "";
        boolean validScanInput = false;
        //While loop to make sure user puts in the correct input
        while(!validScanInput) {
            //Call Scanner methods
            try {
                //Scanner method to collect input
                scanInput = scan.nextLine();
                validScanInput = true;
            }
            catch (Exception e) {
                //If user enters invalid input, the catch block will prevent errors.
                System.out.println("Invalid input! Try typing a valid String!");
                scan.next();
            }
        }
        return scanInput;
    }

}
