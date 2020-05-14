package hanalyst.application.hanalystclub.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import hanalyst.application.hanalystclub.Entity.Notation;
import hanalyst.application.hanalystclub.Entity.Player;
import hanalyst.application.hanalystclub.Entity.Team;
import hanalyst.application.hanalystclub.dao.NotationDao;
import hanalyst.application.hanalystclub.dao.PlayerDao;
import hanalyst.application.hanalystclub.dao.TeamDao;

@Database(entities = {Player.class, Team.class, Notation.class}, version = 2, exportSchema = false)
public abstract class HAnalystDb extends RoomDatabase {
    public abstract PlayerDao playerDao();
    public abstract TeamDao teamDao();
    public abstract NotationDao notationDao();

    private static HAnalystDb instance;
    public static synchronized HAnalystDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, HAnalystDb.class, "hanalystdb")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

