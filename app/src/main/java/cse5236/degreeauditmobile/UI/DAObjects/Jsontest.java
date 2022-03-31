package cse5236.degreeauditmobile.UI.DAObjects;

import android.os.Build;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import androidx.annotation.RequiresApi;

public class Jsontest {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args) {
        try {
            File filename= new File("app/src/main/java/cse5236/degreeauditmobile/UI/DAObjects/classList.json");
            JsonElement fileElem = JsonParser.parseReader(new FileReader(filename.getAbsolutePath()));
            JsonObject fileObj = fileElem.getAsJsonObject();

            JsonArray x = fileObj.get("CSE").getAsJsonObject().get("1223").getAsJsonArray();
            System.out.println(x.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
