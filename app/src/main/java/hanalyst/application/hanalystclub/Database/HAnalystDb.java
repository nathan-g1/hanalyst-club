package hanalyst.application.hanalystclub.Database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import hanalyst.application.hanalystclub.Entity.Player;
import hanalyst.application.hanalystclub.dao.DaoAccess;

@Database(entities = {Player.class}, version = 1, exportSchema = false)
public abstract class HAnalystDb extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}

