package ytext.custom.client.modules.impl.misc;

import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;

@ModuleInfo(name = "Memory Usage", description = "Shows the memory usage of total available memory", type = ModuleCategory.MISC)
public class MemoryUsageModule extends Module {


    private static final long GIGA = 1024L * 1024L * 1024L;

    @EventHandler
    public void onRender2D(Render2DEvent e) {
        Runtime r = Runtime.getRuntime();


        double free = (double) Math.round((double) r.freeMemory() / GIGA * 10) / 10;
        double total = (double) Math.round((double) r.totalMemory() / GIGA * 10) / 10;


        e.drawItem(String.format("Memory: %.1fGB / %.1fGB", free, total));
    }
}







