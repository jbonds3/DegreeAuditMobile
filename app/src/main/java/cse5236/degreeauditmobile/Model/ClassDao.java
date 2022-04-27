package cse5236.degreeauditmobile.Model;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ClassDao {
    @Query("SELECT * FROM class")
    public LiveData<List<Class>> getAll();

    //get course
    @Query("SELECT * FROM class WHERE courseID = :courseID")
    public LiveData<Class> findByCourseID(String courseID);

    //get department
    @Query("SELECT department FROM class WHERE courseID = :courseID")
    public String getDepartment(String courseID);

    //get course number
    @Query("SELECT courseNumber FROM class WHERE courseID = :courseID")
    public String getCourseNumber(String courseID);

    @Query("SELECT EXISTS(SELECT * FROM class WHERE courseID = :courseID)")
    public boolean hasEntry(String courseID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Class classes);

    @Delete
    public void delete(Class c);

    @Query("DELETE FROM class")
    void deleteAll();
}