package cse5236.degreeauditmobile.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RequirementClassDao {
    @Query("SELECT * FROM requirementclass")
    List<RequirementClass> getAll();

    @Query("SELECT * FROM requirementclass WHERE requirement = :reqName")
    List<RequirementClass> findByRequirement(String reqName);

    @Insert
    void insert(RequirementClass req_class);

    @Delete
    void delete(RequirementClass req_class);
}