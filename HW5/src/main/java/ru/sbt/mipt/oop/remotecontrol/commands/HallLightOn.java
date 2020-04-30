package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.Room;
import ru.sbt.mipt.oop.elements.SmartHome;

public class HallLightOn implements Command {
    private SmartHome smartHome;

    public HallLightOn(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("Hall")) {
                    room.execute(object_new -> {
                        if (object_new instanceof Light) {
                            Light light = (Light) object_new;
                            light.setOn(true);
                        }
                    });
                }
            }
        });
    }
}
