package hanalyst.application.hanalystclub.Entity;

public enum Language {
    ENGLISH("English", "en"),
    AMHARIC("Amharic", "am"),
    OROMIFFA("Oromiffa", "om"),
    SOMALI("Somali", "so"),
    TIGRIGNA("Tigrigna", "ti");

    private final String type;
    private final String shortCode;

    Language(String type, String shortCode) {
        this.type = type;
        this.shortCode = shortCode;
    }

    @Override
    public String toString() {
        return type;
    }

    public String getLanguageCode() {
        return shortCode;
    }
}


