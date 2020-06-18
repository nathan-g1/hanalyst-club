package hanalyst.application.hanalystclub.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import hanalyst.application.hanalystclub.Database.HAnalystDb;
import hanalyst.application.hanalystclub.Entity.GameType;
import hanalyst.application.hanalystclub.dao.GameTypeDao;

public class GameTypeRepository {
    private GameTypeDao gameTypeDao;
    private LiveData<List<GameType>> allGameTypes;

    public GameTypeRepository(Application application) {
        HAnalystDb hAnalystDb = HAnalystDb.getInstance(application);
        gameTypeDao = hAnalystDb.gameTypeDao();
        allGameTypes = gameTypeDao.getAllGameTypes();
    }

    public LiveData<List<GameType>> getAllGameTypes() {
        return allGameTypes;
    }

    public void insert(GameType gameType) {
        new InsertGameTypeAsyncTask(gameTypeDao).execute(gameType);
    }

    public void deleteAllGameTypes() {
        new DeleteAllGameTypesAsyncTask(gameTypeDao).execute();
    }

    private class InsertGameTypeAsyncTask extends AsyncTask<GameType, Void, Void> {
        private GameTypeDao gameTypeDao;

        private InsertGameTypeAsyncTask(GameTypeDao gameTypeDao) {
            this.gameTypeDao = gameTypeDao;
        }

        @Override
        protected Void doInBackground(GameType... gameTypes) {
            gameTypeDao.insertGameType(gameTypes[0]);
            return null;
        }
    }

    private static class DeleteAllGameTypesAsyncTask extends AsyncTask<Void, Void, Void> {

        private GameTypeDao gameTypeDao;

        private DeleteAllGameTypesAsyncTask(GameTypeDao gameTypeDao) {
            this.gameTypeDao = gameTypeDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            gameTypeDao.deleteAllGameTypes();
            return null;
        }
    }
}
