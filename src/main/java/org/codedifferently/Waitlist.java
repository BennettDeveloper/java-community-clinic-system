package org.codedifferently;

import org.codedifferently.data.TimeSlot;


import java.util.ArrayList;

public class Waitlist {

        private final ArrayList<WaitlistEntry> entries = new ArrayList<>();

        public void add(WaitlistEntry entry) {
            entries.add(entry);
        }

        // Returns and removes the first person waiting for this exact TimeSlot
        public WaitlistEntry popNextFor(TimeSlot timeSlot) {
            for (int i = 0; i < entries.size(); i++) {
                if (entries.get(i).getRequestedTimeSlot().equals(timeSlot)) {
                    return entries.remove(i);
                }
            }
            return null;
        }

        public boolean isEmpty() {
            return entries.isEmpty();
        }

        public int size() {
            return entries.size();
        }

        public ArrayList<WaitlistEntry> getAll() {
            return entries;
        }

    public WaitlistEntry getEntries(int index) {
        return entries.get(index);
    }
}
