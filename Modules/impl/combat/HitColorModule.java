package ytext.custom.client.modules.impl.ytextbat;

import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import net.minecraft.client.gui.FontRenderer;

import java.awt.*;

@ModuleInfo(name = "Hit Color", description = "color will be changed by attacked entity hit color", type = ModuleCategory.ytextBAT)
public class HitColorModule extends Module {
    public Color color = Color.ORANGE;

    @EventHandler
    public void onRender2D(Render2DEvent e) {
        FontRenderer fr = e.getMinecraft().fontRendererObj;
        String s = "Custom Hit Color: ";
        fr.drawString("#", e.drawItem(s), e.getY(), color.getRGB());
    }

    @EventHandler
    public void onColorRender(ytext.custom.client.event.impl.LivingEntityColorEvent e) {
        if (e.hurt) {
            e.color = color;
        }
    }
}