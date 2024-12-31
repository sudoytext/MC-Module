package ytext.custom.client.modules.impl.misc;

import ytext.custom.client.event.impl.WorldTimeChangeEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;

@ModuleInfo(name = "Time Changer", description = "Changes the time", type = ModuleCategory.MISC)
public class TimeChangerModule extends Module {
    public long time = 0;


    @EventHandler
    public void onWorldTimeChange(WorldTimeChangeEvent e) {
        e.time = time;
    }
}
