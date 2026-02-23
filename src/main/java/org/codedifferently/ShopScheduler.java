package org.codedifferently;
import org.codedifferently.data.TimeSlot;
import org.codedifferently.helpers.InputHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class ShopScheduler {

        // Check if a slot is already booked
        public boolean isSlotBooked(CarClinicSystem carClinicSystem, String appointmentID) {
            for (CarAppointment appt : carClinicSystem.getCarAppointments()) {
                if (appt.getAppointmentID().equals(appointmentID)) {
                    return true; // Slot is taken
                }
            }
            return false; // Slot is available
        }

        // Schedule an appointment
        public boolean scheduleAppointment(CarClinicSystem carClinicSystem, CarPatient customer, TimeSlot timeSlot, String serviceType) {

            CarAppointment newAppt = new CarAppointment(customer, timeSlot, "Oil Change");

            // Prevent double booking
            if (isSlotBooked(carClinicSystem, newAppt.getAppointmentID())) {
                System.out.println("Slot is booked! Can't do it!");
                return false;
            }

            //Prevent scheduling during off business hours
            if(!newAppt.isDuringBusinessHours(newAppt.getTimeSlot())) {
                System.out.println("Can't schedule during business hours! Can't do it");
                return false;
            }

            carClinicSystem.getCarAppointments().add(newAppt);
            return true;
        }

        // Cancel appointment by timeSlot
        public boolean cancelAppointment(CarClinicSystem carClinicSystem, String appointmentID) {

            for(int i = 0; i < carClinicSystem.getCarAppointments().size(); i++) {

                if(carClinicSystem.getCarAppointments().get(i).getAppointmentID().equals(appointmentID)) {
                    carClinicSystem.getCarAppointments().remove(i);
                    return true;
                }
            }
            return false;

        }

        // Print full schedule
        public void printSchedule(CarClinicSystem carClinicSystem) {
            System.out.println("\n=== CWW Auto Repair Shop Daily Schedule ===");

            for (int i = 0; i < carClinicSystem.getCarAppointments().size(); i++) {

                CarAppointment appointment = carClinicSystem.getCarAppointments().get(i);

                if (appointment == null) {
                    System.out.println((i + 1) + " - AVAILABLE");
                } else {
                    System.out.println((i + 1) + ". " + "(" + appointment.getAppointmentID() + ") "
                            + appointment.getCarPatient().getLastName()  + ", " + appointment.getCarPatient().getFirstName()
                            + " (" +  appointment.getTimeSlot().getStart() +
                            " - " + appointment.getTimeSlot().getEnd() +
                           ") (" + appointment.getServiceType() + ")");
                }
            }
        }

        public TimeSlot promptTimeSlot() {
            System.out.println("Give me the Start Time:");
            int startTime = InputHandler.handleIntegerInput();

            System.out.println("Give me the End Time:");
            int endTime = InputHandler.handleIntegerInput();

            LocalDateTime startDateTime = LocalDate.now().atTime(startTime, 0);

            LocalDateTime endDateTime = LocalDate.now().atTime(endTime, 0);

            TimeSlot timeSlot = new TimeSlot(startDateTime, endDateTime);
            return timeSlot;
        }
        
        public String promptServiceType() {
            System.out.println("What type of Service are you doing today?:");
            String serviceType = InputHandler.handleStringInput();
            return serviceType;
        }
    }

