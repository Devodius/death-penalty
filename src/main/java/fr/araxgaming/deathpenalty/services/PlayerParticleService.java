package fr.araxgaming.deathpenalty.services;

import org.bukkit.EntityEffect;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerParticleService {

    public static void playerTotemEffect(final Player player, final ItemStack totem) {
        player.playEffect(EntityEffect.PROTECTED_FROM_DEATH);
    }

}
