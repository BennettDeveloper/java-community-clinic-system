package org.codedifferently;
import java.util.ArrayList;

public class ShopScheduler {

        // Array representing available time slots for the day
        private final String[] timeSlots = {
                "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM",
                "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM"
        };

        // Store appointments using ArrayList
        private ArrayList<CarAppointment> appointments = new ArrayList<>();

        public String[] getTimeSlots() {
            return timeSlots;
        }

        // Check if a slot is already booked
        public boolean isSlotBooked(int slotIndex) {
            for (CarAppointment appt : appointments) {
                if (appt.getSlotIndex() == slotIndex) {
                    return true; // Slot is taken
                }
            }
            return false; // Slot is available
        }

        // Schedule an appointment
        public boolean scheduleAppointment(CarPatient customer, String serviceType, int slotIndex) {

            // check for timeSlot range
            if (slotIndex < 0 || slotIndex >= timeSlots.length) {
                return false;
            }

            // Prevent double booking
            if (isSlotBooked(slotIndex)) {
                return false;
            }

            CarAppointment newAppt = new CarAppointment(customer, serviceType, slotIndex);
            appointments.add(newAppt);
            return true;
        }

        // Cancel appointment by timeSlot
        public boolean cancelAppointment(int slotIndex) {
            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).getSlotIndex() == slotIndex) {
                    appointments.remove(i);
                    return true;
                }
            }
            return false;
        }

        // Print full schedule
        public void printSchedule() {
            System.out.println("\n=== CWW Auto Repair Shop Daily Schedule ===");

            for (int i = 0; i < timeSlots.length; i++) {

                CarAppointment found = null;

                for (CarAppointment appt : appointments) {
                    if (appt.getSlotIndex() == i) {
                        found = appt;
                        break;
                    }
                }

                if (found == null) {
                    System.out.println((i + 1) + ") " + timeSlots[i] + " - AVAILABLE");
                } else {
                    System.out.println((i + 1) + ") " + timeSlots[i] +
                            " - " + found.getCustomer().getFirstName() +
                            " (" + found.getServiceType() + ")");
                }
            }
        }
    }

