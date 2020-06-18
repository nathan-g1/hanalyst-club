package hanalyst.application.hanalystclub.Entity.remote;

public class RGameType {

    private String id;
    private String gameType;
    private String shortCode;

    public RGameType(String gameType, String shortCode) {
        this.gameType = gameType;
        this.shortCode = shortCode;
    }

    public String getId() {
        return id;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }
}
