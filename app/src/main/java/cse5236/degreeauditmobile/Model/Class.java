package cse5236.degreeauditmobile.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class Class {

    @PrimaryKey @NonNull
    public String fullClassName;

    @ColumnInfo(name = "Year + Session")
    public String dateTaken;

    public Class(String fullClassName, String dateTaken) {
        this.fullClassName = fullClassName;
        this.dateTaken = dateTaken;
    }
}