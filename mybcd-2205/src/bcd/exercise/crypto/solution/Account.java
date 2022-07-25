package bcd.exercise.crypto.solution;

import java.util.Date;

public class Account {

    private String membership_from;
    private String membership_to;

    public Account(String membership_from, String membership_to) {
        this.membership_from = membership_from;
        this.membership_to = membership_to;
    }

    public String getMembership_from() {
        return membership_from;
    }

    public void setMembership_from(String membership_from) {
        this.membership_from = membership_from;
    }

    public String getMembership_to() {
        return membership_to;
    }

    public void setMembership_to(String membership_to) {
        this.membership_to = membership_to;
    }

    
    @Override
    public String toString() {
        return "Account{" + "membership_from=" + membership_from + ", membership_to=" + membership_to + '}';
    }

}
