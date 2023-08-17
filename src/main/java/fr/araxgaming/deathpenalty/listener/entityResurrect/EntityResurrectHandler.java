package fr.araxgaming.deathpenalty.listener.entityResurrect;

import fr.araxgaming.deathpenalty.Loadable;
import fr.araxgaming.deathpenalty.listener.PluginEventHandler;
import fr.araxgaming.deathpenalty.services.PlayerInventoryService;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class EntityResurrectHandler extends Loadable implements PluginEventHandler<EntityResurrectEvent> {

    @Override
    public void execute(final EntityResurrectEvent event) {
        final EquipmentSlot undyingHand = event.getHand();
        final EntityEquipment entityEquipment = event.getEntity().getEquipment();

        if (undyingHand == null || entityEquipment == null) {
            return;
        }

        final ItemStack undyingItem = entityEquipment.getItem(undyingHand);

        if (PlayerInventoryService.isSameCustomItem(undyingItem, new ItemStack(Material.TOTEM_OF_UNDYING))) {
            return;
        }

        event.setCancelled(true);
    }
}
