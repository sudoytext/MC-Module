package ytext.custom.client.modules.impl.misc.visual;

import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.event.impl.Render3DEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.util.Objects;
import java.util.Random;

@ModuleInfo(name = "Item Physics", description = "Realistic item drops", type = ModuleCategory.MISC)
public class ItemPhysicsModule extends Module {
    private Minecraft mc = Minecraft.getMinecraft();
    private final Random random = new Random();
    public float rotateSpeed = 1.0F;

    @EventHandler
    public void onRender3D(Render3DEvent e) {
        for (EntityItem entity : mc.theWorld.getEntities(EntityItem.class, Objects::nonNull)) {
            ItemStack itemStack = entity.getEntityItem();
            if (itemStack == null) return;


            float partialTicks = mc.getTimer().renderPartialTicks;

            double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks;
            double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks;
            double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks;


            mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);

            GlStateManager.enableRescaleNormal();
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
            GlStateManager.pushMatrix();

            IBakedModel model = mc.getRenderItem().getItemModelMesher().getItemModel(itemStack);
            boolean isGui3d = model.isGui3d();


            GlStateManager.translate(x, y + 0.2F, z);
            GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(rotateSpeed * (entity.getAge() + partialTicks), 0.0F, 1.0F, 0.0F);

            if (isGui3d) {
                GlStateManager.scale(0.5F, 0.5F, 0.5F);
            } else {
                GlStateManager.scale(0.25F, 0.25F, 0.25F);
            }


            mc.getRenderItem().renderItem(itemStack, model);

            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
        }
    }

    @EventHandler
    public void onRender2D(Render2DEvent e) {
        e.drawItem("ItemPhysics");
    }

    private int getModelCount(ItemStack stack) {
        int count = 1;
        if (stack.stackSize > 40) {
            count = 5;
        } else if (stack.stackSize > 32) {
            count = 4;
        } else if (stack.stackSize > 16) {
            count = 3;
        } else if (stack.stackSize > 1) {
            count = 2;
        }
        return count;
    }
}
