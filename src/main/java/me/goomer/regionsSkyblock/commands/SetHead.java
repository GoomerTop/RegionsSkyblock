package me.goomer.regionsSkyblock.commands;

import me.goomer.regionsSkyblock.RegionsSkyblock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHead implements CommandExecutor {

    private RegionsSkyblock plugin;

    public SetHead(RegionsSkyblock plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length>0){
            plugin.getConfig().set("head",strings[0]);
            plugin.saveConfig();
            send(commandSender, "Done");
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
