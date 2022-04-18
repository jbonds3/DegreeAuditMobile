package cse5236.degreeauditmobile.Model;

import android.text.format.Time;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
public
class User {

    @PrimaryKey @NonNull
    public String userName;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "time_stamp")
    public long timeStamp;

    @ColumnInfo(name = "recent_incorrect_attempts")
    public int recentIncorrectAttempts;

    public User(String userName, String password, long timeStamp, int recentIncorrectAttempts) {
        this.userName = userName;
        this.password = password;
        this.timeStamp = timeStamp;
        this.recentIncorrectAttempts = recentIncorrectAttempts;
    }
    @Ignore
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.timeStamp = System.currentTimeMillis();
        this.recentIncorrectAttempts = 0;
    }
}
