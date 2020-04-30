package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.processors.*;
import ru.sbt.mipt.oop.processors.adapter.AdapterEventHandler;
import ru.sbt.mipt.oop.processors.adapter.converters.*;
import ru.sbt.mipt.oop.readers.JsonSmartHomeReader;

import java.util.Collection;

@Configuration
@Import(RemoteControlConfiguration.class)
public class SpringConfiguration {

    @Bean
    SensorEventsManager sensorEventsManager(EventProcessorInterface eventProcessor, CCSensorEventConverter ccSensorEventConverter){
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new AdapterEventHandler(eventProcessor, ccSensorEventConverter));
        return sensorEventsManager;
    }

    @Bean
    public EventProcessorInterface eventProcessor(Collection<EventProcessorInterface> collectionEventProcess) {
        return new EventDecorator(new EventProcessor(collectionEventProcess), alarm());
    }

    @Bean
    EventProcessorInterface lightEventProcessorProcessor(){
        return new LightEventProcessor(smartHome());
    }

    @Bean
    EventProcessorInterface hallDoorEventProcessor(){
        return new HallDoorEventProcessor(smartHome());
    }

    @Bean
    EventProcessorInterface doorEventProcessor(){
        return new DoorEventProcessor(smartHome());
    }

    @Bean
    EventProcessorInterface processingAlarmEvent(){
        return new AlarmEventProcessor(alarm());
    }

    @Bean
    SmartHome smartHome() {
        return new JsonSmartHomeReader().read();
    }

    @Bean
    Alarm alarm() {
        return new Alarm("123");
    }

    @Bean
    public CCSensorEventConverter ccSensorEventConverter(LightsIsOffConverter lightsIsOffConverter) {
        return new LightsIsOnConverter(lightsIsOffConverter);
    }

    @Bean
    public LightsIsOffConverter lightsIsOffConverter(DoorIsOpenedConverter doorIsOpenedConverter) {
        return new LightsIsOffConverter(doorIsOpenedConverter);
    }

    @Bean
    public DoorIsOpenedConverter doorIsOpenedConverter(DoorIsClosedConverter doorIsClosedConverter) {
        return new DoorIsOpenedConverter(doorIsClosedConverter);
    }

    @Bean
    DoorIsClosedConverter doorIsClosedConverter() {
        return new DoorIsClosedConverter(null);
    }
}