package ytext.custom.client.modules.impl.misc;

import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

@ModuleInfo(name = "Potion Effects", type = ModuleCategory.MISC, description = "Displays active potion effects")
public class PotionEffectsModule extends Module {
    private final ResourceLocation potionIcons = new ResourceLocation("textures/gui/container/inventory.png");
    public int x = 4;
    public Color color = Color.WHITE;
    public int size = 18;

    @EventHandler
    public void onRender2D(Render2DEvent e) {
        if (e.getMinecraft().thePlayer == null) return;
        EntityPlayerSP player = e.getMinecraft().thePlayer;

        int currentY = 2;
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        e.getMinecraft().getTextureManager().bindTexture(potionIcons);

        for (PotionEffect effect : player.getActivePotionEffects()) {
            Potion potion = Potion.potionTypes[effect.getPotionID()];
            if (!potion.hasStatusIcon()) continue;

            int iconIndex = potion.getStatusIconIndex();
            int u = iconIndex % 8 * 18;
            int v = 198 + iconIndex / 8 * 18;

            drawTexture(e, this.x, currentY, u, v, size, size);

            FontRenderer fr = e.getMinecraft().fontRendererObj;
            int totalSec = effect.getDuration() / 20;
            StringBuilder secondsString = new StringBuilder(String.valueOf(totalSec % 60));
            if (secondsString.length() == 1) secondsString.insert(0, "0");
            String duration = Math.floorDiv(totalSec, 60) + ":" + secondsString;

            fr.drawString(duration, this.x + size + 2, currentY + (size / 2) - fr.FONT_HEIGHT / 2, color.getRGB());

            String effectName = I18n.format(effect.getEffectName());
            fr.drawString(effectName, this.x + size + 2, currentY + size + 2, color.getRGB());

            currentY += size + 6;
        }

        GlStateManager.popMatrix();
    }

    private void drawTexture(Render2DEvent e, int x, int y, int u, int v, int width, int height) {

        e.getMinecraft().ingameGUI.drawTexturedModalRect(x, y, u, v, width, height);
    }
}
