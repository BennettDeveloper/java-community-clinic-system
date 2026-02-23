package org.codedifferently;

import org.codedifferently.data.TimeSlot;

import java.sql.Array;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

public class CarAppointment {

    private String appointmentID;

    private static final LocalTime OPEN_TIME = LocalTime.of(7,0);
    private static final LocalTime CLOSE_TIME = LocalTime.of(21,0);
    private static final LocalTime LAST_APPOINTMENT = LocalTime.of(20,0);
    private CarPatient carPatient;
    private TimeSlot timeSlot;
    private boolean isCompleted;
    private String serviceType;
    private static int instanceCounter;
    {
        instanceCounter++;
    }
    public static int getInstanceCounter() {
        return instanceCounter;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public boolean isDuringBusinessHours(TimeSlot timeSlot){
        LocalTime startTime = timeSlot.getStart().toLocalTime();
        LocalTime endTime = timeSlot.getEnd().toLocalTime();

        return startTime.isAfter(OPEN_TIME) && endTime.isBefore(CLOSE_TIME);
    }
    public CarAppointment(CarPatient carPatient, TimeSlot timeSlot, String serviceType){
            this.carPatient = carPatient;
            this.timeSlot = timeSlot;
            this.serviceType= serviceType;
            appointmentID = UUID.randomUUID().toString().substring(0, 6);
    }

    public CarAppointment(CarPatient carPatient, TimeSlot timeSlot){
        if(isDuringBusinessHours(timeSlot)){
            this.carPatient = carPatient;
            this.timeSlot = timeSlot;
        } else {
            System.out.println("Appointment must be between our business hours of 7am-9pm");
        }
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public CarPatient getCarPatient() {
        return carPatient;
    }

    public void setCarPatient(CarPatient carPatient) {
        this.carPatient = carPatient;
    }

}
