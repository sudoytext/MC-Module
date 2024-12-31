package ytext.custom.client.modules.impl.movement;

import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;

@ModuleInfo(name = "FOV", description = "Changes the field of view", type = ModuleCategory.MOVEMENT)
public class FovChangerModule extends Module {
    public float fov = 120;

    @EventHandler
    public void onRender2D(Render2DEvent e) {
        e.drawItem("FOV: " + fov);
        e.getMinecraft().gameSettings.fovSetting = fov;
    }
}
