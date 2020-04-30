package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.HomeRemoteControl;
import ru.sbt.mipt.oop.remotecontrol.RemoteControl;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlRegistry;
import ru.sbt.mipt.oop.remotecontrol.commands.*;

@Configuration
public class RemoteControlConfiguration {

    @Bean
    RemoteControl remoteControlRealisation(LightOffWholeHouse lightOffWholeHouse,
                                           HallDoorClosed hallDoorClosed,
                                           HallLightOn hallLightOn,
                                           AlarmActivate alarmActivate,
                                           DangerAlarmStateActivate dangerAlarmStateActivate,
                                           LightOnWholeHouse lightOnWholeHouse,
                                           RemoteControlRegistry remoteControlRegistry) {
        HomeRemoteControl remoteControl = new HomeRemoteControl();
        remoteControl.addRemoteControl("A", lightOffWholeHouse);
        remoteControl.addRemoteControl("B", hallDoorClosed);
        remoteControl.addRemoteControl("C", hallLightOn);
        remoteControl.addRemoteControl("1", alarmActivate);
        remoteControl.addRemoteControl("2", dangerAlarmStateActivate);
        remoteControl.addRemoteControl("3", lightOnWholeHouse);
        remoteControlRegistry.registerRemoteControl(remoteControl, "111");
        return remoteControl;
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry() {
        return new RemoteControlRegistry();
    }

    @Bean
    LightOffWholeHouse lightOffWholeHouse(SmartHome smartHome) {
        return new LightOffWholeHouse(smartHome);
    }

    @Bean
    HallDoorClosed hallDoorClosed(SmartHome smartHome) {
        return new HallDoorClosed(smartHome);
    }

    @Bean
    HallLightOn hallLightOn(SmartHome smartHome) {
        return new HallLightOn(smartHome);
    }

    @Bean
    AlarmActivate alarmActivate(Alarm alarm) {
        return new AlarmActivate(alarm);
    }

    @Bean
    DangerAlarmStateActivate dangerAlarmStateActivate(Alarm alarm) {
        return new DangerAlarmStateActivate(alarm);
    }

    @Bean
    LightOnWholeHouse lightOnWholeHouse(SmartHome smartHome) {
        return new LightOnWholeHouse(smartHome);
    }
}
