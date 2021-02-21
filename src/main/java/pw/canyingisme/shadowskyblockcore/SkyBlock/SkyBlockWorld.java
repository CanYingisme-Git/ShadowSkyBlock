package pw.canyingisme.shadowskyblockcore.SkyBlock;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import pw.canyingisme.shadowcore.Message.SkyBlockMessage;

public class SkyBlockWorld {
    SkyBlockMessage message = new SkyBlockMessage();

    public boolean GenerateWorldOverWorld() {
        WorldCreator worldCreator = new WorldCreator("SkyBlockOverWorld");
        worldCreator.environment(World.Environment.NORMAL);
        worldCreator.type(WorldType.FLAT);
        World world = Bukkit.getServer().createWorld(worldCreator);
        if (world == null) {
            System.out.println(message.OverWorldCreateFaltMessage());
            return false;
        }
        System.out.println(message.OverWorldCreateFinishMessage());
        return true;
    }

    public boolean GenerateWorldNether() {
        WorldCreator worldCreator = new WorldCreator("SkyBlockNether");
        worldCreator.environment(World.Environment.NETHER);
        worldCreator.type(WorldType.FLAT);
        World world = Bukkit.getServer().createWorld(worldCreator);
        if (world == null) {
            System.out.println(message.NetherCreateFaltMessage());
            return false;
        }
        System.out.println(message.NetherCreateFinishMessage());
        return true;
    }

    public boolean isNetherExits() {
        return Bukkit.getServer().getWorld("SkyBlockNether") != null;
    }

    public boolean isOverWorldExits() {
        return Bukkit.getServer().getWorld("SkyBlockOverWorld") != null;
    }

    public World getOverWorld(){
        return Bukkit.getServer().getWorld("SkyBlockOverWorld");
    }
    public World getNether(){
        return Bukkit.getServer().getWorld("SkyBlockNether");
    }
}
