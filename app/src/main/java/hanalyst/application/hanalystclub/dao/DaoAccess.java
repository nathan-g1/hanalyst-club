package hanalyst.application.hanalystclub.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import androidx.lifecycle.LiveData;
import hanalyst.application.hanalystclub.Entity.Player;

public interface DaoAccess {
    @Insert
    Long insertPlayer(Player player);

    @Query("SELECT * FROM PLAYER")
    LiveData<List<Player>> getAllPlayers();

    @Query("SELECT * FROM Player where id =:playerId")
    LiveData<Player> getAPlayer(int playerId);

    @Update
    void updatePlayer(Player player);

    @Delete
    void deletePlayer(Player player);


}
