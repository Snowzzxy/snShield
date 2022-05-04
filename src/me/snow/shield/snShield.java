package me.snow.shield;

import me.snow.shield.api.ShieldAPI;
import me.snow.shield.commands.EscudoCommand;
import me.snow.shield.commands.PontosCommand;
import me.snow.shield.listeners.*;
import me.snow.shield.services.SQLite;
import me.snow.shield.utils.DateManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class snShield extends JavaPlugin {

    public static snShield plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();

        DateManager.createFolder("database");
        SQLite.openConnection();

        ShieldAPI.setupFactions();

        registerEvents();
        registerCommands();

        Bukkit.getConsoleSender().sendMessage("§b[snShield] Plugin iniciado");
    }

    @Override
    public void onDisable() {
        SQLite.closeConnection();
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        getServer().getPluginManager().registerEvents(new EntityEvent(), this);
        getServer().getPluginManager().registerEvents(new FacEvent(), this);
        getServer().getPluginManager().registerEvents(new CommandEvent(), this);
        getServer().getPluginManager().registerEvents(new ExplodeEvent(), this);
    }

    public void registerCommands() {
        getCommand("pontos").setExecutor(new PontosCommand());
        getCommand("escudo").setExecutor(new EscudoCommand());
    }
}
