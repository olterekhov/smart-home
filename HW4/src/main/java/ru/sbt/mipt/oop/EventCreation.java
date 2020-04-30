package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.processors.EventProcessorInterface;

import java.util.Collection;

public class EventCreation {
    private SensorEvent event;
    private Collection<EventProcessorInterface> eventProcessorInterfaces;

    public EventCreation(SensorEvent event, Collection<EventProcessorInterface> eventProcessorInterfaces) {
        this.event = event;
        this.eventProcessorInterfaces = eventProcessorInterfaces;
    }

    public void process() {
        System.out.println("Got event: " + event);
        for (EventProcessorInterface EventProcessorInterface : eventProcessorInterfaces) {
            EventProcessorInterface.processEvent();
        }
    }
}