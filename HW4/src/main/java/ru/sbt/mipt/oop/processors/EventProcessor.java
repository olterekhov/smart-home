package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.SensorEvent;

import java.util.Collection;

public class EventProcessor implements EventProcessorInterface {
    private Collection<EventProcessorInterface> EventsProcessorCollection;

    public EventProcessor(Collection<EventProcessorInterface> EventsProcessorCollection) {
        this.EventsProcessorCollection = EventsProcessorCollection;
    }

    @Override
    public void processEvent(SensorEvent event) {
        for(EventProcessorInterface eventProcessor: EventsProcessorCollection){
            eventProcessor.processEvent(event);
        }
    }
}
