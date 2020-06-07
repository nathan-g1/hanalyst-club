package hanalyst.application.hanalystclub.Util;

import android.content.Context;
import android.preference.PreferenceManager;

public class SharedPreferenceHAn {
    Context context;
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

    public void signOut() {
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply();
    }
}
