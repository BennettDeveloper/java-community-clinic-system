package org.codedifferently.data;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Duration;

public class TimeSlot {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public TimeSlot(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {

        return end;
    }

    public Duration getDuration() {

        return Duration.between(start, end);
    }
}
