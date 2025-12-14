package me.goomer.regionsSkyblock.commands;

import me.goomer.regionsSkyblock.RegionsSkyblock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Regenerate implements CommandExecutor {

    private RegionsSkyblock plugin;

    public Regenerate(RegionsSkyblock plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length < 1){
            send(commandSender, "Need to enter a key");
        }
        String key = strings[0];
        if(key.equals("*")){
            plugin.regenerateAll();
            send(commandSender, "Regenerated All");
            return true;
        }

        plugin.regenerateByKey(key);
        send(commandSender, "Regenerated " + key);

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
