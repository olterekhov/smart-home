package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.remotecontrol.commands.Command;

import java.util.HashMap;

public class HomeRemoteControl implements RemoteControl {
    private HashMap<String, Command> remoteControls;

    public HomeRemoteControl() {
        this.remoteControls = new HashMap<>();
    }


    @Override
    public void onButtonPressed(String buttonCode) {
        if (remoteControls.containsKey(buttonCode)) {
            remoteControls.get(buttonCode).execute();
        }
    }

    public void addRemoteControl(String button, Command command) {
        remoteControls.put(button, command);
    }
}
