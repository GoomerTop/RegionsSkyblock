package me.goomer.regionsSkyblock.events;

import me.goomer.regionsSkyblock.RegionsSkyblock;
import me.goomer.regionsSkyblock.regions.Farm;
import me.goomer.regionsSkyblock.regions.Mine;
import me.goomer.regionsSkyblock.regions.RegionsHelper;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockBreak implements Listener {

    private RegionsSkyblock plugin;
    private RegionsHelper helper;

    public BlockBreak(RegionsSkyblock plugin){
        this.plugin = plugin;
        this.helper = new RegionsHelper(plugin);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onBreak(BlockBreakEvent event){
        Block block = event.getBlock();
        Farm farm = helper.getFarmByBlock(block);
        if(farm != null){
            boolean loop = plugin.exists(farm.getKey());
            plugin.addBlock(farm.getKey(), block);
            if(!loop){
                farmLoop(farm);
            }
            return;
        }
        Mine mine = helper.getMineByLocation(block.getLocation());
        if(mine != null){
            if(mine.contains(block.getLocation())){
                if(block.getType()== Material.COBBLESTONE){
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.BEDROCK);
                        }
                    }.runTask(plugin);
                }
                else{
                    boolean loop = plugin.exists(mine.getKey());
                    plugin.addBlock(mine.getKey(), block);
                    if(!loop){
                        mineLoop(mine);
                    }
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.COBBLESTONE);
                        }
                    }.runTask(plugin);
                }
            }
            return;
        }
    }

    public void mineLoop(Mine mine){
        new BukkitRunnable() {
            @Override
            public void run() {
                if(plugin.exists(mine.getKey())){
                    plugin.regenerateFirst(mine.getKey(), false);
                    if(plugin.exists(mine.getKey())){
                        mineLoop(mine);
                    }
                }
            }
        }.runTaskLater(plugin, mine.getDelay());
    }

    public void farmLoop(Farm farm){
        new BukkitRunnable() {
            @Override
            public void run() {
                if(plugin.exists(farm.getKey())){
                    // TODO star effect
                    plugin.regenerateFirst(farm.getKey(), farm.getStar()!=null);
                    if(plugin.exists(farm.getKey())){
                        farmLoop(farm);
                    }
                }
            }
        }.runTaskLater(plugin, farm.getDelay());
    }
}
