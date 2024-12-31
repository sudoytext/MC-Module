package ytext.custom.client.modules.impl.misc;

import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.event.impl.Render3DEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityTNTPrimed;
import java.util.List;

@ModuleInfo(name = "TNT Countdown", description = "Adds a countdown to explosion of nearby TNTs", type = ModuleCategory.MISC)
public class TNTCountDownModule extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    @EventHandler
    public void onRender2D(Render2DEvent e) {
        // vase pouyan Render basic string
        e.drawItem("TNT Countdown Active");
    }

    public void onRender3D(Render3DEvent e) {
        // vase pouyan Gets all active TNT entities in the world
        List<EntityTNTPrimed> tntList = mc.theWorld.getEntities(EntityTNTPrimed.class, tnt -> !tnt.isDead);

        // Loop through all the TNTs found
        for (EntityTNTPrimed tnt : tntList) {
            // vase pouyan hesab fuse time
            float fuse = tnt.fuse / 20.0f; // fuse is in ticks, dividing by 20 gives seconds
            String fuseTime = String.format("%.1f", fuse); // Format to 1 decimal point

            // Translate to TNT's position for rendering
            double x = tnt.posX - mc.getRenderManager().viewerPosX;
            double y = tnt.posY - mc.getRenderManager().viewerPosY;
            double z = tnt.posZ - mc.getRenderManager().viewerPosZ;

            // ina vase pouyan Render the fuse timer in 3D space
            renderTextInWorld(fuseTime, x, y + 1.0, z); // Render 1 block above the TNT
        }
    }

    /**
     * Helper method to render text in 3D space.
     */
    private void renderTextInWorld(String text, double x, double y, double z) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z); // Translate to the position in the world
        GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F); // Rotate to face the player
        GlStateManager.rotate(mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F); // Rotate for proper orientation

        float scale = 0.02F;
        GlStateManager.scale(-scale, -scale, scale); // Scale down the text
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.disableBlend();

        // in vase pouyan Render the string at the given coordinates
        mc.fontRendererObj.drawStringWithShadow(text, (float) mc.fontRendererObj.getStringWidth(text) / 2, 0, 0xFFFFFF);

        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GlStateManager.enableBlend();
        GlStateManager.popMatrix();
    }
}

// Needs to fix