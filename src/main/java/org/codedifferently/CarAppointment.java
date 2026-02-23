package org.codedifferently;

import java.sql.Array;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class CarAppointment {
    private static final LocalTime OPEN_TIME = LocalTime.of(7,0);
    private static final LocalTime CLOSE_TIME = LocalTime.of(21,0);
    private static final LocalTime LAST_APPOINTMENT = LocalTime.of(20,0);

    private CarPatient carPatient;
    private LocalDateTime apptDateTime;
    private String serviceType;

    private boolean isDuringBusinessHours(LocalDateTime apptDateTime){
        LocalTime time = apptDateTime.toLocalTime();
        return time.isAfter(OPEN_TIME) && time.isBefore(CLOSE_TIME);
    }
    public CarAppointment(CarPatient carPatient, LocalDateTime apptDateTime, String serviceType){
        if(isDuringBusinessHours(apptDateTime)){
            this.carPatient = carPatient;
            this.apptDateTime = apptDateTime;
            this.serviceType= serviceType;
        } else {
            throw new IllegalArgumentException("Appointment must be between our business hours of 7am-9pm");
        }
    }

    public CarAppointment(CarPatient carPatient, LocalDateTime apptDateTime){
        if(isDuringBusinessHours(apptDateTime)){
            this.carPatient = carPatient;
            this.apptDateTime = apptDateTime;
        } else {
            throw new IllegalArgumentException("Appointment must be between our business hours of 7am-9pm");
        }
    }

    public LocalDateTime getApptDateTime() {
        return apptDateTime;
    }

    public void setApptDateTime(LocalDateTime apptDateTime) {
        this.apptDateTime = apptDateTime;
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
