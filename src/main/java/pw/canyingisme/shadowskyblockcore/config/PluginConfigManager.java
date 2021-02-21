package pw.canyingisme.shadowskyblockcore.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PluginConfigManager extends ConfigManager{
    private static YamlConfiguration Config;
    public PluginConfigManager(YamlConfiguration defaultConfig) {
        super(defaultConfig, new File(new File("").getAbsolutePath() + "/plugins/ShadowSkyBlock/config.yml"));
        Config = getYamlConfig();
    }
    public int getSkyBlockRadius(){
        return Config.getInt("islandRadius");
    }
    public int getSkyBlockRange(){
        return Config.getInt("islandRange");
    }
    public String getPrefix(){
        return Config.getString("prefix");
    }
}
