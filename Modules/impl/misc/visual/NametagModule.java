package ytext.custom.client.modules.impl.misc.visual;

import ytext.custom.client.event.impl.Render3DEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

import java.awt.*;

@ModuleInfo(name = "NameTag", description = "Adds tweaks to NameTags.", type = ModuleCategory.VISUAL)
public class NametagModule extends Module {

    private final Minecraft mc = Minecraft.getMinecraft();


    private boolean nameTagInThirdPerson = true;
    private int opacity = 255;
    private int size = 3;
    private float yPosition = 5.0f;
    private boolean disablePlayerNameTags = false;
    private Color fontColor = Color.WHITE;

    @Override
    public void onEnable() {
        super.onEnable();


    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @EventHandler
    public void onRender3D(Render3DEvent e) {
        if (disablePlayerNameTags) {
            return;
        }

        if (mc.gameSettings.thirdPersonView == 0 || nameTagInThirdPerson) {
            for (Object obj : mc.theWorld.loadedEntityList) {
                if (obj instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) obj;
                    renderNameTag(player, e.getPartialTicks());
                }
            }
        }
    }

    private void renderNameTag(EntityPlayer player, float partialTicks) {
        double x = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
        double y = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks + yPosition;
        double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;

        String name = player.getDisplayName().getFormattedText();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y + size, z);
        GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-0.025f * size, -0.025f * size, 0.025f * size);

        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GlStateManager.color(
                fontColor.getRed() / 255f,
                fontColor.getGreen() / 255f,
                fontColor.getBlue() / 255f,
                opacity / 255f
        );

        drawCenteredString(mc.fontRendererObj, name, 0, 0, fontColor.getRGB());

        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    private void drawCenteredString(net.minecraft.client.gui.FontRenderer fontRenderer, String text, int x, int y, int color) {
        int width = fontRenderer.getStringWidth(text);
        fontRenderer.drawString(text, x - width / 2, y, color);
    }


    private void loadSettings() {


    }
}
