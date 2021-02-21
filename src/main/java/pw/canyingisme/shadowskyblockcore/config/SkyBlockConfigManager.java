package pw.canyingisme.shadowskyblockcore.config;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import javax.security.auth.login.Configuration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
读取空岛配置文件和创建空岛配置文件
 */
public class SkyBlockConfigManager extends ConfigManager{
    private static YamlConfiguration Config;
    public SkyBlockConfigManager(YamlConfiguration defaultConfig) {
        super(defaultConfig, new File(new File("").getAbsolutePath() + "/plugins/ShadowSkyBlock/island.yml"));
        Config = getYamlConfig();
    }
    public boolean isPlayerHasIsland(Player player){
        ConfigurationSection configurationSection = Config.getConfigurationSection("islands");
        //Owners UUID
        List<String> islandOwners = new ArrayList<>(configurationSection.getKeys(false));
        for (int i = 0;i < islandOwners.size();i++){
            if(islandOwners.get(i).equals(player.getUniqueId().toString())){
                return true;
            }
        }
        return false;
    }
    public String getLeastIsland(){
        ConfigurationSection configurationSection = Config.getConfigurationSection("islands");
        List<String> islandOwners = new ArrayList<String>(configurationSection.getKeys(false));
        if(islandOwners.size()==0){
            //None island
            return null;
        }else {
            return islandOwners.get(islandOwners.size() - 1);
        }
    }
}
