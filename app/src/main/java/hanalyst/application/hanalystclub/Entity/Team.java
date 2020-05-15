package hanalyst.application.hanalystclub.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Team {
    @PrimaryKey
    private String id;
    private String coach;
    private String analyst;
    private String since;
    private String name;
    private String captain;
    private int players;

    public Team(String id, String coach, String analyst, String since, String name, String captain, int players) {
        this.id = id;
        this.coach = coach;
        this.analyst = analyst;
        this.since = since;
        this.name = name;
        this.captain = captain;
        this.players = players;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }
}
