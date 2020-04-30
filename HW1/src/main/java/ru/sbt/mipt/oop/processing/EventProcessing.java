package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.processing.DoorEventProcessing;
import ru.sbt.mipt.oop.processing.LightEventProcessing;
import ru.sbt.mipt.oop.processing.ProcessingEvent;

import java.util.ArrayList;
import java.util.Collection;

public class EventProcessing {

    private SensorEvent event;
    private SmartHome smartHome;

    public EventProcessing(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    public Collection<ProcessingEvent> createEventProcessing() {
        Collection<ProcessingEvent> processingEvents = new ArrayList<>();
        processingEvents.add(new LightEventProcessing(event, smartHome));
        processingEvents.add(new DoorEventProcessing(event, smartHome));
        return processingEvents;
    }

}