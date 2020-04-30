package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.processors.EventCreatorProcessor;
import ru.sbt.mipt.oop.processors.EventProcessor;

import java.util.Collection;

public class StateHandler {

    private SensorEvent event;
    private SmartHome smartHome;

    public StateHandler(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    public void stateHandle() {
        while (event != null) {
            Collection<EventProcessor> EventsProcessors = new EventCreatorProcessor(event, smartHome).createEventProcessor();
            new EventCreation(event, EventsProcessors).process();
            event = new NextSensorEventGetter().getNextEvent();
        }
    }
}