package me.goomer.regionsSkyblock.commands;

import me.goomer.regionsSkyblock.RegionsSkyblock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddFarm implements CommandExecutor {

    private RegionsSkyblock plugin;

    public AddFarm(RegionsSkyblock plugin){
        this.plugin = plugin;
    }

    // /addfarm <key> <block> <minDelay> <maxDelay> [starX] [starY] [starZ]

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length >= 4){
            try{
                String key = strings[0];
                String block = strings[1];
                int minDelay = Integer.parseInt(strings[2]);
                int maxDelay = Integer.parseInt(strings[3]);

                plugin.getConfig().set("farms." + key + ".block", block);
                plugin.getConfig().set("farms." + key + ".minDelay", minDelay);
                plugin.getConfig().set("farms." + key + ".maxDelay", maxDelay);

                if(strings.length >= 7){
                    int starX = Integer.parseInt(strings[4]);
                    int starY = Integer.parseInt(strings[5]);
                    int starZ = Integer.parseInt(strings[6]);

                    plugin.getConfig().set("farms." + key + ".star.x", starX);
                    plugin.getConfig().set("farms." + key + ".star.y", starY);
                    plugin.getConfig().set("farms." + key + ".star.z", starZ);
                }

                plugin.saveConfig();

                send(commandSender, "Added " + key);

            }catch (NumberFormatException e){
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
