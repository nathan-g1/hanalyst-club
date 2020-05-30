package hanalyst.application.hanalystclub.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import hanalyst.application.hanalystclub.Entity.GameType;

@Dao
public interface GameTypeDao {
    @Insert
    Long insertGameType(GameType gameType);

    @Query("SELECT * FROM GameType")
    LiveData<List<GameType>> getAllGameTypes();

    @Query("DELETE FROM GameType")
    void deleteAllGameTypes();

}
