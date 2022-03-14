package cse5236.degreeauditmobile;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE userName = :username")
    User findByName(String username);

    @Query("SELECT password FROM user WHERE userName = :username")
    String getPassword(String username);

    @Query("SELECT EXISTS(SELECT * FROM user WHERE userName = :username)")
    boolean hasEntry(String username);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}