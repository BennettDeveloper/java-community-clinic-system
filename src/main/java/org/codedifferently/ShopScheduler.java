package org.codedifferently;
import org.codedifferently.data.TimeSlot;
import org.codedifferently.helpers.InputHandler;
import org.codedifferently.WaitlistEntry;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ShopScheduler {

    private boolean timeSlotsOverlap(TimeSlot a, TimeSlot b) {
        return a.getStart().isBefore(b.getEnd()) && a.getEnd().isAfter(b.getStart());
    }

    public boolean isTimeSlotBooked(CarClinicSystem carClinicSystem, TimeSlot timeSlot) {
        for (CarAppointment appt : carClinicSystem.getCarAppointments()) {
            if (appt != null && timeSlotsOverlap(appt.getTimeSlot(), timeSlot)) {
                return true;
            }
        }
        return false;
    }


    public boolean scheduleAppointment(CarClinicSystem carClinicSystem, CarPatient customer, TimeSlot timeSlot, String serviceType) {

        // Use the serviceType the user typed (not hardcoded)
        CarAppointment newAppt = new CarAppointment(customer, timeSlot, serviceType);

        // Prevent scheduling outside business hours
        if (!newAppt.isDuringBusinessHours(newAppt.getTimeSlot())) {
            System.out.println("Can't schedule outside business hours!");
            return false;
        }

        // If slot is already taken, offer waitlist
        if (isTimeSlotBooked(carClinicSystem, timeSlot)) {
            System.out.println("That time slot is already booked.");

            System.out.println("Would you like to join the waitlist for this time slot?");
            System.out.println("1. Yes");
            System.out.println("2. No");

            int choice = InputHandler.handleIntegerInput();
            if (choice == 1) {
                String entryId = "W" + System.currentTimeMillis();
                WaitlistEntry entry = new WaitlistEntry(entryId, customer, timeSlot, serviceType);
                carClinicSystem.getWaitlist().add(entry);

                System.out.println("Added to waitlist! Entry ID: " + entryId);
            } else {
                System.out.println("Okay, please choose another time slot.");
            }

            return false;
        }

        // Slot is open, schedule it
        carClinicSystem.getCarAppointments().add(newAppt);
        System.out.println("Appointment scheduled successfully!");
        return true;
    }

    // Cancel appointment by timeSlot

        // Cancel appointment by timeSlot
        public void cancelAppointment(CarClinicSystem carClinicSystem, String appointmentID) {
            for(int i = 0; i < carClinicSystem.getCarAppointments().size(); i++) {
                if(carClinicSystem.getCarAppointments().get(i).getAppointmentID().equals(appointmentID)) {
                    carClinicSystem.getCarAppointments().remove(i);
                    System.out.println("Appointment canceled.");
                }else {
                    System.out.println("No appointment found.");
                }
            }

        }



    // Print full schedule
    public void printSchedule(CarClinicSystem carClinicSystem) {
        System.out.println("\n=== CWW Auto Repair Shop Daily Schedule ===");
        System.out.println("---------------------------------------------");

        if(carClinicSystem.getCarAppointments().size() < 1) {
            System.out.println("No appointments currently scheduled!");
        }
        else {
            for (int i = 0; i < carClinicSystem.getCarAppointments().size(); i++) {
                CarAppointment appointment = carClinicSystem.getCarAppointments().get(i);

                if (appointment == null) {
                    System.out.println((i + 1) + " - AVAILABLE");
                } else {
                    System.out.println((i + 1) + ". " + "(" + appointment.getAppointmentID() + ") "
                            + appointment.getCarPatient().getLastName() + ", " + appointment.getCarPatient().getFirstName()
                            + " (" + appointment.getTimeSlot().getStart() +
                            " - " + appointment.getTimeSlot().getEnd() +
                            ") (" + appointment.getServiceType() + ")");
                }
            }

        }
        System.out.println("-------------------------------");
        System.out.println("WAITLIST APPOINTMENTS");
        System.out.println("-------------------------------");

        if(carClinicSystem.getWaitlist().getAll().size() < 1) {
            System.out.println("No appointments on waitlist!");
        }
        else {
            for(int i=0; i < carClinicSystem.getWaitlist().getAll().size();i++) {
                String firstName = carClinicSystem.getWaitlist().getEntries(i).getPatient().getFirstName();
                String lastName = carClinicSystem.getWaitlist().getEntries(i).getPatient().getLastName();
                String id = carClinicSystem.getWaitlist().getEntries(i).getPatient().getPatientID();

                System.out.println("First name " + firstName);
                System.out.println("Last name " + lastName);
                System.out.println("Id" + id);
            }

        }

    }
            public TimeSlot promptTimeSlot () {
                System.out.println("Give me the Start Time: (We're open from 7-21 military time)");
                int startTime = InputHandler.handleIntegerInput();
                System.out.println("Give me the End Time:");
                int endTime = InputHandler.handleIntegerInput();
                LocalDateTime startDateTime = LocalDate.now().atTime(startTime, 0);
                LocalDateTime endDateTime = LocalDate.now().atTime(endTime, 0);
                return new TimeSlot(startDateTime, endDateTime);
            }

            public String promptServiceType () {
                System.out.println("What type of Service are you doing today?:");
                return InputHandler.handleStringInput();


            }
        }
