package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.processors.EventDecorator;
import ru.sbt.mipt.oop.processors.EventProcessorInterface;

import java.util.Collection;

public class StateHandler {

    private EventDecorator decorator;

    public StateHandler(EventDecorator decorator) {
        this.decorator = decorator;
    }

    public void stateHandle() {
        SensorEvent event = new NextSensorEventGetter().getNextEvent();
        while (event != null) {
            decorator.processEvent(event);
            event = new NextSensorEventGetter().getNextEvent();
        }
    }
}