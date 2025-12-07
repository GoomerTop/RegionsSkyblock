package me.goomer.regionsSkyblock.regions;

import org.bukkit.Location;

public class Mine {
    private Loc loc1, loc2;
    private String key;
    private int delay;

    public Mine(Loc loc1, Loc loc2, int delay, String key){
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.delay = delay;
        this.key = key;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Loc getLoc1() {
        return loc1;
    }

    public void setLoc1(Loc loc1) {
        this.loc1 = loc1;
    }

    public Loc getLoc2() {
        return loc2;
    }

    public void setLoc2(Loc loc2) {
        this.loc2 = loc2;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean contains(Location loc){
        int minX = Math.min(loc1.getX(), loc2.getX());
        int maxX = Math.max(loc1.getX(), loc2.getX());
        int minY = Math.min(loc1.getY(), loc2.getY());
        int maxY = Math.max(loc1.getY(), loc2.getY());
        int minZ = Math.min(loc1.getZ(), loc2.getZ());
        int maxZ = Math.max(loc1.getZ(), loc2.getZ());

        return minX <= loc.getX() && loc.getX() <= maxX &&
                minZ <= loc.getZ() && loc.getZ() <= maxZ
                && minY <= loc.getY() && loc.getY() <= maxY;
    }
}
