package me.goomer.regionsSkyblock.commands;

import me.goomer.regionsSkyblock.RegionsSkyblock;
import me.goomer.regionsSkyblock.regions.RegionsHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddMine implements CommandExecutor {

    private RegionsSkyblock plugin;

    public AddMine(RegionsSkyblock plugin){
        this.plugin = plugin;
    }

    // /addmine <key> <x1> <y1> <z1> <x2> <y2> <z2> <minDelay> <maxDelay> <world>

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length>=10){
            try{

                String key = strings[0];

                int x1 = Integer.parseInt(strings[1]);
                int y1 = Integer.parseInt(strings[2]);
                int z1 = Integer.parseInt(strings[3]);

                int x2 = Integer.parseInt(strings[4]);
                int y2 = Integer.parseInt(strings[5]);
                int z2 = Integer.parseInt(strings[6]);

                int minDelay = Integer.parseInt(strings[7]);
                int maxDelay = Integer.parseInt(strings[8]);

                String world = strings[9];

                plugin.getConfig().set("mines."+ key + ".world", world);
                plugin.getConfig().set("mines."+ key + ".minDelay", minDelay);
                plugin.getConfig().set("mines."+ key + ".maxDelay", maxDelay);
                plugin.getConfig().set("mines."+ key + ".loc1.x", x1);
                plugin.getConfig().set("mines."+ key + ".loc1.y", y1);
                plugin.getConfig().set("mines."+ key + ".loc1.z", z1);
                plugin.getConfig().set("mines."+ key + ".loc2.x", x2);
                plugin.getConfig().set("mines."+ key + ".loc2.y", y2);
                plugin.getConfig().set("mines."+ key + ".loc2.z", z2);

                plugin.saveConfig();

                send(commandSender, "Added " + key);

            } catch (NumberFormatException e){
                send(commandSender, "You didn't enter the arguments correctly");
            }

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
