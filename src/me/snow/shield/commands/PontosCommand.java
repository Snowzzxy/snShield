package me.snow.shield.commands;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import me.snow.shield.api.ShieldAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PontosCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        Player p = (Player) sender;

        if(cmd.getLabel().equalsIgnoreCase("pontos")) {
            if(args.length == 0) {
                p.sendMessage("§cUtilize, /pontos ajuda.");
                return true;
            }

            if(args[0].equalsIgnoreCase("adicionar")) {
                if(p.hasPermission("shield.givepoints")) {

                    if(args.length < 2) {
                        p.sendMessage("§cIncorreto, /pontos adicionar <tag> <quantia>.");
                        return true;
                    }

                    Faction fac = FactionColl.get().getByTag(args[1]);
                    Double quantia = null;
                    if(fac == null) {
                        p.sendMessage("§cA facção não foi encontrada!");
                        return true;
                    }

                    try{
                        quantia = Double.parseDouble(args[2]);
                    }catch(NumberFormatException e) {
                        p.sendMessage("§cInsira apenas numeros.");
                        return true;
                    }

                    ShieldAPI.addPontos(fac.getTag(), quantia);
                    p.sendMessage("§aYeah! Pontos adicionados com sucesso.");

                }else{
                    p.sendMessage("§cVocê não possui permissão.");
                    return true;
                }
            }

            if(args[0].equalsIgnoreCase("remover")) {
                if(p.hasPermission("shield.removepoints")) {

                    if(args.length < 2) {
                        p.sendMessage("§cIncorreto, /pontos remover <tag> <quantia>.");
                        return true;
                    }

                    Faction fac = FactionColl.get().getByTag(args[1]);
                    Double quantia = null;
                    if(fac == null) {
                        p.sendMessage("§cA facção não foi encontrada!");
                        return true;
                    }

                    try{
                        quantia = Double.parseDouble(args[2]);
                    }catch(NumberFormatException e) {
                        p.sendMessage("§cInsira apenas numeros.");
                        return true;
                    }


                    if(ShieldAPI.getPoints(fac.getTag()) < quantia) {
                        p.sendMessage("§cVocê inseriu um valor maior que a facção possui.");
                        return true;
                    }

                    ShieldAPI.removePontos(fac.getTag(), quantia);
                    p.sendMessage("§aYeah! Pontos adicionados com sucesso.");

                }else{
                    p.sendMessage("§cVocê não possui permissão.");
                    return true;
                }
            }
        }
        return false;
    }
}
