package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.Door;
import ru.sbt.mipt.oop.elements.Room;
import ru.sbt.mipt.oop.sensor.DoorEventType;
import ru.sbt.mipt.oop.sensor.DoorSensorEvent;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessorInterface {

    private SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    @Override
    public void processEvent(SensorEvent event) {
        smartHome.execute(object -> {
            if (event instanceof DoorSensorEvent) {
                if (object instanceof Room) {
                    Room room = (Room) object;
                    room.execute(object_new -> {
                        if (object_new instanceof Door) {
                            Door door = (Door) object_new;
                            updateDoorState((DoorSensorEvent) event, door, room);
                        }
                    });
                }
            }
        });
    }


    private void updateDoorState(DoorSensorEvent event, Door door, Room room) {
        if (door.getId().equals(event.getObjectId())) {
            if (event.getType() == DoorEventType.DOOR_OPEN) {
                door.setOpen(true);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
            } else {
                door.setOpen(false);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
            }
        }
    }
}
