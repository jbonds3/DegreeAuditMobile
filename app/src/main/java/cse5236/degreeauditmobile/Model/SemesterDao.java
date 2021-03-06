package cse5236.degreeauditmobile.Model;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface SemesterDao {
    //get semesters
    @Query("SELECT * FROM Semester")
    LiveData<List<Semester>> getAll();

    @Query("SELECT * FROM Semester WHERE username = :username")
    LiveData<List<Semester>> getByUser(String username);

    @Query("SELECT * FROM Semester WHERE username = :username")
    List<Semester> getByUserNow(String username);

    //get a semester
    @Query("SELECT * FROM Semester WHERE SemesterID LIKE :semesterID")
    LiveData<Semester> findBySemesterID(String semesterID);

    //get session
    @Query("SELECT Session FROM Semester WHERE SemesterID = :semesterID")
    String getSession(String semesterID);

    //get year
    @Query("SELECT Year FROM Semester WHERE Year = :year")
    String getYear(String year);

    //if semester
    @Query("SELECT EXISTS(SELECT * FROM Semester WHERE SemesterID = :semesterID AND username = :username)")
    boolean hasEntry(String semesterID, String username);

    //insert semester into table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Semester semester);

    @Insert
    void insertAll(Semester... semesters);

    @Delete
    void delete(Semester semester);

    @Query("DELETE FROM Semester")
    void deleteAll();

    @Transaction
    @Query("SELECT * FROM Class WHERE SemesterParentID = :semesterID AND ParentUsername = :username")
    public LiveData<List<Class>> getClassesFromSemester(String semesterID, String username);

    @Transaction
    @Query("SELECT * FROM Class WHERE SemesterParentID = :semesterID AND ParentUsername = :username")
    public List<Class> getClassesFromSemesterNow(String semesterID, String username);

}
