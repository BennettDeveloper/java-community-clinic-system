package org.codedifferently;

import org.codedifferently.CarPatient;
import org.codedifferently.data.TimeSlot;


public class WaitlistEntry {
    private final String entryId;
    private final CarPatient patient;
    private final TimeSlot requestedTimeSlot;
    private final String serviceType;

    public WaitlistEntry(String entryId, CarPatient patient, TimeSlot requestedTimeSlot, String serviceType) {
        this.entryId = entryId;
        this.patient = patient;
        this.requestedTimeSlot = requestedTimeSlot;
        this.serviceType = serviceType;
    }

    public String getEntryId() {
        return entryId;
    }

    public CarPatient getPatient() {
        return patient;
    }

    public TimeSlot getRequestedTimeSlot() {
        return requestedTimeSlot;
    }

    public String getServiceType() {
        return serviceType;
    }

    @Override
    public String toString() {
        return "Entry ID: " + entryId
                + " | Patient: " + patient.getLastName() + ", " + patient.getFirstName()
                + " | TimeSlot: " + requestedTimeSlot
                + " | Service: " + serviceType;
    }
}

