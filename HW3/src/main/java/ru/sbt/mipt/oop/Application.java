package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.processors.*;
import ru.sbt.mipt.oop.readers.JsonSmartHomeReader;
import ru.sbt.mipt.oop.readers.Reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        Reader<SmartHome> jsonReader = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smartHome = jsonReader.read();

        // начинаем цикл обработки событий
        Alarm alarm = new Alarm("1");
        Collection<EventProcessorInterface> EventsProcessor = new ArrayList<>();
        EventsProcessor.add(new LightEventProcessor(smartHome));
        EventsProcessor.add(new DoorEventProcessor(smartHome));
        EventsProcessor.add(new HallDoorEventProcessor(smartHome));
        EventsProcessor.add(new AlarmEventProcessor(alarm));
        EventDecorator decorator = new EventDecorator(new EventProcessor(EventsProcessor), alarm);
        new StateHandler(decorator).stateHandle();
    }

    public static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
