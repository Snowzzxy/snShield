package me.snow.shield.listeners;

import com.massivecraft.factions.event.EventFactionsCreate;
import com.massivecraft.factions.event.EventFactionsDisband;
import me.snow.shield.api.ShieldAPI;
import me.snow.shield.services.SQLite;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FacEvent implements Listener {

    @EventHandler
    void a(EventFactionsCreate e) {

        SQLite.createFacInDB(e.getFactionTag());
        Bukkit.getConsoleSender().sendMessage("§b[snShield] A facção §f" + e.getFactionTag() + " §bfoi registrada.");
    }

    @EventHandler
    void a(EventFactionsDisband e) {

        ShieldAPI.deleteFac(e.getFaction().getTag());
        Bukkit.getConsoleSender().sendMessage("§b[snShield] §cA facção §f" + e.getFaction().getTag() + " §cfoi deletada.");
    }
}
