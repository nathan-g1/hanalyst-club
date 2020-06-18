package hanalyst.application.hanalystclub.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import hanalyst.application.hanalystclub.Database.HAnalystDb;
import hanalyst.application.hanalystclub.Entity.Player;
import hanalyst.application.hanalystclub.dao.PlayerDao;

public class PlayerRepository {
    private PlayerDao playerDao;
    private LiveData<List<Player>> allPlayers;

    public PlayerRepository(Application application) {
        HAnalystDb hAnalystDb = HAnalystDb.getInstance(application);
        playerDao = hAnalystDb.playerDao();
        allPlayers = playerDao.getAllPlayers();
    }

    public void insert(Player player) {
        new InsertPlayerAsyncTask(playerDao).execute(player);
    }

    public void updatePlayer(Player player) {
        new UpdatePlayerAsyncTask(playerDao).execute(player);
    }

    public LiveData<Player> getASinglePlayer(String playerId) {
        return playerDao.getAPlayer(playerId);
    }

    public void deletePlayer(String playerId) {
        new DeletePlayerAsyncTask(playerDao).execute(playerId);
    }

    public void deleteAllPlayers() {
        new DeleteAllPlayersAsyncTask(playerDao).execute();
    }

    public LiveData<List<Player>> getAllPlayers() {
        return allPlayers;
    }

    private static class InsertPlayerAsyncTask extends AsyncTask<Player, Void, Void> {

        private PlayerDao playerDao;

        private InsertPlayerAsyncTask(PlayerDao playerDao) {
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(Player... players) {
            playerDao.insertPlayer(players[0]);
            return null;
        }
    }

    private static class UpdatePlayerAsyncTask extends AsyncTask<Player, Void, Void> {

        private PlayerDao playerDao;

        private UpdatePlayerAsyncTask(PlayerDao playerDao) {
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(Player... players) {
            playerDao.updatePlayer(players[0]);
            return null;
        }
    }

    private static class DeletePlayerAsyncTask extends AsyncTask<String, Void, Void> {

        private PlayerDao playerDao;

        private DeletePlayerAsyncTask(PlayerDao playerDao) {
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            playerDao.deletePlayer(strings[0]);
            return null;
        }
    }

    private static class DeleteAllPlayersAsyncTask extends AsyncTask<Void, Void, Void> {

        private PlayerDao playerDao;

        private DeleteAllPlayersAsyncTask(PlayerDao playerDao) {
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            playerDao.deleteAllPlayers();
            return null;
        }
    }

}
