package me.snow.shield.listeners;

import com.massivecraft.factions.entity.MPlayer;
import me.snow.shield.api.ShieldAPI;
import me.snow.shield.managers.InventoryManagers;
import me.snow.shield.snShield;
import me.snow.shield.utils.ActionBarAPI;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ClickEvent implements Listener {

    public static final DecimalFormatSymbols DFS = new DecimalFormatSymbols(new Locale("pt", "BR"));
    public static final DecimalFormat FORMATTER = new DecimalFormat("#,##0.0", DFS);

    @EventHandler
    public void b(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        MPlayer fac = MPlayer.get(p);

        ItemStack item = e.getCurrentItem();
        FileConfiguration config = snShield.plugin.getConfig();

        if (fac.getFaction() != null) {

            if (e.getInventory().getName().equals("Escudo - [" + fac.getFactionTag() + "]")) {
                e.setCancelled(true);

                if (e.getClickedInventory() != null) {

                    if (item != null && item.getType() != Material.AIR) {

                        if (e.getSlot() == 29) {
                            p.openInventory(InventoryManagers.viewUpgradesMenu(p));
                        } else if (e.getSlot() == 31) {

                            if (ShieldAPI.getEscudo(fac.getFactionTag())) {
                                p.sendMessage("§cSua facção ja possui um Escudo ativo.");
                                return;
                            }

                            if (ShieldAPI.getPoints(fac.getFactionTag()) < config.getInt("Escudo.ComprarEscudo")) {
                                p.sendMessage("§cSua facção precisa possuir §f" + FORMATTER.format(config.getInt("Escudo.ComprarEscudo")) + " §cPontos para comprar isto.");
                                return;
                            }
                            fac.getFaction().getMPlayers().forEach(teste -> ActionBarAPI.sendActionBarMessage(teste.getPlayer() , config.getString("Mensagens.EscudoComprado")
                                    .replace("&","§")
                                    .replace("{tag}", fac.getFactionTag())));

                            ShieldAPI.removePontos(fac.getFactionTag(), config.getInt("Escudo.ComprarEscudo"));
                            ShieldAPI.setEscudo(fac.getFactionTag(), 1);
                            p.closeInventory();
                            p.openInventory(InventoryManagers.shieldMenu(p));

                            int time = config.getInt("Escudo.Duracao") + ShieldAPI.getNivel(fac.getFactionTag());

                            new BukkitRunnable() {

                                @Override
                                public void run() {
                                    ShieldAPI.setEscudo(fac.getFactionTag(), 0);
                                    fac.getFaction().getMPlayers().forEach(teste -> ActionBarAPI.sendActionBarMessage(teste.getPlayer() , config.getString("Mensagens.EscudoTerminou")
                                            .replace("&","§")
                                            .replace("{tag}", fac.getFactionTag())));
                                }
                            }.runTaskLaterAsynchronously(snShield.plugin, time * 60 * 20 * 20L);
                        }
                    }
                }
            } else if (e.getInventory().getName().equals("Upgrades Escudo - [" + fac.getFactionTag() + "]")) {
                e.setCancelled(true);

                if (e.getClickedInventory() != null) {

                    if (item != null && item.getType() != Material.AIR) {
                        if (e.getSlot() == 12) {
                            p.sendMessage("§cVocê ja possui esse upgrade.");
                            return;

                        } else if (e.getSlot() == 13) {

                            if (ShieldAPI.getNivel(fac.getFactionTag()) >= 2) {
                                p.sendMessage("§cVocê ja possui esse upgrade.");
                                return;
                            }

                            if (ShieldAPI.getPoints(fac.getFactionTag()) < config.getDouble("Upgrades.II")) {
                                p.sendMessage("§cSua facção precisa possuir §f" + FORMATTER.format(config.getDouble("Upgrades.II")) + " §cPontos para comprar isto.");
                                return;
                            }

                            p.sendMessage("§aYeah! Upgrade II comprado com sucesso.");
                            ShieldAPI.removePontos(fac.getFactionTag(), config.getDouble("Upgrades.II"));
                            ShieldAPI.setNivel(fac.getFactionTag(), 2);
                            p.closeInventory();
                            p.openInventory(InventoryManagers.viewUpgradesMenu(p));
                        } else if (e.getSlot() == 14) {

                            if (ShieldAPI.getNivel(fac.getFactionTag()) >= 3) {
                                p.sendMessage("§cVocê ja possui esse upgrade.");
                                return;
                            }

                            if(ShieldAPI.getNivel(fac.getFactionTag()) < 2) {
                                p.sendMessage("§cPara dar outro upgrade você precisar ser Nivel II.");
                                return;
                            }

                            if (ShieldAPI.getPoints(fac.getFactionTag()) < config.getDouble("Upgrades.III")) {
                                p.sendMessage("§cSua facção precisa possuir §f" + FORMATTER.format(config.getDouble("Upgrades.III")) + " §cPontos para comprar isto.");
                                return;
                            }

                            p.sendMessage("§aYeah! Upgrade III comprado com sucesso.");
                            ShieldAPI.removePontos(fac.getFactionTag(), config.getDouble("Upgrades.III"));
                            ShieldAPI.setNivel(fac.getFactionTag(), 3);
                            p.closeInventory();
                            p.openInventory(InventoryManagers.viewUpgradesMenu(p));
                        }
                    }
                }
            }else if (e.getInventory().getName().equals("Verificar Escudo")) {
                e.setCancelled(true);
            }

        }
    }
}
