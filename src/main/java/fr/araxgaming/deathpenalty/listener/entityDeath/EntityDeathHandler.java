package fr.araxgaming.deathpenalty.listener.entityDeath;

import fr.araxgaming.deathpenalty.Loadable;
import fr.araxgaming.deathpenalty.listener.PluginEventHandler;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathHandler extends Loadable implements PluginEventHandler<EntityDeathEvent> {

    @Override
    public void execute(final EntityDeathEvent event) {
        if (plugin.getPluginConfig().isEnableCustomTotemCraft()) {
            removeTotemOfUndyingDrop(event);
        }
    }

    private void removeTotemOfUndyingDrop(final EntityDeathEvent event) {
        if (event.getEntity().getType() != EntityType.EVOKER) {
            return;
        }

        event.getDrops().removeIf((item -> item.getType() == Material.TOTEM_OF_UNDYING));
    }
}
