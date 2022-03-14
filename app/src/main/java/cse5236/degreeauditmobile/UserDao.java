package cse5236.degreeauditmobile;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE userName LIKE :username AND password LIKE :password LIMIT 1")
    User findByName(String username, String password);

    @Query("SELECT password FROM user WHERE userName LIKE :username")
    String getPassword(String username);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}