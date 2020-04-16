package hanalyst.application.hanalystclub.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import hanalyst.application.hanalystclub.Database.HAnalystDb;
import hanalyst.application.hanalystclub.Entity.Player;

public class PlayerRepository {
    private String db_player = "db_player";
    private HAnalystDb hAnalystDb;

    public PlayerRepository(Context context) {
        hAnalystDb = Room.databaseBuilder(context, HAnalystDb.class, db_player).build();
    }

    public void insertPlayer(int tNumber, String name, String teamId, int o) {
        insertPlayer(tNumber, name, teamId);
    }

    public void insertPlayer(int tNumber, String name, String teamId) {
        Player player = new Player();
        player.setTNumber(tNumber);
        player.setName(name);
        player.setTeamId(teamId);
        insertPlayer(player);
    }

    @SuppressLint("StaticFieldLeak")
    public void insertPlayer(final Player player) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                hAnalystDb.daoAccess().insertPlayer(player);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void updatePlayer(final Player player) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                hAnalystDb.daoAccess().updatePlayer(player);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void deletePlayer(final int id) {
        final LiveData<Player> player = getPlayer(id);
        if (player != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    hAnalystDb.daoAccess().deletePlayer(player.getValue());
                    return null;
                }
            }.execute();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public void deletePlayers(final Player player) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                hAnalystDb.daoAccess().deletePlayer(player);
                return null;
            }
        }.execute();
    }

    public LiveData<Player> getPlayer(int id) {
        return hAnalystDb.daoAccess().getAPlayer(id);
    }

    public LiveData<List<Player>> getPlayers() {
        return hAnalystDb.daoAccess().getAllPlayers();
    }
}
