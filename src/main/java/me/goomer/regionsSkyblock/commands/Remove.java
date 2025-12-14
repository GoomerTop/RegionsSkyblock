package me.goomer.regionsSkyblock.commands;

import me.goomer.regionsSkyblock.RegionsSkyblock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Remove implements CommandExecutor {

    private RegionsSkyblock plugin;

    public Remove(RegionsSkyblock plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length>=1){
            String key = strings[0];
            switch (s){
                case "removemine":
                    plugin.getConfig().set("mines."+key,null);
                    break;
                case "removefarm":
                    plugin.getConfig().set("farms."+key,null);
                    break;
                case "removetree":
                    plugin.getConfig().set("trees."+key,null);
                    break;
            }

            plugin.saveConfig();
            send(commandSender, "Removed " + key);
            return true;
        }
        send(commandSender, "Not enough arguments");
        return true;
    }

    public void send(CommandSender commandSender, String msg){
        if(commandSender instanceof Player p){
            p.sendMessage(msg);
        }
        else{
            System.out.println(msg);
        }
    }
}
