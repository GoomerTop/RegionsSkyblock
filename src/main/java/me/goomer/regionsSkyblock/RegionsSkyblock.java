package me.goomer.regionsSkyblock;

import me.goomer.regionsSkyblock.events.BlockBreak;
import me.goomer.regionsSkyblock.regions.BlockLoc;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public final class RegionsSkyblock extends JavaPlugin {

    HashMap<String, ArrayList<BlockLoc>> blocks;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        blocks = new HashMap<>();
        getServer().getPluginManager().registerEvents(new BlockBreak(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void regenerateFirst(String key, boolean isStar){
        ArrayList<BlockLoc> rblocks = blocks.get(key);
        if(rblocks != null && !rblocks.isEmpty()){
            BlockLoc blockLoc = rblocks.removeFirst();
            Block block = blockLoc.getBlockAt();
            block.setType(Material.getMaterial(blockLoc.getBlock()));
            if(block.getBlockData() instanceof Ageable ageable){
                ageable.setAge(ageable.getMaximumAge());
                block.setBlockData(ageable);

            }
        }
    }

    public boolean exists(String key){
        if(blocks.containsKey(key))
            return !blocks.get(key).isEmpty();
        return false;
    }

    public void addBlock(String key, Block block){
        BlockLoc blockLoc = new BlockLoc(block);
        if(exists(key)){
            blocks.get(key).add(blockLoc);
        }
        else{
            ArrayList<BlockLoc> list = new ArrayList<>();
            list.add(blockLoc);
            blocks.put(key, list);
        }
    }
}
