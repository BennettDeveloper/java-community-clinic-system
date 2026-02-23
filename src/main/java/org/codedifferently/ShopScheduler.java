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
    public boolean cancelAppointment(CarClinicSystem carClinicSystem, String appointmentID) {

        for (int i = 0; i < carClinicSystem.getCarAppointments().size(); i++) {

            CarAppointment appt = carClinicSystem.getCarAppointments().get(i);

            if (appt.getAppointmentID().equals(appointmentID)) {

                // Save the freed slot before removing
                TimeSlot freedSlot = appt.getTimeSlot();

                // Remove appointment
                carClinicSystem.getCarAppointments().remove(i);
                System.out.println("Appointment canceled.");

                // Auto-schedule from waitlist (same slot)
                WaitlistEntry next = carClinicSystem.getWaitlist().popNextFor(freedSlot);

                if (next != null) {
                    CarAppointment waitlistedAppt = new CarAppointment(
                            next.getPatient(),
                            freedSlot,
                            next.getServiceType()
                    );

                    carClinicSystem.getCarAppointments().add(waitlistedAppt);

                    System.out.println("Scheduled from waitlist: "
                            + next.getPatient().getLastName() + ", "
                            + next.getPatient().getFirstName());
                }

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
                        + appointment.getCarPatient().getLastName() + ", " + appointment.getCarPatient().getFirstName()
                        + " (" + appointment.getTimeSlot().getStart() +
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

