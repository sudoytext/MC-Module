package ytext.custom.client.modules.impl.misc.visual;

import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

@Getter
@Setter
@ModuleInfo(
        name = "Zoom",
        description = "Allows you to zoom into the world.",
        author = "customClient",
        version = "1.0",
        type = ModuleCategory.MISC
)
public class ScrollZoomModule extends Module {

    private static final Minecraft mc = Minecraft.getMinecraft();
    private static boolean zoom = false;


    private static int keybinding = Keyboard.KEY_C;
    private static float zoomAmount = 100f;
    private static boolean smoothZoom = true;
    private static float animationProgress = 0f;

    public ScrollZoomModule() {

    }

    @Override
    public void onEnable() {
        super.onEnable();

    }

    @Override
    public void onDisable() {
        super.onDisable();

    }

    @EventHandler
    public void onRender2D(Render2DEvent e) {

        if (smoothZoom) {
            float targetFOV = zoomAmount;
            float currentFOV = mc.gameSettings.fovSetting;
            animationProgress += (targetFOV - currentFOV) * 0.1f;
            mc.gameSettings.fovSetting = currentFOV + animationProgress;


            if (Math.abs(animationProgress) < 0.01f) {
                animationProgress = 0;
            }
        }
    }

    @EventHandler
    public void onKeyInput() {
        zoom = Keyboard.isKeyDown(getKey());
    }

    @EventHandler
    public void onMouseScroll() {
        if (Mouse.getEventDWheel() != 0) {
            int scrollAmount = Mouse.getEventDWheel() > 0 ? 1 : -1;
            zoomAmount += scrollAmount * 2;
            zoomAmount = Math.max(30, Math.min(zoomAmount, 200));
        }
    }

    public static float getFOV() {
        if (smoothZoom) {
            return mc.gameSettings.fovSetting + animationProgress;
        }
        return zoom ? zoomAmount : mc.gameSettings.fovSetting;
    }

    public static boolean isZoom() {
        return zoom;
    }

    private static boolean isSmooth() {
        return smoothZoom;
    }

    private static float getAmount() {
        return zoomAmount;
    }

    private int getKey() {
        return keybinding;
    }
}


