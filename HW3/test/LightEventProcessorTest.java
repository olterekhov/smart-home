import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.*;

import java.util.Arrays;
import java.util.Collections;

public class LightEventProcessorTest {
    @Test
    public void LightEventProcessorOn() {
        SmartHome smartHome = new SmartHome();

        String lightIdFirst = "1";
        Light lightFirst = new Light(lightIdFirst, true);

        String lightIdSecond = "2";
        Light lightSecond = new Light(lightIdSecond, false);

        smartHome.addRoom(new Room(Arrays.asList(lightFirst), Collections.emptyList(), "lightFirst"));
        smartHome.addRoom(new Room(Arrays.asList(lightSecond), Collections.emptyList(), "lightSecond"));

        SensorEvent eventFirst = new SensorEvent(SensorEventType.LIGHT_ON, lightIdFirst);
        SensorEvent eventSecond = new SensorEvent(SensorEventType.LIGHT_ON, lightIdSecond);

        LightEventProcessor LightEventProcessorFirst = new LightEventProcessor(eventFirst, smartHome);
        LightEventProcessorFirst.processEvent();

        LightEventProcessor LightEventProcessorSecond = new LightEventProcessor(eventSecond, smartHome);
        LightEventProcessorSecond.processEvent();

        Assert.assertTrue(lightFirst.getStatus());
        Assert.assertTrue(lightSecond.getStatus());
    }

    @Test
    public void LightEventProcessorOff() {
        SmartHome smartHome = new SmartHome();

        String lightIdFirst = "1";
        Light lightFirst = new Light(lightIdFirst, true);

        String lightIdSecond = "2";
        Light lightSecond = new Light(lightIdSecond, false);

        smartHome.addRoom(new Room(Arrays.asList(lightFirst), Collections.emptyList(), "lightFirst"));
        smartHome.addRoom(new Room(Arrays.asList(lightSecond), Collections.emptyList(), "lightSecond"));

        SensorEvent newEventFirst = new SensorEvent(SensorEventType.LIGHT_OFF, lightIdFirst);
        SensorEvent newEventSecond = new SensorEvent(SensorEventType.LIGHT_OFF, lightIdSecond);

        LightEventProcessor NewLightEventFirstProcessor = new LightEventProcessor(newEventFirst, smartHome);
        NewLightEventFirstProcessor.processEvent();

        LightEventProcessor NewLightEventSecondProcessor = new LightEventProcessor(newEventSecond, smartHome);
        NewLightEventSecondProcessor.processEvent();

        Assert.assertFalse(lightFirst.getStatus());
        Assert.assertFalse(lightSecond.getStatus());

    }
}
