package fr.araxgaming.deathpenalty.listener;

import org.bukkit.event.Event;

public interface PluginEventHandler<EventHandled extends Event> {

    void execute(EventHandled event);

}
