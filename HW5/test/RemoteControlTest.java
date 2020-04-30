package remoteControlTest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.SpringConfigurationFile;
import ru.sbt.mipt.oop.alarm.ActivationAlarmState;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.DangerAlarmState;
import ru.sbt.mipt.oop.homeelements.Door;
import ru.sbt.mipt.oop.homeelements.Light;
import ru.sbt.mipt.oop.homeelements.Room;
import ru.sbt.mipt.oop.homeelements.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.RemoteControl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class RemoteControlTest {

    @Test
    public void TestLightOffAndOnWholeHouse() {
        boolean flag = false;
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationFile.class);
        SmartHome smartHome = context.getBean(SmartHome.class);
        RemoteControl remoteControl = (RemoteControl) context.getBean("remoteControlRealisation");
        remoteControl.onButtonPressed("A");
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getStatus()) {
                    flag = true;
                }
            }
        }
        assertFalse(flag);
        flag = false;
        remoteControl.onButtonPressed("3");
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (!light.getStatus()) {
                    flag = true;
                }
            }
        }
        assertFalse(flag);
    }

    @Test
    public void TestActivationAlarmState() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationFile.class);
        Alarm alarm = context.getBean(Alarm.class);
        RemoteControl remoteControl = (RemoteControl) context.getBean("remoteControlRealisation");

        remoteControl.onButtonPressed("1");
        assertTrue(alarm.getState() instanceof ActivationAlarmState);
    }

    @Test
    public void TestDangerAlarmState() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationFile.class);
        Alarm alarm = context.getBean(Alarm.class);
        RemoteControl remoteControl = (RemoteControl) context.getBean("remoteControlRealisation");

        remoteControl.onButtonPressed("2");
        assertTrue(alarm.getState() instanceof DangerAlarmState);
    }

    @Test
    public void TestHallDoorClosed() {
        boolean flag = false;
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationFile.class);
        SmartHome smartHome = context.getBean(SmartHome.class);
        RemoteControl remoteControl = (RemoteControl) context.getBean("remoteControlRealisation");
        remoteControl.onButtonPressed("B");
        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("Hall")) {
                for (Door door : room.getDoors()) {
                    if (door.getStatus()) {
                        flag = true;
                    }
                }
            }
        }
        assertFalse(flag);
    }

    @Test
    public void TestHallLightOn() {
        boolean flag = false;
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationFile.class);
        SmartHome smartHome = context.getBean(SmartHome.class);
        RemoteControl remoteControl = (RemoteControl) context.getBean("remoteControlRealisation");
        remoteControl.onButtonPressed("C");
        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("Hall")) {
                for (Light light : room.getLights()) {
                    if (!light.getStatus()) {
                        flag = true;
                    }
                }
            }
        }
        assertFalse(flag);
    }
}
