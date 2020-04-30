package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.Door;
import ru.sbt.mipt.oop.elements.Room;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {

    private SmartHome smartHome;
    private SensorEvent event;

    public DoorEventProcessor(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }


    @Override
    public void processEvent() {
        smartHome.execute(object -> {
            if (event.getType() == DOOR_CLOSED || event.getType() == DOOR_OPEN) {
                if (object instanceof Room) {
                    Room room = (Room) object;
                    room.execute(object_new -> {
                        if (object_new instanceof Door) {
                            Door door = (Door) object_new;
                            updateDoorState(event, door, room);
                        }
                    });
                }
            }
        });
    }


    private void updateDoorState(SensorEvent event, Door door, Room room) {
        if (door.getId().equals(event.getObjectId())) {
            if (event.getType() == DOOR_OPEN) {
                door.setOpen(true);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
            } else {
                door.setOpen(false);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
            }
        }
    }
}
