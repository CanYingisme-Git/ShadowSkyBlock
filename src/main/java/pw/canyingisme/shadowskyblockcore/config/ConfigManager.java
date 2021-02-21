package pw.canyingisme.shadowskyblockcore.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private static YamlConfiguration Config;
    private static File file;
    public ConfigManager(YamlConfiguration defaultConfig, File configFile){
        file = configFile;
        Config = new YamlConfiguration();
        if (file.exists()){
            try {
                Config.load(file);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }else {
            try {
                Config = defaultConfig;
                Config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public YamlConfiguration getYamlConfig(){
        return Config;
    }
    public void saveYamlConfig(YamlConfiguration YamlConfig){
        try {
            Config = YamlConfig;
            Config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
