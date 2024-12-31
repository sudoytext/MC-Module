package ytext.custom.client.modules.impl.misc.visual;

import ytext.custom.client.event.impl.Render3DEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

@Getter
@Setter
@ModuleInfo(name = "Color Saturation", type = ModuleCategory.VISUAL, description = "Adjusts the color saturation of the game")
public class ColorSaturation extends Module {
    private float saturation = 1.0f;

    @EventHandler
    public void onRender3D(Render3DEvent e) {
        applyColorSaturation(saturation);
    }

    private void applyColorSaturation(float saturation) {
        Minecraft mc = Minecraft.getMinecraft();
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f, saturation); // Apply saturation
        GL11.glMatrixMode(GL11.GL_COLOR);
        GL11.glLoadIdentity();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, saturation);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GlStateManager.popMatrix();
    }


}
