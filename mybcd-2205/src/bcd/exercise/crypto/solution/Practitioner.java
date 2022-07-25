package bcd.exercise.crypto.solution;

public class Practitioner {
    
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    private String name;
    private String category;
    private Account acc;
    private String keyPairDirectory;
    
    private Replacement replacement;

    public Practitioner() {
    }

    public Practitioner(String key, String name, String category, Account acc, String keyPairDirectory) {
        this.key = key;
        this.name = name;
        this.category = category;
        this.acc = acc;
        this.keyPairDirectory = keyPairDirectory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Replacement getReplacement() {
        return replacement;
    }

    public void setReplacement(Replacement replacement) {
        this.replacement = replacement;
    }

    public String getKeyPairDirectory() {
        return keyPairDirectory;
    }

    public void setKeyPairDirectory(String keyPairDirectory) {
        this.keyPairDirectory = keyPairDirectory;
    }

    @Override
    public String toString() {
        return String.join(",", 
                key, name, category, acc.getMembership_from(), acc.getMembership_to(), 
                replacement.getName(), replacement.getAcc().getMembership_from(), replacement.getAcc().getMembership_to(),
                keyPairDirectory
        );
    }

    
    
}
