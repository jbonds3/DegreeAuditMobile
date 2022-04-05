package cse5236.degreeauditmobile.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProgressRequirementsDao {
    @Query("SELECT * FROM progressrequirements")
    List<ProgressRequirements> getAll();

    @Query("SELECT * FROM progressrequirements WHERE requirement = :reqName")
    ProgressRequirements findByName(String reqName);


    @Query("SELECT EXISTS(SELECT * FROM progressrequirements WHERE requirement = :reqName)")
    boolean hasEntry(String reqName);

    @Insert
    void insert(ProgressRequirements progress);

    @Delete
    void delete(ProgressRequirements progress);
}