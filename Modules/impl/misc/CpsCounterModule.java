package ytext.custom.client.modules.impl.misc;

import ytext.custom.client.event.impl.MouseClickEvent;
import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleInfo;
import org.lwjgl.util.Timer;

@ModuleInfo(name = "CPS", description = "Shows your current Clicks per second")
public class CpsCounterModule extends Module {
    public Timer timer = new Timer();
    private int cps;

    @EventHandler
    public void onClick(MouseClickEvent e) {
        if (e.isLeftClick()) cps++;
    }

    @EventHandler
    public void onRender(Render2DEvent e) {
        if (timer.getTime() >= 1) {
            cps = 0;
            timer.reset();
        }
        e.drawItem("CPS: " + cps);
    }

    @Override
    public void onEnable() {
        timer.resume();
    }

    @Override
    public void onDisable() {
        timer.pause();
    }
}
