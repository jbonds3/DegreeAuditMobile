package cse5236.degreeauditmobile;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public
class User {

    @PrimaryKey @NonNull
    public String userName;

    @ColumnInfo(name = "password")
    public String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
