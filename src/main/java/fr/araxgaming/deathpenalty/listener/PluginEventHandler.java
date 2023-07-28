package fr.araxgaming.deathpenalty.listener;

import fr.araxgaming.deathpenalty.Loadable;
import org.bukkit.event.Event;

public interface PluginEventHandler<EventHandled extends Event> extends Loadable {

    void execute(EventHandled event);

}
