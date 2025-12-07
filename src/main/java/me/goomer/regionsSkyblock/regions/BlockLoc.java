package me.goomer.regionsSkyblock.regions;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class BlockLoc extends Loc{
    private String block;

    public BlockLoc(int x, int y, int z, String world, String block) {
        super(x, y, z, world);
        this.block = block;
    }

    public BlockLoc(Block block){
        super(block.getX(), block.getY(), block.getZ(), block.getWorld().getName());
        this.block = block.getType().name();
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public void setBlockAt(){
        Block block = getBlockAt();
        if(block != null)
            block.setType(Material.getMaterial(this.block));
    }
}
