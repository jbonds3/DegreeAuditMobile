package cse5236.degreeauditmobile.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.UUID;

@Entity
public
class ProgressRequirements {

    @PrimaryKey @NonNull
    public String requirement;
    public ProgressRequirements(String requirement) {
        this.requirement = requirement;
    }

}