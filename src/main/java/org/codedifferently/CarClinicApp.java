package org.codedifferently;

import org.codedifferently.data.TimeSlot;
import org.codedifferently.helpers.InputHandler;
import org.codedifferently.helpers.PatientHandler;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class CarClinicApp {
    public static void main(String[] args) {

        CarClinicApp carClinicApp = new CarClinicApp();
        CarClinicSystem carClinicSystem = new CarClinicSystem();

        carClinicApp.handleMainMenu(carClinicSystem);

    }

    void handleMainMenu(CarClinicSystem carClinicSystem) {

        boolean inMainMenu = true;
        while(inMainMenu) {

            System.out.println("Welcome to the CWW Auto Repair Shop.");
            System.out.println("What would you like to do today?");

            System.out.println("-------------------------------------");
            System.out.println("1. View Car Patients");
            System.out.println("2. View Car Appointments");
            System.out.println("3. Check Summary Reports");
            System.out.println("4. Exit");
            System.out.println("-------------------------------------");

            //Call Static Method to handle Integer input
            int scanInput = InputHandler.handleIntegerInput();

            switch (scanInput) {
                case 1:
                    handlePatientMenu(carClinicSystem);
                    break;
                case 2:
                    handleAppointmentMenu(carClinicSystem);
                    break;
                case 4:
                    inMainMenu = false;
                    System.out.println("Alright, have a nice day!");
                    break;
                default:
                    System.out.println("That's not an option on our menu! Please try again!");
                    break;
            }
        }
    }

    void handlePatientMenu(CarClinicSystem carClinicSystem) {

        boolean inPatientMenu = true;
        while(inPatientMenu) {
            System.out.println("-------------------------------------");
            System.out.println("Patient Viewer");
            System.out.println("We appreciate your patience, Patient!");
            System.out.println("What would you like to do today?");
            System.out.println("-------------------------------------");
            System.out.println("1. Add patient");
            System.out.println("2. View all patients");
            System.out.println("3. Check in patient");
            System.out.println("4. Search for patient");
            System.out.println("5. Exit");
            System.out.println("-------------------------------------");

            int scanInput = InputHandler.handleIntegerInput();

            PatientHandler patientHandler = new PatientHandler();

            switch(scanInput) {
                case 1:
                    patientHandler.promptNewPatient(carClinicSystem);
                    break;
                case 2:
                    patientHandler.viewAllPatients(carClinicSystem);
                    break;
                case 3:
                    patientHandler.checkInPatient(carClinicSystem);
                    break;
                case 4:
                    patientHandler.printSearchedPatient(carClinicSystem);
                    break;
                case 5:
                    System.out.println("Exiting out of Patient Menu!");
                    inPatientMenu = false;
                    break;
                default:
                    System.out.println("Thats not an option on our menu, please try again.");
            }
        }

    }

    void handleAppointmentMenu(CarClinicSystem carClinicSystem) {

        boolean inAppointmentMenu = true;
        while(inAppointmentMenu) {
            System.out.println("-------------------------------------");
            System.out.println("Appointment Viewer");
            System.out.println("You have reached A POINT meant in time.");
            System.out.println("What would you like to do today?");
            System.out.println("-------------------------------------");
            System.out.println("1. View Timeslots");
            System.out.println("2. Schedule an appointment");
            System.out.println("3. Cancel an appointment.");
            System.out.println("4. Exit");
            System.out.println("-------------------------------------");

            int scanInput = InputHandler.handleIntegerInput();

            PatientHandler patientHandler = new PatientHandler();

            ShopScheduler shopScheduler = new ShopScheduler();

            switch(scanInput) {
                case 1:
                    shopScheduler.printSchedule(carClinicSystem);
                    break;
                case 2:
                    CarPatient patient = patientHandler.searchForPatient(carClinicSystem);
                    if(patient == null) {
                        System.out.println("Patient was not found!!!");
                        //Create new patient if not found.
                        patient = patientHandler.promptNewPatient(carClinicSystem);
                    }
                    TimeSlot timeSlot = shopScheduler.promptTimeSlot();
                    String serviceType = shopScheduler.promptServiceType();
                    shopScheduler.scheduleAppointment(carClinicSystem, patient, timeSlot, serviceType);
                    break;
                case 3:
                    System.out.println("Give me the appointment ID that you want to cancel: ");
                    String appointmentID = InputHandler.handleStringInput();
                    shopScheduler.cancelAppointment(carClinicSystem, appointmentID);
                    break;
                case 4:
                    System.out.println("Exiting out of Patient Menu!");
                    System.out.println("End of day summary report.");
                    System.out.println("Number of appointments created: " + CarAppointment.getInstanceCounter());
                    System.out.println("Number of customers added to system: " + CarPatient.getInstanceCounter());
                    inAppointmentMenu = false;
                    break;
                default:
                    System.out.println("Thats not an option on our menu, please try again.");
            }
        }

    }
}