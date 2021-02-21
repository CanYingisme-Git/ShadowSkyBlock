package pw.canyingisme.shadowcore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;
import pw.canyingisme.shadowcore.Command.CommandExecute;
import pw.canyingisme.shadowcore.Event.CoreEventListener;
import pw.canyingisme.shadowcore.Message.Message;
import pw.canyingisme.shadowcore.Utils.CommandUtil;
import pw.canyingisme.shadowcore.Event.CoreEventListener;

import java.util.*;
import java.util.logging.Logger;

public final class Core{
    JavaPlugin MainPlugin;
    Logger logger;
    Message message = new Message();
    Command command = null;

    public Core(JavaPlugin plugin){
        MainPlugin = plugin;
        MainPlugin.getLogger();
    }
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[ShadowCore] " +message.EnableMessage());
        ArrayList<String> list = new ArrayList<String>();
        list.add("sc");
        command = new Command("shadowcore",
                "ShadowCore Command",
                "/shadowcore or /sc",
                list) {
            @Override
            public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                return new CommandExecute().onCommand(sender,this,commandLabel,args);
            }
        };
        MainPlugin.getServer().getPluginManager().addPermission(new Permission("shadowcore.command.shadowcore.use", PermissionDefault.OP));
        command.setPermission("shadowcore.command.shadowcore.use");
        new CommandUtil().registerCommand(command,MainPlugin);
        Bukkit.getPluginManager().registerEvents(new CoreEventListener(),MainPlugin);

    }
    public void onDisable() {
        // Plugin shutdown logic
        logger.info(message.disableMessage());
    }
}
