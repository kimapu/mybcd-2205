package bcd.exercise.crypto.solution;

import java.time.LocalDateTime;
import java.util.Date;

public class Registration {
    
    private Practitioner p;
    
    private LocalDateTime registration_dateTime;

    public Registration() {
        this.registration_dateTime = LocalDateTime.now();
    }

    public Registration(Practitioner p) {
        this();
        this.p = p;
    }

    public LocalDateTime getRegistration_dateTime() {
        return registration_dateTime;
    }

    public void setRegistration_dateTime(LocalDateTime registration_dateTime) {
        this.registration_dateTime = registration_dateTime;
    }

    @Override
    public String toString() {
        return String.join(",", p.getKey(), registration_dateTime.toString());
    }
    
    
    
}
