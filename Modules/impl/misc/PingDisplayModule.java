package ytext.custom.client.modules.impl.misc;

import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleInfo;
import net.minecraft.client.Minecraft;

@ModuleInfo(name = "Ping", description = "Displays the player ping")
public class PingDisplayModule extends Module {
    @EventHandler
    public void onRender2D(Render2DEvent e) {
        long ping = 0;
        Minecraft mc = e.getMinecraft();
        if (mc.getCurrentServerData() != null)
            ping = mc.getCurrentServerData().pingToServer;
        e.drawItem("Ping: " + ping);
    }
}
