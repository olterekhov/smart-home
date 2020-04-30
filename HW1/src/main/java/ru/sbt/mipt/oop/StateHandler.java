package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.processing.ProcessingEvent;

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
            Collection<ProcessingEvent> processingEvents = new ru.sbt.mipt.oop.EventProcessing(event, smartHome).createEventProcessing();
            event = new EventCreation(event, processingEvents).processing();
        }
    }
}