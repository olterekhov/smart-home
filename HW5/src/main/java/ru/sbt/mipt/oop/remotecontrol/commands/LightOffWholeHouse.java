package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.SmartHome;

public class LightOffWholeHouse implements Command {

    private SmartHome smartHome;

    public LightOffWholeHouse(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(false);
            }
        });
    }
}
