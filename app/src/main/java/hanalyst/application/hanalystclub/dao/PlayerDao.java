package hanalyst.application.hanalystclub.dao;



import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import hanalyst.application.hanalystclub.Entity.Player;
@Dao
public interface PlayerDao {
    @Insert
    Long insertPlayer(Player player);

    @Query("SELECT * FROM Player")
    LiveData<List<Player>> getAllPlayers();

    @Query("SELECT * FROM Player where id =:playerId")
    LiveData<Player> getAPlayer(String playerId);

    @Update
    void updatePlayer(Player player);

    @Query("DELETE FROM Player where id =:playerId")
    void deletePlayer(String playerId);

    @Query("DELETE FROM Player")
    void deleteAllPlayers();

}
