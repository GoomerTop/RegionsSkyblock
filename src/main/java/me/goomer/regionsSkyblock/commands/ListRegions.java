package me.goomer.regionsSkyblock.commands;

import me.goomer.regionsSkyblock.RegionsSkyblock;
import me.goomer.regionsSkyblock.regions.Farm;
import me.goomer.regionsSkyblock.regions.Mine;
import me.goomer.regionsSkyblock.regions.RegionsHelper;
import me.goomer.regionsSkyblock.regions.Tree;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class ListRegions extends SubCommand{
    private RegionsSkyblock plugin;
    private RegionsHelper regionsHelper;

    public ListRegions(RegionsSkyblock plugin){
        this.plugin = plugin;
        this.regionsHelper = new RegionsHelper(plugin);
    }

    @Override
    public void execute(CommandSender sender, String[] strings) {
        if(strings.length>=2){
            if(strings[1].equals("tree") || strings[1].equals("trees")){
                send(sender, getTreeList());
            }
            else if (strings[1].equals("mine") || strings[1].equals("mines")) {
                send(sender, getMineList());
            }
            else if (strings[1].equals("farm") || strings[1].equals("farms")) {
                send(sender, getFarmList());
            }
            else{
                send(sender, "No \"" + strings[1] + "\" region");
            }
        }
        else{
            send(sender, getFarmList() + getMineList() + getTreeList());
        }
    }

    public String getFarmList(){
        ArrayList<Farm> farms = regionsHelper.getAllFarms();
        if(farms.isEmpty()){
            return "farms:\n - no farms";
        }
        String str = "farms:\n";
        for(Farm farm : farms){
            str += " - " + farm.getKey() + "\n";
        }
        return str;
    }

    public String getMineList(){
        ArrayList<Mine> mines = regionsHelper.getAllMines();
        if(mines.isEmpty()){
            return "mines:\n - no mines";
        }
        String str = "mines:\n";
        for(Mine mine : mines){
            str += " - " + mine.getKey() + "\n";
        }
        return str;
    }

    public String getTreeList(){
        ArrayList<Tree> trees = regionsHelper.getAllTrees();
        if(trees.isEmpty()){
            return "trees:\n - no trees";
        }
        String str = "trees:\n";
        for(Tree tree : trees){
            str += " - " + tree.getKey() + "\n";
        }
        return str;
    }


    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getSyntax() {
        return "/regions list [region-type]";
    }

    @Override
    public String getDesc() {
        return "get a list of all the regions";
    }
}
