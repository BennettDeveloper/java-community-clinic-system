package org.codedifferently;

import java.util.ArrayList;

public class CarClinicSystem {

    private ArrayList<CarAppointment> carAppointments = new ArrayList<>();
    private ArrayList<CarPatient> carPatients = new ArrayList<>();

    private final Waitlist waitlist = new Waitlist();

    public Waitlist getWaitlist() {
        return waitlist;
    }
    public ArrayList<CarAppointment> getCarAppointments() {
        return carAppointments;
    }

    public ArrayList<CarPatient> getCarPatients() {
        return carPatients;
    }
}
