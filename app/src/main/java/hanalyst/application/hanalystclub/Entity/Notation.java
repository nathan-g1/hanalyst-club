package hanalyst.application.hanalystclub.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Notation {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String what;
    private String who;
    private String when;
    private String where;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
