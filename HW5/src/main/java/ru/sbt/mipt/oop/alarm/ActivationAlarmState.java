package ru.sbt.mipt.oop.alarm;

public class ActivationAlarmState implements AlarmState {

    private Alarm alarm;

    public ActivationAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }


    @Override
    public void activate(String code) {
    }


    @Override
    public void deactivate(String code) {
        if (code.equals(alarm.getCode())) {
            alarm.changeState(new DeactivationAlarmState(alarm));
        } else {
            alarm.changeState(new DangerAlarmState(alarm));
        }
    }
}
