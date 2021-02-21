package pw.canyingisme.shadowskyblockcore.SkyBlock;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

public class Island {
    Location skyBlockLocationA;
    Location skyBlockLocationB;
    Location center;
    UUID uuid;
    public Island(Location skyBlockLocationA, Location skyBlockLocationB,Location center, UUID uuid){
        this.skyBlockLocationA = skyBlockLocationA;
        this.skyBlockLocationB = skyBlockLocationB;
        this.center = center;
        this.uuid = uuid;
    }
}
