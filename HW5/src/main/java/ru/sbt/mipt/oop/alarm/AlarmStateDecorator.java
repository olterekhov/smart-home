package ru.sbt.mipt.oop.alarm;

public class AlarmStateDecorator implements AlarmState{
    private AlarmState alarmState;

    public AlarmStateDecorator(AlarmState alarmState) {
        this.alarmState = alarmState;
    }

    @Override
    public void activate(String code) {
        alarmState.activate(code);
    }

    @Override
    public void deactivate(String code) {
        alarmState.deactivate(code);
    }
}