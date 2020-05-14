package hanalyst.application.hanalystclub.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import hanalyst.application.hanalystclub.Entity.Notation;

@Dao
public interface NotationDao {
    @Insert
    Long insertNotation(Notation notation);

    @Query("SELECT * FROM Notation")
    LiveData<List<Notation>> getAllNotations();

    @Query("SELECT * FROM Notation WHERE id =:notationId")
    LiveData<List<Notation>> getNotationById(String notationId);

    @Update
    void updateNotation(Notation notation);

    @Delete
    void deleteNotation(Notation notation);
}
