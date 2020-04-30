import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.*;

import java.util.Arrays;
import java.util.Collections;

public class DoorEventProcessorTest {
    @Test
    public void DoorEventProcessorOpen() {
        SmartHome smartHome = new SmartHome();

        String doorIdFirst = "1";
        Door doorFirst = new Door(true, doorIdFirst);

        String doorIdSecond = "2";
        Door doorSecond = new Door(false, doorIdSecond);

        smartHome.addRoom(new Room(Collections.emptyList(), Arrays.asList(doorFirst), "roomFirst"));
        smartHome.addRoom(new Room(Collections.emptyList(), Arrays.asList(doorSecond), "roomSecond"));

        SensorEvent eventFirst = new SensorEvent(SensorEventType.DOOR_OPEN, doorIdFirst);
        SensorEvent eventSecond = new SensorEvent(SensorEventType.DOOR_OPEN, doorIdSecond);

        DoorEvent DoorEventFirstProcessor = new DoorEventProcessor(eventFirst, smartHome);
        DoorEventFirstProcessor.processEvent();

        DoorEventProcessor DoorEventSecondProcessor = new DoorEventProcessor(eventSecond, smartHome);
        DoorEventSecondProcessor.processEvent();

        Assert.assertTrue(doorFirst.getStatus());
        Assert.assertTrue(doorSecond.getStatus());
    }

    @Test
    public void DoorEventProcessorClosed(){
        SmartHome smartHome = new SmartHome();

        String doorIdFirst = "1";
        Door doorFirst = new Door(true, doorIdFirst);

        String doorIdSecond = "2";
        Door doorSecond = new Door(false, doorIdSecond);

        smartHome.addRoom(new Room(Collections.emptyList(), Arrays.asList(doorFirst), "roomFirst"));
        smartHome.addRoom(new Room(Collections.emptyList(), Arrays.asList(doorSecond), "roomSecond"));

        SensorEvent newEventFirst = new SensorEvent(SensorEventType.DOOR_CLOSED, doorIdFirst);
        SensorEvent newEventSecond = new SensorEvent(SensorEventType.DOOR_CLOSED, doorIdSecond);

        DoorEventProcessor NewDoorEventFirstProcessor = new DoorEventProcessor(newEventFirst, smartHome);
        NewDoorEventFirstProcessor.processEvent();

        DoorEventProcessor NewDoorEventSecondProcessor = new DoorEventProcessor(newEventSecond, smartHome);
        NewDoorEventSecondProcessor.processEvent();

        Assert.assertFalse(doorFirst.getStatus());
        Assert.assertFalse(doorSecond.getStatus());
    }
}
