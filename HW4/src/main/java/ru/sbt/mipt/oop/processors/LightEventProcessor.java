package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.Room;
import ru.sbt.mipt.oop.sensor.LightEventType;
import ru.sbt.mipt.oop.sensor.LightSensorEvent;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessorInterface {

    private SmartHome smartHome;
    private SensorEvent event;

    public LightEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    @Override
    public void processEvent(SensorEvent event) {
        smartHome.execute(object -> {
            if (event instanceof LightSensorEvent) {
                if (object instanceof Room) {
                    Room room = (Room) object;
                    room.execute((objectNew) -> {
                        if (objectNew instanceof Light) {
                            Light light = (Light) objectNew;
                            updateLightState((LightSensorEvent) event, light, room);
                        }
                    });
                }
            }
        });
    }


    private void updateLightState(LightSensorEvent event, Light light, Room room) {
        if (light.getId().equals(event.getObjectId())) {
            if (event.getType() == LightEventType.LIGHT_OFF) {
                light.setOn(true);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
            } else {
                light.setOn(false);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
            }
        }
    }
}
