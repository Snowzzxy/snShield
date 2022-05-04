package me.snow.shield.listeners;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.ps.PS;
import me.snow.shield.api.ShieldAPI;
import me.snow.shield.snShield;
import me.snow.shield.utils.ActionBarAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplodeEvent implements Listener {

    @EventHandler
    void a(EntityExplodeEvent e) {

        Faction ps = BoardColl.get().getFactionAt(PS.valueOf(e.getLocation()));
        FileConfiguration config = snShield.plugin.getConfig();

        if(ShieldAPI.getEscudo(ps.getTag())) {
            e.setCancelled(true);
            ps.getMPlayers().forEach(teste -> ActionBarAPI.sendActionBarMessage(teste.getPlayer() , config.getString("Mensagens.TentandoAtaque")
                    .replace("&","§")));
        }
    }
}
