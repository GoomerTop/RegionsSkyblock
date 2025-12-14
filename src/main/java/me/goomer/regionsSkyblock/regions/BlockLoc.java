package me.goomer.regionsSkyblock.regions;

import org.bukkit.Axis;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Orientable;

public class BlockLoc extends Loc{
    private String block;
    private Axis face;

    public BlockLoc(int x, int y, int z, String world, String block, Axis face) {
        super(x, y, z, world);
        this.block = block;
        this.face = face;
    }

    public BlockLoc(Block block){
        super(block.getX(), block.getY(), block.getZ(), block.getWorld().getName());
        this.block = block.getType().name();
        this.face = null;
        if(block.getBlockData() instanceof Orientable orientable){
            this.face = orientable.getAxis();
        }
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

    public Axis getFace() {
        return face;
    }

    public void setFace(Axis face) {
        this.face = face;
    }
}
