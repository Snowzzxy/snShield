package me.snow.shield.listeners;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.MPlayer;
import me.snow.shield.managers.InventoryManagers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandEvent implements Listener {

    @EventHandler
    void a(PlayerCommandPreprocessEvent e) {

        Player p = e.getPlayer();
        MPlayer p2 = MPlayer.get(p);

        if(e.getMessage().startsWith("/f escudo") || e.getMessage().startsWith("/f shield")) {
            e.setCancelled(true);

                if (p2.getFaction() == null) {
                    p.sendMessage("§cVocê precisa possuir uma facção.");
                    return;
                }

                if (p2.getRole() == Rel.MEMBER || p2.getRole() == Rel.RECRUIT) {
                    p.sendMessage("§cVocê precisa ser Capitão ou superior.");
                    return;
                }

                p.openInventory(new InventoryManagers().shieldMenu(p));
                p.sendMessage("§aSistema de Escudo aberto.");
        }
    }
}
