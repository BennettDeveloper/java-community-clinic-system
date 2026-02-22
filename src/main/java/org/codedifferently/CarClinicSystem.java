package org.codedifferently;

import java.util.ArrayList;

public class CarClinicSystem {

    private ArrayList<CarAppointment> carAppointments = new ArrayList<>();
    private ArrayList<CarPatient> carPatients = new ArrayList<>();

    public ArrayList<CarAppointment> getCarAppointments() {
        return carAppointments;
    }

    public ArrayList<CarPatient> getCarPatients() {
        return carPatients;
    }
}
