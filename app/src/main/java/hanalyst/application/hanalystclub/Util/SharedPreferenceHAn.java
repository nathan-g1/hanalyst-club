package hanalyst.application.hanalystclub.Util;

import android.content.Context;
import android.preference.PreferenceManager;

public class SharedPreferenceHAn {
    private static final String PLAYING_TEAM_NAME = "PLAYING_TEAM_NAME";
    private static final String CURRENT_GAME_ID = "CURRENT_GAME_ID";
    private Context context;
    private static final String TEAM_NAME = "TEAM_NAME";
    private static final String TEAM_ID = "TEAM_ID";

    public SharedPreferenceHAn(Context context) {
        this.context = context;
    }

    public void setTeamId(String teamId) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(TEAM_ID, teamId).apply();
    }

    public String getTeamId() {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(TEAM_ID, "null");
    }

    public void setTeamName(String teamName) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(TEAM_NAME, teamName).apply();
    }

    public String getTeamName() {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(TEAM_NAME, "null");
    }

    public void setPlayingTeams(String teamName) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(PLAYING_TEAM_NAME, teamName).apply();
    }

    public String getPlayingTeams() {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(PLAYING_TEAM_NAME, "null");
    }

    public void setCurrentGameId(String gameId) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(CURRENT_GAME_ID, gameId).apply();
    }

    public String getCurrentGameId() {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(CURRENT_GAME_ID,"null");
    }

    public void signOut() {
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply();
    }
}
