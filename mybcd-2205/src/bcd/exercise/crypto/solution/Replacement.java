package bcd.exercise.crypto.solution;

public class Replacement {

    private Account acc;
    private String name;

    public Replacement(Account acc, String name) {
        this.acc = acc;
        this.name = name;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Replacement{" + "acc=" + acc + ", name=" + name + '}';
    }
    
    
    
    
}
