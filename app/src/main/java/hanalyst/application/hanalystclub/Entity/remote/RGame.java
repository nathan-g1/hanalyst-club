package hanalyst.application.hanalystclub.Entity.remote;

import hanalyst.application.hanalystclub.Entity.Team;
import hanalyst.application.hanalystclub.Entity.Temperature;

public class RGame {
    private String id;
    private String startTime;
    private String endTime;
    private String venue;
    private boolean ha;
    private String referee;
    private Temperature temperature;
    private String gameType;
    private Team[] playingTeams;

    public RGame(String id, String startTime, String endTime, String venue, boolean ha, String referee, Temperature temperature, String gameType, Team[] playingTeams) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.ha = ha;
        this.referee = referee;
        this.temperature = temperature;
        this.gameType = gameType;
        this.playingTeams = playingTeams;
    }

    public RGame(String startTime, String endTime, String venue, boolean ha, String referee, Temperature temperature, String gameType, Team[] playingTeams) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.ha = ha;
        this.referee = referee;
        this.temperature = temperature;
        this.gameType = gameType;
        this.playingTeams = playingTeams;
    }

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
