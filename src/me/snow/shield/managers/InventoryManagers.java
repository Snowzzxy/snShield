package me.snow.shield.managers;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.util.Banner;
import me.snow.shield.api.ShieldAPI;
import me.snow.shield.snShield;
import me.snow.shield.utils.ActionBarAPI;
import me.snow.shield.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class InventoryManagers{

    public static final DecimalFormatSymbols DFS = new DecimalFormatSymbols(new Locale("pt", "BR"));
    public static final DecimalFormat FORMATTER = new DecimalFormat("#,##0.0", DFS);

    public static long time2 = System.currentTimeMillis();

    public static Inventory shieldMenu(Player p) {

        MPlayer p2 = MPlayer.get(p);
        Faction fac = p2.getFaction();

        Inventory inv = Bukkit.createInventory(null, 5 * 9, "Escudo - [" + p2.getFactionTag() + "]");

        inv.setItem(13, new ItemBuilder((fac.isInAttack() ? Banner.getRedBanner(p2.getFactionTag().toLowerCase())
                : Banner.getWhiteBanner(p2.getFactionTag().toLowerCase())))
                .setName("§a[" + p2.getFactionTag() + "] - " + p2.getFactionName())
                .setLore(Arrays.asList("",
                        "§fPoder: §7" + fac.getPowerRounded() + "/" + fac.getPowerMaxRounded(),
                        "§fPontos: §7" + FORMATTER.format(ShieldAPI.getPoints(fac.getTag())))).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).build());

        inv.setItem(29, new ItemBuilder(Material.POTION)
                .setName("§aUpgrades")
                .setLore(Arrays.asList("§7Clique para ver os upgrades disponiveis.")).build());

        inv.setItem(31, new ItemBuilder(Material.GOLD_INGOT)
                .setName("§aComprar Escudo")
                .setLore(Arrays.asList("§7Um escudo divino que garante a",
                        "§7proteção de seus Spawners durante",
                        "§f8 Horas§7.",
                        "",
                        (ShieldAPI.getEscudo(fac.getTag()) ? "§aSua facção ja possui um Escudo ativado." : "§7Clique para adquirir um Escudo."))).build());

        inv.setItem(33, new ItemBuilder(Material.COMPASS)
                .setName("§aStatus do Escudo")
                .setLore(Arrays.asList("§7Status: " + (ShieldAPI.getEscudo(fac.getTag()) ? "§aHabilitado" : "§cDesabilitado"),
                        "",
                        (ShieldAPI.getEscudo(fac.getTag()) ? "§fDuração: §c" + timeFormatter( fac.getTag(), time2) : "§cCompre um Escudo para ver a duração."))).build());

        return inv;
    }

    public static Inventory viewUpgradesMenu(Player p) {

        MPlayer p2 = MPlayer.get(p);
        Faction fac = p2.getFaction();

        FileConfiguration config = snShield.plugin.getConfig();

        Inventory inv = Bukkit.createInventory(null, 3 * 9, "Upgrades Escudo - [" + p2.getFactionTag() + "]");

        inv.setItem(12, new ItemBuilder(Material.INK_SACK, 1, (ShieldAPI.getNivel(fac.getTag()) >= 1 ? (byte) 10 : (byte) 8))
                .setName("§aUpgrade I")
                .setLore(Arrays.asList("§7Com este upgrade sua facção tera",
                        "§7uma hora a mais de Escudo.",
                        "",
                        (ShieldAPI.getNivel(fac.getTag()) >= 1 ? "§aSua facção ja possui esse upgrade." : "§7Custo: §f" + FORMATTER.format(config.getInt("Upgrades.I")) + " Pontos"))).build());
        inv.setItem(13, new ItemBuilder(Material.INK_SACK, 1, (ShieldAPI.getNivel(fac.getTag()) >= 2 ? (byte) 10 : (byte) 8))
                .setName("§aUpgrade II")
                .setLore(Arrays.asList("§7Com este upgrade sua facção tera",
                        "§7duas horas a mais de Escudo.",
                        "",
                        (ShieldAPI.getNivel(fac.getTag()) >= 2 ? "§aSua facção ja possui esse upgrade." : "§7Custo: §f" + FORMATTER.format(config.getInt("Upgrades.II")) + " Pontos"))).build());
        inv.setItem(14, new ItemBuilder(Material.INK_SACK, 1, (ShieldAPI.getNivel(fac.getTag()) == 3 ? (byte) 10 : (byte) 8))
                .setName("§aUpgrade III")
                .setLore(Arrays.asList("§7Com este upgrade sua facção tera",
                        "§7tres horas a mais de Escudo.",
                        "",
                        (ShieldAPI.getNivel(fac.getTag()) == 3 ? "§aSua facção ja possui esse upgrade." : "§7Custo: §f" + FORMATTER.format(config.getInt("Upgrades.III")) + " Pontos"))).build());

        return inv;
    }

    public static Inventory verifyEscudo(String tag) {

        Inventory inv = Bukkit.createInventory(null, 3*9, "Verificar Escudo");

        Faction fac = FactionColl.get().getByTag(tag);

        inv.setItem(13, new ItemBuilder(Banner.getWhiteBanner(tag.toLowerCase()))
                .setName("§a[" + tag.toUpperCase() + "] - " + fac.getName())
                .setLore(Arrays.asList("",
                        "§7Status: " + (ShieldAPI.getEscudo(tag) ? "§aHabilitado" : "§cDesabilitado"))).build());

        return inv;
    }

    public static String timeFormatter(String tag, long time) {
        if (time == 0L)
            return null;
        time -= System.currentTimeMillis();
        time += TimeUnit.HOURS.toMillis(5L + ShieldAPI.getNivel(tag));
        long day = TimeUnit.MILLISECONDS.toDays(time);
        long hours = TimeUnit.MILLISECONDS.toHours(time) - day * 24L;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.MILLISECONDS.toHours(time) * 60L;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MILLISECONDS.toMinutes(time) * 60L;
        StringBuilder stringBuilder = new StringBuilder();
        if (day > 0L)
            stringBuilder.append(day).append(" dias").append(" ");
        if (hours > 0L)
            stringBuilder.append(hours).append(" horas").append(" ");
        if (minutes > 0L)
            stringBuilder.append(minutes).append(" minutos").append(" ");
        if (seconds > 0L)
            stringBuilder.append(seconds).append(" segundos");
        return stringBuilder.toString().isEmpty() ? null : stringBuilder.toString();
    }
}
