package hanalyst.application.hanalystclub.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import hanalyst.application.hanalystclub.Entity.Team;

@Dao
public interface TeamDao {
    @Insert
    Long insertTeam(Team team);

    @Query("SELECT * FROM Team")
    LiveData<List<Team>> getAllTeams();

    @Query("SELECT * FROM Team WHERE id =:teamId")
    LiveData<Team> getATeam(String teamId);

    @Update
    void updateTeam(Team team);

    @Delete
    void deleteTeam(Team team);
}
