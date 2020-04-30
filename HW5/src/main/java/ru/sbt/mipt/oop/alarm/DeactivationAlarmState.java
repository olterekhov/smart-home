package ru.sbt.mipt.oop.alarm;

public class DeactivationAlarmState implements AlarmState {

    private Alarm alarm;

    public DeactivationAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }


    @Override
    public void activate(String code) {
        alarm.setCode(code);
        alarm.changeState(new ActivationAlarmState(alarm));
    }


    @Override
    public void deactivate(String code) {
        if (!alarm.getCode().equals(code)) {
            alarm.changeState(new DangerAlarmState(alarm));
        }
    }
}
