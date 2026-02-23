package org.codedifferently;

import org.codedifferently.data.TimeSlot;

import java.sql.Array;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class CarAppointment {
    private static final LocalTime OPEN_TIME = LocalTime.of(7,0);
    private static final LocalTime CLOSE_TIME = LocalTime.of(21,0);
    private static final LocalTime LAST_APPOINTMENT = LocalTime.of(20,0);

    private CarPatient carPatient;
    private TimeSlot timeSlot;

    private String serviceType;

    private boolean isDuringBusinessHours(TimeSlot timeSlot){
        LocalTime startTime = timeSlot.getStart().toLocalTime();
        LocalTime endTime = timeSlot.getEnd().toLocalTime();

        return startTime.isAfter(OPEN_TIME) && endTime.isBefore(CLOSE_TIME);
    }
    public CarAppointment(CarPatient carPatient, TimeSlot timeSlot, String serviceType){
        if(isDuringBusinessHours(timeSlot)){
            this.carPatient = carPatient;
            this.timeSlot = timeSlot;
            this.serviceType= serviceType;
        } else {
            System.out.println("Appointment must be between our business hours of 7am-9pm");
        }
    }

    public CarAppointment(CarPatient carPatient, TimeSlot timeSlot){
        if(isDuringBusinessHours(timeSlot)){
            this.carPatient = carPatient;
            this.timeSlot = timeSlot;
        } else {
            System.out.println("Appointment must be between our business hours of 7am-9pm");
        }
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

    /*if ()
    String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    open 7am-9pm daily (14hours/day)
    int[] weekdayHours = new int [13];
    ArrayList<String> apptSlots = new ArrayList<>();


    public void makeAppt(){
*/


}
