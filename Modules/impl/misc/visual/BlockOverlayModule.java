package ytext.custom.client.modules.impl.misc.visual;

import ytext.custom.client.event.impl.Render3DEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import lombok.Setter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.awt.Color;

@ModuleInfo(name = "Block Overlay", description = "Draws an overlay on the block you're looking at.", type = ModuleCategory.VISUAL)
public class BlockOverlayModule extends Module {

    private Minecraft mc = Minecraft.getMinecraft();
    private Color overlayColor = new Color(0, 255, 0, 100);  // Green with 100 alpha (semi-transparent)
    @Setter
    private float lineWidth = 2.0F;

    @EventHandler
    public void onRender3D(Render3DEvent e) {

        MovingObjectPosition objectMouseOver = mc.objectMouseOver;

        if (objectMouseOver == null || objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) {
            return;
        }

        BlockPos blockPos = objectMouseOver.getBlockPos();
        World world = mc.theWorld;

        if (blockPos == null || world == null) {
            return;
        }

        IBlockState blockState = world.getBlockState(blockPos);
        if (blockState == null) {
            return;
        }


        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GlStateManager.depthMask(false);

        Entity player = mc.thePlayer;
        double interpX = player.lastTickPosX + (player.posX - player.lastTickPosX) * e.getPartialTicks();
        double interpY = player.lastTickPosY + (player.posY - player.lastTickPosY) * e.getPartialTicks();
        double interpZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * e.getPartialTicks();
        GlStateManager.translate(-interpX, -interpY, -interpZ);

        GL11.glLineWidth(lineWidth);
        float red = overlayColor.getRed() / 255.0F;
        float green = overlayColor.getGreen() / 255.0F;
        float blue = overlayColor.getBlue() / 255.0F;
        float alpha = overlayColor.getAlpha() / 255.0F;
        GL11.glColor4f(red, green, blue, alpha);

        drawBoundingBox(blockPos, blockState.getBlock().getSelectedBoundingBox(world, blockPos));

        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }


    private void drawBoundingBox(BlockPos blockPos, net.minecraft.util.AxisAlignedBB box) {
        double x = blockPos.getX();
        double y = blockPos.getY();
        double z = blockPos.getZ();

        GL11.glBegin(GL11.GL_LINES);

        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x + 1, y, z);
        GL11.glVertex3d(x + 1, y, z);
        GL11.glVertex3d(x + 1, y + 1, z);
        GL11.glVertex3d(x + 1, y + 1, z);
        GL11.glVertex3d(x, y + 1, z);
        GL11.glVertex3d(x, y + 1, z);
        GL11.glVertex3d(x, y, z);

        GL11.glVertex3d(x, y, z + 1);
        GL11.glVertex3d(x + 1, y, z + 1);
        GL11.glVertex3d(x + 1, y, z + 1);
        GL11.glVertex3d(x + 1, y + 1, z + 1);
        GL11.glVertex3d(x + 1, y + 1, z + 1);
        GL11.glVertex3d(x, y + 1, z + 1);
        GL11.glVertex3d(x, y + 1, z + 1);
        GL11.glVertex3d(x, y, z + 1);

        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x, y, z + 1);
        GL11.glVertex3d(x + 1, y, z);
        GL11.glVertex3d(x + 1, y, z + 1);
        GL11.glVertex3d(x + 1, y + 1, z);
        GL11.glVertex3d(x + 1, y + 1, z + 1);
        GL11.glVertex3d(x, y + 1, z);
        GL11.glVertex3d(x, y + 1, z + 1);

        GL11.glEnd();
    }


}
