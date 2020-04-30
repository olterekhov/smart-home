package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.alarm.Alarm;

public class DangerAlarmStateActivate implements Command {

    private Alarm alarm;

    public DangerAlarmStateActivate(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.danger();
    }
}
