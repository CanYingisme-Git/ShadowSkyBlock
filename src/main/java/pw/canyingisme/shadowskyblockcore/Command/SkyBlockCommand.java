package pw.canyingisme.shadowskyblockcore.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pw.canyingisme.shadowskyblockcore.SkyBlock.SkyBlockManager;

public class SkyBlockCommand implements CommandExecutor {
    SkyBlockManager skyBlockManager;
    public SkyBlockCommand(SkyBlockManager skyBlockManager){
        this.skyBlockManager = skyBlockManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
            backIsland(sender);
        }
        return false;
    }
    public void backIsland(CommandSender sender){
        if(skyBlockManager.getSkyBlockConfigManager().isPlayerHasIsland(Bukkit.getPlayerExact(sender.getName()))){
            //has island
            //teleport to island
            //TODO: teleport
        }else {
            //create island
        }
    }
}
