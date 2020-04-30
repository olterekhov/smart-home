package ru.sbt.mipt.oop.processors.adapter.converters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.sensor.DoorEventType;
import ru.sbt.mipt.oop.sensor.DoorSensorEvent;

public class DoorIsClosedConverter implements CCSensorEventConverter {
    private CCSensorEventConverter ccSensorEventConverter;

    public DoorIsClosedConverter(CCSensorEventConverter ccSensorEventConverter) {
        this.ccSensorEventConverter = ccSensorEventConverter;
    }

    @Override
    public SensorEvent convert(CCSensorEvent ccSensorEvent) {
        if ((ccSensorEvent.getEventType().equals("DoorIsClosed")) || (ccSensorEvent.getEventType().equals("DoorIsLocked"))) {
            return new DoorSensorEvent(DoorEventType.DOOR_CLOSED, ccSensorEvent.getObjectId());
        } else if (ccSensorEventConverter == null) {
            return null;
        }
        return ccSensorEventConverter.convert(ccSensorEvent);
    }
}
