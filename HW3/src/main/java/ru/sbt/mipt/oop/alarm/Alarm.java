package ru.sbt.mipt.oop.alarm;

public class Alarm {

    private AlarmState alarmState;
    private String code;

    public Alarm(String code) {
        this.alarmState = new DeactivationAlarmState(this);
        this.code = code;
    }


    public void changeState(AlarmState state) {
        this.alarmState = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public AlarmState getState(){
        return alarmState;
    }

    public void danger() {
        alarmState.danger();
    }

    public void activate(String code) {
        alarmState.activate(code);
    }

    public void deactivate(String code) {
        alarmState.deactivate(code);
    }
}