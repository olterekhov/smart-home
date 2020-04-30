package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.elements.SmartHome;

import java.util.ArrayList;
import java.util.Collection;

public class EventCreatorProcessor {

    private SensorEvent event;
    private SmartHome smartHome;

    public EventCreatorProcessor(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    public Collection<EventProcessor> createEventProcessor() {
        Collection<EventProcessor> EventProcessors = new ArrayList<>();
        EventProcessors.add(new LightEventProcessor(event, smartHome));
        EventProcessors.add(new DoorEventProcessor(event, smartHome));
        EventProcessors.add(new HallDoorEventProcessor(event, smartHome));
        return EventProcessors;
    }

}
