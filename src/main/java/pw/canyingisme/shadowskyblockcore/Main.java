package pw.canyingisme.shadowskyblockcore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;
import pw.canyingisme.shadowcore.Command.CommandExecute;
import pw.canyingisme.shadowcore.Core;
import pw.canyingisme.shadowcore.Utils.CommandUtil;
import pw.canyingisme.shadowskyblockcore.Command.SkyBlockCommand;
import pw.canyingisme.shadowskyblockcore.SkyBlock.SkyBlockManager;
import pw.canyingisme.shadowskyblockcore.SkyBlock.SkyBlockWorld;
import pw.canyingisme.shadowskyblockcore.event.EventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    Logger logger = this.getLogger();
    Core ShadowCore;

    @Override
    public void onEnable() {
        // Plugin startup logic
        logger.info("Loading ShadowCore");
        ShadowCore = new Core(this);
        ShadowCore.onEnable();
        logger.info("- ShadowSkyBlockCore");
        logger.info("服务端 By:CanYingisme");
        logger.info("空岛插件为残影本人编写");
        new File( new File("").getAbsolutePath()+"/plugins/ShadowSkyBlock").mkdir();
        new File( new File("").getAbsolutePath()+"/plugins/ShadowSkyBlock/islands").mkdir();
        this.getServer().getPluginManager().addPermission(new Permission("skyblock.admin.delete", PermissionDefault.OP));
        this.getServer().getPluginManager().addPermission(new Permission("skyblock.admin.reset", PermissionDefault.OP));
        logger.info("初始化世界");
        SkyBlockManager skyBlockManager = new SkyBlockManager();
        SkyBlockWorld skyBlockWorld = skyBlockManager.getSkyBlockWorldManager();
        if(!skyBlockWorld.isNetherExits()){
            skyBlockWorld.GenerateWorldNether();
        }
        if(!skyBlockWorld.isOverWorldExits()){
            skyBlockWorld.GenerateWorldOverWorld();
        }
        skyBlockManager.loadConfig();
        skyBlockManager.loadIslands();
        logger.info("注册监听器");
        Bukkit.getPluginManager().registerEvents(new EventListener(skyBlockManager),this);
        logger.info("注册命令&权限");
        ArrayList<String> list = new ArrayList<String>();
        list.add("sb");
        Command command = new Command("skyblock",
                "SkyBlock Command",
                "/skyblock or /sb",
                list) {
            @Override
            public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                return new SkyBlockCommand(skyBlockManager).onCommand(sender,this,commandLabel,args);
            }
        };
        new CommandUtil().registerCommand(command,this);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ShadowCore.onDisable();
    }
}
