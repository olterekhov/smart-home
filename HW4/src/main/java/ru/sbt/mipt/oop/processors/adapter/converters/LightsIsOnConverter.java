package ru.sbt.mipt.oop.processors.adapter.converters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.sensor.LightEventType;
import ru.sbt.mipt.oop.sensor.LightSensorEvent;

public class LightsIsOnConverter implements CCSensorEventConverter {
    private CCSensorEventConverter ccSensorEventConverter;

    public LightsIsOnConverter(CCSensorEventConverter ccSensorEventConverter) {
        this.ccSensorEventConverter = ccSensorEventConverter;
    }

    @Override
    public SensorEvent convert(CCSensorEvent ccSensorEvent) {
        if ((ccSensorEvent.getEventType().equals("LightIsOn"))) {
            return new LightSensorEvent(LightEventType.LIGHT_ON, ccSensorEvent.getObjectId());
        } else if (ccSensorEventConverter == null) {
            return null;
        }
        return ccSensorEventConverter.convert(ccSensorEvent);
    }
}
