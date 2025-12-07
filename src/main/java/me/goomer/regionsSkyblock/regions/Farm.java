package me.goomer.regionsSkyblock.regions;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class Farm {
    private String key, block;
    private int delay;
    private Loc star;

    public Farm(String key, String block, int delay){
        this.block = block;
        this.key = key;
        this.delay = delay;
        this.star = null;
    }

    public Farm(String key, String block, int delay, Loc star){
        this.block = block;
        this.key = key;
        this.delay = delay;
        this.star = star;
    }

    public Loc getStar() {
        return star;
    }

    public void setStar(Loc star) {
        this.star = star;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean contains(Block block){
        return block.getRelative(0, -2, 0).getType().name().equals(this.block);
    }
}
