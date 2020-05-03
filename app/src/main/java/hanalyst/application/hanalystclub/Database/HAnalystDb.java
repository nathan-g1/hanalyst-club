package hanalyst.application.hanalystclub.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import hanalyst.application.hanalystclub.Entity.Notation;
import hanalyst.application.hanalystclub.Entity.Player;
import hanalyst.application.hanalystclub.Entity.Team;
import hanalyst.application.hanalystclub.dao.NotationDao;
import hanalyst.application.hanalystclub.dao.PlayerDao;
import hanalyst.application.hanalystclub.dao.TeamDao;

@Database(entities = {Player.class, Team.class, Notation.class}, version = 2, exportSchema = false)
public abstract class HAnalystDb extends RoomDatabase {
    public abstract PlayerDao daoAccess();
    public abstract TeamDao teamDao();
    public abstract NotationDao notationDao();
}

