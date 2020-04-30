package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.alarm.Alarm;

public class AlarmActivate implements Command {

    private Alarm alarm;

    public AlarmActivate(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.activate(alarm.getCode());
    }
}
