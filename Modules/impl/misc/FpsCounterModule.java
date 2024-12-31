package ytext.custom.client.modules.impl.misc;

import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleInfo;
import net.minecraft.client.Minecraft;

@ModuleInfo(name = "FPS",description = "Shows your current FPS")
public class FpsCounterModule extends Module {
    @EventHandler
    public void onRender2D(Render2DEvent e) {
        e.drawItem("FPS: " + Minecraft.getDebugFPS());
    }
}
