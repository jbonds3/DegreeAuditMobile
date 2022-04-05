package cse5236.degreeauditmobile.Model;

import android.os.Build;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

public class ClassListConverter {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public List<Class> storedStringToClasses(String value) {
        List<String> classStrList = Arrays.asList(value.split("\\s*,\\s*"));

        List<Class> classList = new ArrayList<>();

        if (classList.size() > 1) {
            for (String c : classStrList) {
                classList.add(new Class(c, "none"));
            }
        }

        return classList;
    }

    @TypeConverter
    public String classesToStoredString(List<Class> classes) {
        String value = "";

        if (classes.size() > 1) {
            for (Class c : classes) {
                value += c.title() + ",";
            }
        }


        return value;
    }
}