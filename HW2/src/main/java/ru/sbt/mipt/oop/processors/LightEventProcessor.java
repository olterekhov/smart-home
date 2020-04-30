package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.Room;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {

    private SmartHome smartHome;
    private SensorEvent event;

    public LightEventProcessor(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }


    @Override
    public void processEvent() {
        smartHome.execute(object -> {
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                if (object instanceof Room) {
                    Room room = (Room) object;
                    room.execute((objectNew) -> {
                        if (objectNew instanceof Light) {
                            Light light = (Light) objectNew;
                            updateLightState(event, light, room);
                        }
                    });
                }
            }
        });
    }


    private void updateLightState(SensorEvent event, Light light, Room room) {
        if (light.getId().equals(event.getObjectId())) {
            if (event.getType() == LIGHT_ON) {
                light.setOn(true);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
            } else {
                light.setOn(false);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
            }
        }
    }
}
