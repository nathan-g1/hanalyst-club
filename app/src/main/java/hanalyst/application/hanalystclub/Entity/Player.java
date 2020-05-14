package hanalyst.application.hanalystclub.Entity;


import androidx.room.Entity;

@Entity
public class Player {
    private String id;
    private int tNumber;
    private String name;
    private String teamId;
    private String history;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTNumber() {
        return tNumber;
    }

    public void setTNumber(int tNumber) {
        this.tNumber = tNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
