package hanalyst.application.hanalystclub.Entity;

import androidx.room.Entity;

@Entity
public class Game {
    private String id;
    private String startTime;
    private String endTime;
    private String venue;
    private boolean ha;
    private String referee;
    private Temperature temperature;
    private String gameType;
    private Team[] playingTeams;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public boolean isHa() {
        return ha;
    }

    public void setHa(boolean ha) {
        this.ha = ha;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public Team[] getPlayingTeams() {
        return playingTeams;
    }

    public void setPlayingTeams(Team[] playingTeams) {
        this.playingTeams = playingTeams;
    }
}
