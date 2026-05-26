package fr.araxgaming.deathpenalty.services;

import org.bukkit.EntityEffect;
import org.bukkit.entity.Player;

public class PlayerParticleService {

    public static void playerTotemEffect(final Player player) {
        player.playEffect(EntityEffect.PROTECTED_FROM_DEATH);
    }

}
