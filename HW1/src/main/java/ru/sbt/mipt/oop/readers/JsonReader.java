package ru.sbt.mipt.oop.readers;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader implements Reader<SmartHome> {
    private String path;

    public JsonReader(String path) {
        this.path = path;
    }

    // считываем состояние дома из файла
    @Override
    public SmartHome read() {
        try {
            Gson gson = new Gson();
            String json = new String(Files.readAllBytes(Paths.get(path)));
            return gson.fromJson(json, SmartHome.class);
        } catch (IOException error) {
            System.err.println(error);
        }
        return null;
    }
}
