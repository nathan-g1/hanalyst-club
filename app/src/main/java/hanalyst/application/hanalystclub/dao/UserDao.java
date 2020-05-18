package hanalyst.application.hanalystclub.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import hanalyst.application.hanalystclub.Entity.User;

@Dao
public interface UserDao {
    @Insert
    Long insertUser(User user);

    @Query("SELECT * FROM User")
    LiveData<User> getCurrentUser();

    @Delete
    void deleteUser(User user);
}
