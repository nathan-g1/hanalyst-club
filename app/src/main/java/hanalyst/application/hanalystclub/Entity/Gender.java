package hanalyst.application.hanalystclub.Entity;

public enum Gender {
    MALE ("Male"),
    FEMALE ("Female");

    private final String shortCode;

    Gender(String shortCode) {
        this.shortCode = shortCode;
    }

    @Override
    public String toString() {
        return shortCode;
    }
};
