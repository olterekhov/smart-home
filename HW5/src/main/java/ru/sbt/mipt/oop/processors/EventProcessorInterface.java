package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.SensorEvent;

public interface EventProcessorInterface {
    void processEvent(SensorEvent event);
}
