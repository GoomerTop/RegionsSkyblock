package me.goomer.regionsSkyblock;

import me.goomer.regionsSkyblock.commands.*;
import me.goomer.regionsSkyblock.events.BlockBreak;
import me.goomer.regionsSkyblock.events.NewBlockBreak;
import me.goomer.regionsSkyblock.regions.BlockLoc;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Orientable;
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
        // getServer().getPluginManager().registerEvents(new BlockBreak(this), this);
        getServer().getPluginManager().registerEvents(new NewBlockBreak(this), this);
        //getCommand("regenerate").setExecutor(new Regenerate(this));
        getCommand("addmine").setExecutor(new AddMine(this));
        getCommand("addfarm").setExecutor(new AddFarm(this));
        getCommand("addtree").setExecutor(new AddTree(this));
        getCommand("removemine").setExecutor(new Remove(this));
        getCommand("removefarm").setExecutor(new Remove(this));
        getCommand("removetree").setExecutor(new Remove(this));

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
            if(block.getBlockData() instanceof Orientable orientable){
                if(blockLoc.getFace()!=null){
                    orientable.setAxis(blockLoc.getFace());
                    block.setBlockData(orientable);
                }
            }
        }
    }

    public void regenerateByKey(String key){
        if(exists(key)){
            while(exists(key)){
                regenerateFirst(key, false);
            }
        }
    }

    public void regenerateAll(){
        for(String key : blocks.keySet()){
            regenerateByKey(key);
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

    public void addBlockLoc(String key, BlockLoc blockLoc){
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
