package ru.sbt.mipt.oop.processors.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.processors.EventProcessorInterface;
import ru.sbt.mipt.oop.processors.adapter.converters.CCSensorEventConverter;

public class AdapterEventHandler implements EventHandler {

    private EventProcessorInterface eventProcessor;
    private CCSensorEventConverter ccSensorEventConverter;

    public AdapterEventHandler(EventProcessorInterface eventProcessor, CCSensorEventConverter ccSensorEventConverter) {
        this.eventProcessor = eventProcessor;
        this.ccSensorEventConverter = ccSensorEventConverter;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        eventProcessor.processEvent(ccSensorEventConverter.convert(event));
    }
}
