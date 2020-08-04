package hanalyst.application.hanalystclub.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import hanalyst.application.hanalystclub.Database.RoomConverter;

@Entity
public class Game {
    @PrimaryKey
    @NonNull
    private String id;
    private String startTime;
    private String endTime;
    private String venue;
    private boolean ha;
    private String referee;
    private String temperature;
    private String gameType;
    @TypeConverters(RoomConverter.class)
    private String[] playingTeams;
    private String formation;

    public Game(String id, String startTime, String endTime, String venue, boolean ha, String referee, String temperature, String gameType, String[] playingTeams, String formation) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.ha = ha;
        this.referee = referee;
        this.temperature = temperature;
        this.gameType = gameType;
        this.playingTeams = playingTeams;
        this.formation = formation;
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

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String[] getPlayingTeams() {
        return playingTeams;
    }

    public void setPlayingTeams(String[] playingTeams) {
        this.playingTeams = playingTeams;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }
}
