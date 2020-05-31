package hanalyst.application.hanalystclub.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import hanalyst.application.hanalystclub.Entity.Game;

@Dao
public interface GameDao {
    @Insert
    Long insertGame(Game game);

    @Query("SELECT * FROM Game")
    LiveData<List<Game>> getAllGames();

    @Update
    void updateGame(Game game);

    @Delete
    void deleteGame(Game game);

    @Query("DELETE FROM Game")
    void deleteAllGames();
}
