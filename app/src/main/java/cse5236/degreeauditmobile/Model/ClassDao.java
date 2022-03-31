package cse5236.degreeauditmobile.Model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ClassDao {
    @Query("SELECT * FROM class")
    List<Class> getAll();

    //get course
    @Query("SELECT * FROM class WHERE courseID = :courseID")
    Class findByCourseID(String courseID);

    //get department
    @Query("SELECT department FROM class WHERE courseID = :courseID")
    String getDepartment(String courseID);

    //get course number
    @Query("SELECT courseNumber FROM class WHERE courseID = :courseID")
    String getCourseNumber(String courseID);

    @Query("SELECT EXISTS(SELECT * FROM class WHERE courseID = :courseID)")
    boolean hasEntry(String courseID);

    @Insert
    void insertAll(Class... classes);

    @Update
    void UpdatePassword(Class c);

    @Delete
    void delete(Class c);
}