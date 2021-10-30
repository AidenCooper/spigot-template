package me.majeek.template.managers;

import me.majeek.template.Template;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class EventManager implements Listener {
    private final List<Listener> LISTENERS = new ArrayList<>();

    public void register() {
        //listeners.add(new ExampleListener());

        registerListeners();
    }

    public List<Listener> getListeners() {
        return this.LISTENERS;
    }

    private void registerListeners() {
        for (Listener listener : this.LISTENERS) {
            Template.getInstance().getServer().getPluginManager().registerEvents(listener, Template.getInstance());
        }
    }
}
