package org.codedifferently;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class CarClinicApp {
    public static void main(String[] args) {

        System.out.println("Test");



        CarClinicApp carClinicApp = new CarClinicApp();
        carClinicApp.handleMainMenu();
    }

    void handleMainMenu() {
        System.out.println("Welcome to the CWW Auto Repair Shop.");
        System.out.println("What would you like to do today?");

        System.out.println("-------------------------------------");
        System.out.println("1. Add patient");
        System.out.println("2. View all patients");
        System.out.println("3. Check in patient");
        System.out.println("4. Search for patient");
        System.out.println("5. Exit");
        System.out.println("-------------------------------------");


    }
}