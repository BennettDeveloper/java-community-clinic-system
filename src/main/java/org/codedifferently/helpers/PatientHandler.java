package org.codedifferently.helpers;
import org.codedifferently.CarClinicSystem;
import org.codedifferently.CarPatient;

import java.util.UUID;

public class PatientHandler {

    public void promptNewPatient(CarClinicSystem carClinicSystem) {

        //Prompt user for information. (firstname, lastname, email, phoneNumber)
        System.out.println("What is your first name?");
        String firstName = InputHandler.handleStringInput();

        System.out.println("What is your last name?");
        String lastName = InputHandler.handleStringInput();

        System.out.println("What's your Email Address?");
        String email = InputHandler.handleStringInput();

        System.out.println("What's your Phone Number?");
        String phoneNumber = InputHandler.handleStringInput();

        System.out.println("Awesome, here's your Unique ID, do not share this with ANYONE!");
        String uniqueID = UUID.randomUUID().toString().substring(0, 6);
        System.out.println(uniqueID);

        //Make New Car Patient.
        CarPatient carPatient = new CarPatient(uniqueID, firstName, lastName, email, phoneNumber);

        //Add new Car Patient to Car Patient Array List.
        carClinicSystem.getCarPatients().add(carPatient);

    }

    public void viewAllPatients(CarClinicSystem carClinicSystem) {
        System.out.println("--------------------------------");
        System.out.println("****************************");
        System.out.println("VIEWING ALL PATIENTS");

        //If no patients, print extra message
        if(carClinicSystem.getCarPatients().isEmpty()) {
            System.out.println("THERE ARE NO PATIENTS IN THE SYSTEM CURRENTLY");
        }

        //Loop through all car patients, print each of them out.
        for(int i = 0; i < carClinicSystem.getCarPatients().size(); i++) {

            //print every field of CarPatient
            System.out.print((i+1) + ". (");
            System.out.print(carClinicSystem.getCarPatients().get(i).getPatientID()+ ") ");
            System.out.print(carClinicSystem.getCarPatients().get(i).getLastName() + ", ");
            System.out.print(carClinicSystem.getCarPatients().get(i).getFirstName() + " | ");
            System.out.print(carClinicSystem.getCarPatients().get(i).getEmail() + " | ");
            System.out.print(carClinicSystem.getCarPatients().get(i).getPhoneNumber());

            String checkedInMsg = (carClinicSystem.getCarPatients().get(i).isCheckedIn() ? "Yes" : " No");
            System.out.print("Checked In: " + checkedInMsg);
            System.out.println();
        }

        System.out.println("***************************");
        System.out.println("---------------------------------");

        System.out.println("Type any key to continue...");
        InputHandler.handleStringInput();

    }


    public CarPatient searchForPatient(CarClinicSystem carClinicSystem) {
        System.out.println("SEARCHING FOR PATIENT!!");
        System.out.println("Enter the Patients Last Name:");

        String lastNameInput = InputHandler.handleStringInput();
        CarPatient searchedPatient = null;

        //Loop through all car patients, if theres 1-match. retrieve him
        int foundPatientCount = 0;

        for(int i = 0; i < carClinicSystem.getCarPatients().size(); i++) {

            //Keep a counter if we find a match, if we get more than one match. try
            //to check for first name now to distinguish the two matches.
            if(lastNameInput.equals(carClinicSystem.getCarPatients().get(i).getLastName())) {
                searchedPatient = carClinicSystem.getCarPatients().get(i);
                foundPatientCount++;
            }
        }
        //If one exact match, return the result!
        if(foundPatientCount <= 1) {
            return searchedPatient;
        }

        //else, we will check first names now
        System.out.println("Enter the Patients First Name:");
        searchedPatient = null;
        foundPatientCount = 0;
        String firstNameInput = InputHandler.handleStringInput();
        for(int i = 0; i < carClinicSystem.getCarPatients().size(); i++) {

            //Keep a counter if we find a match, if we get more than one match. try
            //to check for first name now to distinguish the two matches.
            if(firstNameInput.equals(carClinicSystem.getCarPatients().get(i).getFirstName())) {
                searchedPatient = carClinicSystem.getCarPatients().get(i);
                foundPatientCount++;
            }
        }

        //If one exact match, return the result!
        if(foundPatientCount <= 1) {
            return searchedPatient;
        }

        //Finally, if last name AND first name grants duplicate result, then ask user for their
        //unique ID

        System.out.println("Enter the Patients UUID:");
        searchedPatient = null;
        foundPatientCount = 0;
        String uuIDInput = InputHandler.handleStringInput();
        for(int i = 0; i < carClinicSystem.getCarPatients().size(); i++) {

            //Keep a counter if we find a match, if we get more than one match. try
            //to check for first name now to distinguish the two matches.
            if(uuIDInput.equals(carClinicSystem.getCarPatients().get(i).getPatientID())) {
                searchedPatient = carClinicSystem.getCarPatients().get(i);
                foundPatientCount++;
            }
        }
        return searchedPatient;
    }

    //Print searched patient, call helper to help with results. this one just prints
    //what it finds.
    public void printSearchedPatient(CarClinicSystem carClinicSystem) {

        CarPatient searchedPatient = searchForPatient(carClinicSystem);

        if(searchedPatient == null) {
            System.out.println("Searched Patient did not exist! Please try again!");
        }
        else {
            System.out.println("-----------------------------------------");
            System.out.println("Patient FOUND: ");
            System.out.println("Name: " + searchedPatient.getFirstName() +
                    ", " +searchedPatient.getLastName());
            System.out.println("Email: " + searchedPatient.getEmail());
            System.out.println("Phone Number: " + searchedPatient.getPhoneNumber());
            System.out.println("UUID: " + searchedPatient.getPatientID());
            System.out.println("Checked-In" + (searchedPatient.isCheckedIn() ? "Yes" : "No"));
            System.out.println("Type any key to continue...");
            InputHandler.handleStringInput();
            System.out.println("-------------------------------------");
        }
    }

    //We use the search functionality to get the patient checked-in.
    public void checkInPatient(CarClinicSystem carClinicSystem) {
        System.out.println("---------------------------------------");
        System.out.println("Alright, checking in a patient!!");
        System.out.println("But first we need to locate the patient!");

        CarPatient searchedPatient = searchForPatient(carClinicSystem);

        if(searchedPatient == null) {
            System.out.println("Searched Patient did not exist! Please try again!");
        }
        else {
            System.out.println("Patient " + searchedPatient.getLastName() +
                    ", " +  searchedPatient.getFirstName() + " ("
                    + searchedPatient.getPatientID() +
                    ") is now checked in!");
            searchedPatient.setCheckedIn(true);
        }
    }

}
