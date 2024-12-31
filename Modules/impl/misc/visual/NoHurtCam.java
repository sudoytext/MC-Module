package ytext.custom.client.modules.impl.misc.visual;
// Needs to fix later
import ytext.custom.client.event.impl.Listener;
import ytext.custom.client.event.impl.CameraRotateEvent;
import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import ytext.custom.client.modules.settings.Setting;
import ytext.custom.client.modules.types.ModuleSetting;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

@ModuleInfo(name = "NoHurtCam", description = "Disables the camera shaking when damaged", version = "1.0", type = ModuleCategory.MISC)
public class NoHurtCam extends Module {

    private List<CameraRotateEvent.Reason> reasonList = new ArrayList<>();

//    @Setting
//    public ModuleSetting<List<CameraRotateEvent.Reason>> reasons = new ModuleSetting<>(
//            "Camera Movement Reasons",
//            () -> getReasons(),
//            reasons -> setReasons(reasons)
//    );

    private int x = 10;
    private int y = 10;

    private void setReasons(List<CameraRotateEvent.Reason> reasons) {
        this.reasonList = reasons;
    }

    public List<CameraRotateEvent.Reason> getReasons() {
        return reasonList;
    }

    @Listener
    public void onRender2D(Render2DEvent e) {
        getMinecraft().fontRendererObj.drawStringWithShadow("NoHurtCam", x, y, Color.WHITE.getRGB());
    }

    @Listener
    public void onCameraRotate(CameraRotateEvent e) {
        if (reasonList.contains(e.getReason())) {
            e.setCancelled(true);
        }
    }
}
