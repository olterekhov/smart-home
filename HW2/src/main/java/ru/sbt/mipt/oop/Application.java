package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.readers.JsonSmartHomeReader;
import ru.sbt.mipt.oop.readers.Reader;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        Reader<SmartHome> jsonReader = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smartHome = jsonReader.read();
        // начинаем цикл обработки событий
        NextEventGetter<SensorEvent> sensorEvent = new NextSensorEventGetter();
        SensorEvent event = sensorEvent.getNextEvent();
        new StateHandler(event, smartHome).stateHandle();
    }

    public static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
