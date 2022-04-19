package cse5236.degreeauditmobile.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RequirementClassDao {
    @Query("SELECT * FROM requirementclass")
    List<RequirementClass> getAll();

    @Query("SELECT * FROM requirementclass")
    LiveData<List<RequirementClass>> getAllLive();

    @Query("SELECT * FROM requirementclass WHERE requirement = :reqName")
    List<RequirementClass> findByRequirement(String reqName);

    @Query("SELECT * FROM requirementclass WHERE requirement = :reqName")
    LiveData<List<RequirementClass>> findByRequirementLive(String reqName);

    @Insert
    void insert(RequirementClass req_class);

    @Delete
    void delete(RequirementClass req_class);

    @Update
    void update(RequirementClass req_class);
}