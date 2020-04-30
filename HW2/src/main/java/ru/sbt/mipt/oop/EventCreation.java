package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.processors.EventProcessor;

import java.util.Collection;

public class EventCreation {
    private SensorEvent event;
    private Collection<EventProcessor> EventProcessors;

    public EventCreation(SensorEvent event, Collection<EventProcessor> EventProcessors) {
        this.event = event;
        this.EventProcessors = EventProcessors;
    }

    public void process() {
        System.out.println("Got event: " + event);
        for (EventProcessor EventProcessor : EventProcessors) {
            EventProcessor.processEvent();
        }
    }
}