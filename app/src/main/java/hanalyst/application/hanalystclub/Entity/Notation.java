package hanalyst.application.hanalystclub.Entity;

import androidx.room.Entity;

@Entity
public class Notation {
    private String id;
    private String what;
    private String who;
    private String when;
    private String where;

    public Notation(String id, String what, String who, String when, String where) {
        this.id = id;
        this.what = what;
        this.who = who;
        this.when = when;
        this.where = where;
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
}
