package ru.sbt.mipt.oop.alarm;

public class DangerAlarmState implements AlarmState {

    private Alarm alarm;

    public DangerAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (code.equals(alarm.getCode())) {
            alarm.changeState(new DeactivationAlarmState(alarm));
        }
    }

    @Override
    public void danger() {
    }
}
