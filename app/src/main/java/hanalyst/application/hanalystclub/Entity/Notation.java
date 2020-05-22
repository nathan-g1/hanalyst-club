package hanalyst.application.hanalystclub.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Notation {
    @PrimaryKey
    @NonNull
    private String id;
    private String what;
    private String who;
    private String when;
    private String where;
    private String gameId;

    public Notation(@NonNull String id, String what, String who, String when, String where, String gameId) {
        this.id = id;
        this.what = what;
        this.who = who;
        this.when = when;
        this.where = where;
        this.gameId = gameId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
