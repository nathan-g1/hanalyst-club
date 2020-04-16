package hanalyst.application.hanalystclub.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import hanalyst.application.hanalystclub.Entity.Player;
import hanalyst.application.hanalystclub.dao.DaoAccess;

@Database(entities = {Player.class}, version = 1, exportSchema = false)
public abstract class HAnalystDb extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}

