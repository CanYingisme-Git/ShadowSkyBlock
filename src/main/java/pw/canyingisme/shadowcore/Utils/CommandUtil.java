package pw.canyingisme.shadowcore.Utils;


import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;
import pw.canyingisme.shadowcore.Core;
import pw.canyingisme.shadowcore.Message.Message;

import java.lang.reflect.Field;
import java.util.List;

public class CommandUtil {
    Message msg = new Message();
    public boolean registerCommand(Command command,JavaPlugin plugin){
        return getReflect().register(command.getName(),command);
    }
    public CommandMap getReflect(){
        Field commandMap = null;
        try {
            commandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        } catch (NoSuchFieldException e) {
            System.out.println("[ShadowCore] " + msg.reflectError("CommandMap"));
            e.printStackTrace();
        }
        commandMap.setAccessible(true);
        CommandMap map = null;
        try {
            map = (CommandMap) commandMap.get(Bukkit.getServer());
        } catch (IllegalAccessException e) {
            System.out.println("[ShadowCore] " + msg.reflectError("CommandMap"));
            e.printStackTrace();
        }
        if (map == null){
            System.out.println("[ShadowCore] " + msg.reflectError("CommandMap"));
            return null;
        }
        return map;
    }
}
