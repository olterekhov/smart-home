package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.SmartHome;

public class LightOnWholeHouse implements Command {

    private SmartHome smartHome;

    public LightOnWholeHouse(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(true);
            }
        });
    }
}
