package fr.araxgaming.deathpenalty.services;

import org.bukkit.EntityEffect;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerParticleService {

    public static void playerTotemEffect(final Player player, final ItemStack totem) {
        final PlayerInventory inventory = player.getInventory();

        inventory.setItemInMainHand(totem);

        player.playEffect(EntityEffect.TOTEM_RESURRECT);
    }

}
