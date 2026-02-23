package org.codedifferently;

public class CarAppointment {
         // The customer who booked
        private CarPatient customer;
         // Type of repair
        private String serviceType;
         // Index of time slot in the schedule
        private int slotIndex;
        //The constructor w each contract
        public CarAppointment(CarPatient customer, String serviceType, int slotIndex) {
            this.customer = customer;
            this.serviceType = serviceType;
            this.slotIndex = slotIndex;
        }

        public CarPatient getCustomer() {
            return customer;
        }

        public String getServiceType() {
            return serviceType;
        }

        public int getSlotIndex() {
            return slotIndex;
        }
    }

