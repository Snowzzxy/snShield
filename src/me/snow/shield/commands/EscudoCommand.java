package me.snow.shield.commands;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import me.snow.shield.managers.InventoryManagers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EscudoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("§cApenas para jogadores autenticados.");
            return true;
        }

        Player p = (Player) sender;

        if(cmd.getLabel().equalsIgnoreCase("escudo")) {
            if(args.length == 0) {
                p.sendMessage("§cIncorreto, /escudo verificar <tag>.");
                return true;
            }

            if(args[0].equalsIgnoreCase("verificar")) {

                if(args.length < 2) {
                    p.sendMessage("§cIncorreto, /escudo verificar <tag>.");
                    return true;
                }

                Faction fac = FactionColl.get().getByTag(args[1]);
                if(fac == null) {
                    p.sendMessage("§cA facção não foi encontrada!");
                    return true;
                }

                p.openInventory(new InventoryManagers().verifyEscudo(args[1]));
                p.sendMessage("§aVerificação de Escudo aberta.");
            }
        }
        return false;
    }
}
