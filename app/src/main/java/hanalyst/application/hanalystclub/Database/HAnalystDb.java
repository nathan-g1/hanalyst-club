package hanalyst.application.hanalystclub.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import hanalyst.application.hanalystclub.Entity.GameType;
import hanalyst.application.hanalystclub.Entity.Notation;
import hanalyst.application.hanalystclub.Entity.Player;
import hanalyst.application.hanalystclub.Entity.Team;
import hanalyst.application.hanalystclub.Entity.User;
import hanalyst.application.hanalystclub.dao.GameTypeDao;
import hanalyst.application.hanalystclub.dao.NotationDao;
import hanalyst.application.hanalystclub.dao.PlayerDao;
import hanalyst.application.hanalystclub.dao.TeamDao;
import hanalyst.application.hanalystclub.dao.UserDao;

@Database(entities = {Player.class, Team.class, Notation.class, User.class, GameType.class}, version = 4, exportSchema = false)
public abstract class HAnalystDb extends RoomDatabase {
    public abstract PlayerDao playerDao();
    public abstract TeamDao teamDao();
    public abstract NotationDao notationDao();
    public abstract UserDao userDao();
    public abstract GameTypeDao gameTypeDao();

    private static HAnalystDb instance;
    public static synchronized HAnalystDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    HAnalystDb.class, "hanalystdb")
                    .fallbackToDestructiveMigration()
//                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private TeamDao teamDao;

        private PopulateDBAsyncTask(HAnalystDb hAnalystDb) {
            teamDao = hAnalystDb.teamDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            teamDao.insertTeam(new Team("5e3dbcf08b1e76683bd9afd4", "Andinet Mekuria", "Shibeshi", "1990-01-01T00:00:00.000Z", "Mekelle", "Rambo",17));
            return null;
        }
    }
}

