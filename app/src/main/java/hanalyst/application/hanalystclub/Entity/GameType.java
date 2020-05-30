package hanalyst.application.hanalystclub.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class GameType {

    @PrimaryKey
    @NonNull
    private String id;
    private String gameType;
    private String shortCode;

    public GameType(@NonNull String id, String gameType, String shortCode) {
        this.id = id;
        this.gameType = gameType;
        this.shortCode = shortCode;
    }

    @NonNull
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
