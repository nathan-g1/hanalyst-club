package hanalyst.application.hanalystclub.Entity.remote;

public class RPlayer {
    private String id;
    private int tnumber;
    private String name;
    private String teamId;
    private History history;

    public RPlayer(String id, int tnumber, String name, String teamId, History history) {
        this.id = id;
        this.tnumber = tnumber;
        this.name = name;
        this.teamId = teamId;
        this.history = history;
    }

    public RPlayer(int tnumber, String name, String teamId, History history) {
        this.tnumber = tnumber;
        this.name = name;
        this.teamId = teamId;
        this.history = history;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTNumber() {
        return tnumber;
    }

    public void setTNumber(int tNumber) {
        this.tnumber = tNumber;
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

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }
}
