package me.goomer.regionsSkyblock.regions;

import me.goomer.regionsSkyblock.RegionsSkyblock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class RegionsHelper {

    private RegionsSkyblock plugin;

    public RegionsHelper(RegionsSkyblock plugin){
        this.plugin = plugin;
    }

    public Mine getMineByKey(String key){
        String world = plugin.getConfig().getString("mines."+key+".world");

        int x1 = plugin.getConfig().getInt("mines." + key + ".loc1.x");
        int y1 = plugin.getConfig().getInt("mines." + key + ".loc1.y");
        int z1 = plugin.getConfig().getInt("mines." + key + ".loc1.z");
        Loc loc1 = new Loc(x1, y1, z1, world);

        int x2 = plugin.getConfig().getInt("mines." + key + ".loc2.x");
        int y2 = plugin.getConfig().getInt("mines." + key + ".loc2.y");
        int z2 = plugin.getConfig().getInt("mines." + key + ".loc2.z");
        Loc loc2 = new Loc(x2, y2, z2, world);

        int delay = plugin.getConfig().getInt("mines."+key+".delay");

        return new Mine(loc1, loc2, delay, key);
    }

    public Mine getMineByLocation(Location location){
        for(String key : plugin.getConfig().getConfigurationSection("mines").getKeys(false)){
            Mine mine = getMineByKey(key);
            if(mine.contains(location)){
                return mine;
            }
        }
        return null;
    }

    public Farm getFarmByKey(String key){
        int delay = plugin.getConfig().getInt("farms."+key+".delay");
        String block = plugin.getConfig().getString("farms."+key+".block");

        if(plugin.getConfig().contains("farms." + key + ".star")){
            int x = plugin.getConfig().getInt("farms." + key + ".star.x");
            int y = plugin.getConfig().getInt("farms." + key + ".star.y");
            int z = plugin.getConfig().getInt("farms." + key + ".star.z");
            String world = plugin.getConfig().getString("farms."+key+".star.world");
            Loc star = new Loc(x, y, z, world);

            return new Farm(key, block, delay, star);
        }

        return new Farm(key, block, delay);
    }

    public Farm getFarmByBlock(Block block){
        for(String key : plugin.getConfig().getConfigurationSection("farms").getKeys(false)){
            Farm farm = getFarmByKey(key);
            if(farm.contains(block)){
                return farm;
            }
        }
        return null;
    }
}
