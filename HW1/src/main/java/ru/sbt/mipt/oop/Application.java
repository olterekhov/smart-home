package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.readers.JsonReader;
import ru.sbt.mipt.oop.readers.Reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        Reader<SmartHome> jsonReader = new JsonReader("smart-home-1.js");
        SmartHome smartHome = jsonReader.read();
        // начинаем цикл обработки событий
        NextEventGetter<SensorEvent> sensorEvent = new NextSensorEvent();
        SensorEvent event = sensorEvent.getNextEvent();
        new StateHandler(event, smartHome).stateHandle();
    }

    public static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
