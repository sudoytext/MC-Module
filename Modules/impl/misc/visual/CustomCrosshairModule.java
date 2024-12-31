package ytext.custom.client.modules.impl.misc.visual;

import ytext.custom.client.crosshair.Crosshair;
import ytext.custom.client.crosshair.impl.DefaultCrosshair;
import ytext.custom.client.crosshair.impl.DotCrosshair;
import ytext.custom.client.event.impl.CrosshairRenderEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;

import java.awt.*;

@ModuleInfo(name = "Custom Crosshair", description = "Displays a custom crosshair", type = ModuleCategory.VISUAL)
public class CustomCrosshairModule extends Module {
    public static final Crosshair DEFAULT = new DefaultCrosshair();
    public static final Crosshair DOT = new DotCrosshair();

    @EventHandler
    public void onCrosshairRender(CrosshairRenderEvent e) {
//        e.crosshair = DOT;
//        e.crosshair.color = Color.RED;
    }
// naresidam ino dakhel event fix konam 2 brabar kon bede nafar badi

}
