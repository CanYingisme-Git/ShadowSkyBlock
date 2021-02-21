package pw.canyingisme.shadowskyblockcore.SkyBlock;


import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pw.canyingisme.shadowcore.Utils.LockPlayer;
import pw.canyingisme.shadowskyblockcore.config.PluginConfigManager;
import pw.canyingisme.shadowskyblockcore.config.SkyBlockConfigManager;

import java.io.File;
import java.io.IOException;
import java.util.*;

/*
管理空岛
 */
public class SkyBlockManager {
    private SkyBlockWorld skyBlockWorld = new SkyBlockWorld();
    private PluginConfigManager pluginConfigManager;
    private SkyBlockConfigManager skyBlockConfigManager;
    private final List<Island> islands = new ArrayList<Island>();
    public SkyBlockWorld getSkyBlockWorldManager(){
        return skyBlockWorld;
    }
    public void createIsland(CommandSender sender){
        if(skyBlockConfigManager.isPlayerHasIsland(Bukkit.getPlayerExact(sender.getName()))){
            sender.sendMessage(pluginConfigManager.getPrefix()+" 你已经有空岛了");
            return;
        }
        createIsland((Player)sender);
    }
    public void loadConfig(){
        YamlConfiguration defaultConfig = new YamlConfiguration();
        defaultConfig.set("prefix","[服务器]");
        defaultConfig.set("islandRadius",100);
        defaultConfig.set("islandRange",1000);
        pluginConfigManager = new PluginConfigManager(defaultConfig);
        defaultConfig = new YamlConfiguration();
        skyBlockConfigManager = new SkyBlockConfigManager(defaultConfig);
    }
    public void loadIslands(){
        LinkedList<File> list = new LinkedList<File>();
        File[] files = new File(new File("").getAbsolutePath() + "/plugins/ShadowSkyBlock/islands").listFiles();
        for (File file2 : files) {
            if (file2.isDirectory()) {
            } else {
                YamlConfiguration yamlConfiguration = new YamlConfiguration();
                try {
                    yamlConfiguration.load(file2);
                    Location locationA = new Location(skyBlockWorld.getOverWorld(),
                            yamlConfiguration.getDouble( "island.LocA.X"),
                            yamlConfiguration.getDouble("island.LocA.Y"),
                            yamlConfiguration.getDouble("island.LocA.Z"));
                    Location locationB = new Location(skyBlockWorld.getOverWorld(),
                            yamlConfiguration.getDouble( "island.LocB.X"),
                            yamlConfiguration.getDouble("island.LocB.Y"),
                            yamlConfiguration.getDouble("island.LocB.Z"));
                    Location locationCenter = new Location(skyBlockWorld.getOverWorld(),
                            yamlConfiguration.getDouble( "island.LocCenter.X"),
                            yamlConfiguration.getDouble("island.LocCenter.Y"),
                            yamlConfiguration.getDouble("island.LocCenter.Z"));
                    islands.add(new Island(locationA,locationB,locationCenter,UUID.fromString(file2.getName())));
                } catch (IOException | InvalidConfigurationException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public SkyBlockConfigManager getSkyBlockConfigManager(){
        return skyBlockConfigManager;
    }
    public void createIsland(Player player){
        Location locationCenter;
        if (skyBlockConfigManager.getLeastIsland() == null){
            //First island
            locationCenter = new Location(skyBlockWorld.getOverWorld(),0,60,0);
        }else {
            locationCenter = new Location(skyBlockWorld.getOverWorld(),getIslandByUUID(UUID.fromString(skyBlockConfigManager.getLeastIsland())).
                    center.getX()+pluginConfigManager.getSkyBlockRadius()+pluginConfigManager.getSkyBlockRange(),60,0);
        }
        Island island = createIsland(locationCenter,player);
        createIslandBlock(island,player);
    }
    public Island createIsland(Location locationCenter,Player player){
        //Create a simple Island location by Center
        Location locationA = new Location(skyBlockWorld.getOverWorld(),
                locationCenter.getX()+pluginConfigManager.getSkyBlockRadius(),
                60,
                 pluginConfigManager.getSkyBlockRadius());
        Location locationB = new Location(skyBlockWorld.getOverWorld(),
                locationCenter.getX() - pluginConfigManager.getSkyBlockRadius(),
                60,
                -pluginConfigManager.getSkyBlockRadius());
        Island island = new Island(locationA,locationB,locationCenter,player.getUniqueId());
        return island;
    }
    public void createIslandBlock(Island island,Player player){
        Location blockLoc = island.center;
        Block block = blockLoc.getBlock();
        island.center.setY(island.center.getY()+15);
        player.teleport(island.center);
        LockPlayer.lockPlayer(player);
    }
    public Island getIslandByUUID(UUID uuid){
        for(Island island : islands){
            if (island.uuid == uuid){
                return island;
            }
        }
        return null;
    }
}
