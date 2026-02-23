package org.codedifferently;

import org.codedifferently.data.Car;

public class CarPatient {

    private String patientID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean checkedIn;
    //public Car patientCar;


    public CarPatient(String patientID, String firstName, String lastName,
                      String email, String phoneNumber) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.checkedIn = false;
        //this.patientCar = patientCar;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPatientID() {
        return patientID;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
}
