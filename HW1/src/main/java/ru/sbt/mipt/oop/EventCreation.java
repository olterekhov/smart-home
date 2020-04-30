package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.processing.ProcessingEvent;

import java.util.Collection;

public class EventCreation {
    private SensorEvent event;
    private Collection<ProcessingEvent> processingEvents;

    public EventCreation(SensorEvent event, Collection<ProcessingEvent> processingEvents) {
        this.event = event;
        this.processingEvents = processingEvents;
    }

    public SensorEvent processing() {
        System.out.println("Got event: " + event);
        for (ProcessingEvent processingEvent : processingEvents) {
            processingEvent.processEvent();
        }
        return new NextSensorEvent().getNextEvent();
    }
}