package pw.canyingisme.shadowskyblockcore.event;

import org.bukkit.event.Listener;
import pw.canyingisme.shadowskyblockcore.SkyBlock.SkyBlockManager;

/*
事件监听
 */
public class EventListener implements Listener {
    private SkyBlockManager skyBlockManager;
    private EventManager eventManager = new EventManager(skyBlockManager);
    public EventListener(SkyBlockManager skyBlockManager){
        this.skyBlockManager = skyBlockManager;
    }
    public SkyBlockManager getSkyBlockManager() {
        return skyBlockManager;
    }
    public void setSkyBlockManager(){
        this.skyBlockManager = skyBlockManager;
    }
}
