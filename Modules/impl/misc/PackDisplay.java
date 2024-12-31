package ytext.custom.client.modules.impl.misc;

import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;

@ModuleInfo(name = "Pack Display", description = "Displays the selected pack details", type = ModuleCategory.MISC)
public class PackDisplay extends Module {

    @EventHandler
    public void onRender2D(Render2DEvent e) {
        Minecraft mc = e.getMinecraft();


        int currentY = 0;

        IResourcePack resourcePack = mc.getResourcePackRepository().rprDefaultResourcePack;
        DynamicTexture dynamicTexture;
        try {
            dynamicTexture = new DynamicTexture(resourcePack.getPackImage());
        } catch (IOException ex) {
            dynamicTexture = TextureUtil.missingTexture;
        }

        ResourceLocation textureLocation = mc.getTextureManager().getDynamicTextureLocation("texturepackicon", dynamicTexture);
        GlStateManager.enableBlend();
        GlStateManager.popMatrix();


        mc.getTextureManager().bindTexture(textureLocation);
        Gui.drawModalRectWithCustomSizedTexture(0, currentY, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);


        currentY += 34;


        GlStateManager.pushMatrix();
        FontRenderer fontRenderer = mc.fontRendererObj;
        fontRenderer.drawString(resourcePack.getPackName(), 0, currentY, Color.white.getRGB());


        currentY += 18;
    }
}
