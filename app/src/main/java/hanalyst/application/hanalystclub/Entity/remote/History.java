package hanalyst.application.hanalystclub.Entity.remote;

class History {
    private String gameId;
    private String description;
    private String[] playedInTeams;

    public History(String gameId, String description, String[] playedInTeams) {
        this.gameId = gameId;
        this.description = description;
        this.playedInTeams = playedInTeams;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getPlayedInTeams() {
        return playedInTeams;
    }

    public void setPlayedInTeams(String[] playedInTeams) {
        this.playedInTeams = playedInTeams;
    }
}
