package me.snow.shield.listeners;

import com.massivecraft.factions.entity.MPlayer;
import me.snow.shield.api.ShieldAPI;
import me.snow.shield.snShield;
import me.snow.shield.utils.ActionBarAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityEvent implements Listener {

    @EventHandler
    void a(EntityDeathEvent e) {

        MPlayer killer = MPlayer.get(e.getEntity().getKiller());
        FileConfiguration config = snShield.plugin.getConfig();

        if (e.getEntity().getKiller() != null) {

            if (killer.getFaction() == null) {
                return;
            }

            switch (e.getEntity().getType()) {
                case COW:
                    ShieldAPI.addPontos(killer.getFactionTag(), config.getDouble("Mobs.Cow"));
                    ActionBarAPI.sendActionBarMessage(e.getEntity().getKiller(), config.getString("Mensagens.ActionBar")
                            .replace("&", "§")
                            .replace("{quantia}", "" + config.getDouble("Mobs.Cow")));
                    break;

                case ZOMBIE:
                    ShieldAPI.addPontos(killer.getFactionTag(), config.getDouble("Mobs.Zombie"));
                    ActionBarAPI.sendActionBarMessage(e.getEntity().getKiller(), config.getString("Mensagens.ActionBar")
                            .replace("&", "§")
                            .replace("{quantia}", "" + config.getDouble("Mobs.Zombie")));
                    break;

                case SPIDER:
                    ShieldAPI.addPontos(killer.getFactionTag(), config.getDouble("Mobs.Spider"));
                    ActionBarAPI.sendActionBarMessage(e.getEntity().getKiller(), config.getString("Mensagens.ActionBar")
                            .replace("&", "§")
                            .replace("{quantia}", "" + config.getDouble("Mobs.Spider")));
                    break;

                case SKELETON:
                    ShieldAPI.addPontos(killer.getFactionTag(), config.getDouble("Mobs.Skeleton"));
                    ActionBarAPI.sendActionBarMessage(e.getEntity().getKiller(), config.getString("Mensagens.ActionBar")
                            .replace("&", "§")
                            .replace("{quantia}", "" + config.getDouble("Mobs.Skeleton")));
                    break;

                case BLAZE:
                    ShieldAPI.addPontos(killer.getFactionTag(), config.getDouble("Mobs.Blaze"));
                    ActionBarAPI.sendActionBarMessage(e.getEntity().getKiller(), config.getString("Mensagens.ActionBar")
                            .replace("&", "§")
                            .replace("{quantia}", "" + config.getDouble("Mobs.Blaze")));
                    break;

                case PIG_ZOMBIE:
                    ShieldAPI.addPontos(killer.getFactionTag(), config.getDouble("Mobs.Pigman"));
                    ActionBarAPI.sendActionBarMessage(e.getEntity().getKiller(), config.getString("Mensagens.ActionBar")
                            .replace("&", "§")
                            .replace("{quantia}", "" + config.getDouble("Mobs.Pigman")));
                    break;

                case IRON_GOLEM:
                    ShieldAPI.addPontos(killer.getFactionTag(), config.getDouble("Mobs.Golem"));
                    ActionBarAPI.sendActionBarMessage(e.getEntity().getKiller(), config.getString("Mensagens.ActionBar")
                            .replace("&", "§")
                            .replace("{quantia}", "" + config.getDouble("Mobs.Golem")));
                    break;

                case WITHER:
                    ShieldAPI.addPontos(killer.getFactionTag(), config.getDouble("Mobs.Wither"));
                    ActionBarAPI.sendActionBarMessage(e.getEntity().getKiller(), config.getString("Mensagens.ActionBar")
                            .replace("&", "§")
                            .replace("{quantia}", "" + config.getDouble("Mobs.Wither")));
                    break;
            }

        }
    }
}
