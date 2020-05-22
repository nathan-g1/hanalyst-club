package hanalyst.application.hanalystclub.Entity.remote;

public class RNotation {
    private String id;
    private String what;
    private String who;
    private String when;
    private String where;
    private String gameId;

    public RNotation(String id, String what, String who, String when, String where, String gameId) {
        this.id = id;
        this.what = what;
        this.who = who;
        this.when = when;
        this.where = where;
        this.gameId = gameId;
    }

    public RNotation(String what, String who, String when, String where, String gameId) {
        this.what = what;
        this.who = who;
        this.when = when;
        this.where = where;
        this.gameId = gameId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
