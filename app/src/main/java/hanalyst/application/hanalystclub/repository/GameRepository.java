package hanalyst.application.hanalystclub.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import hanalyst.application.hanalystclub.Database.HAnalystDb;
import hanalyst.application.hanalystclub.Entity.Game;
import hanalyst.application.hanalystclub.dao.GameDao;

public class GameRepository {
    private GameDao gameDao;
    private LiveData<List<Game>> allGames;

    public GameRepository(Application application) {
        HAnalystDb hAnalystDb = HAnalystDb.getInstance(application);
        gameDao = hAnalystDb.gameDao();
        allGames = gameDao.getAllGames();
    }

    public void insert(Game game) {
        new InsertGameAsyncTask(gameDao).execute(game);
    }

    public LiveData<List<Game>> getAllGames() {
        return allGames;
    }

    public void update(Game game) {
        new UpdateGameAsyncTask(gameDao).execute(game);
    }

    public void deleteAllGames() {
        new DeleteAllGameAsyncTask(gameDao).execute();
    }

    private class InsertGameAsyncTask extends AsyncTask<Game, Void, Void> {

        private GameDao gameDao;

        public InsertGameAsyncTask(GameDao gameDao) {
            this.gameDao = gameDao;
        }

        @Override
        protected Void doInBackground(Game... games) {
            gameDao.insertGame(games[0]);
            return null;
        }
    }

    private class UpdateGameAsyncTask extends AsyncTask<Game, Void, Void> {

        private GameDao gameDao;

        public UpdateGameAsyncTask(GameDao gameDao) {
            this.gameDao = gameDao;
        }

        @Override
        protected Void doInBackground(Game... games) {
            gameDao.updateGame(games[0]);
            return null;
        }
    }

    private class DeleteAllGameAsyncTask extends AsyncTask<Void, Void, Void> {
        private GameDao gameDao;

        public DeleteAllGameAsyncTask(GameDao gameDao) {
            this.gameDao = gameDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            gameDao.deleteAllGames();
            return null;
        }
    }
}
