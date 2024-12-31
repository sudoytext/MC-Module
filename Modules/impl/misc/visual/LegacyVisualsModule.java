package ytext.custom.client.modules.impl.misc.visual;
import ytext.custom.client.event.impl.ItemRenderAnimationEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;

@ModuleInfo(name = "1.7 Visuals", description = "Brings back 1.7 animations", type = ModuleCategory.VISUAL)
public class LegacyVisualsModule extends Module {

    @EventHandler
    public void onItemRender(ItemRenderAnimationEvent e) {
        ItemRenderer renderer = e.getItemRenderer();
        switch (e.getAction()) {
            case EAT:
            case DRINK:
//                float f = 1.0F - (renderer + (renderer.getEquippedProgress() - renderer.resetEquippedProgress2()) * e.getPartialTicks();
//                renderer.transformFirstPersonItem(f, 0);
//                oldDrinking(e.getItem(), e.getPlayer(), e.getPartialTicks());
//                e.isCancelled() = true;
                break;

        }
    }

    public void oldDrinking(ItemStack itemToRender, AbstractClientPlayer clientPlayer,
                            float partialTicks) {
        float var14 = clientPlayer.getItemInUseCount() - partialTicks + 1.0F;
        float var15 = 1.0F - var14 / itemToRender.getMaxItemUseDuration();
        float var16 = 1.0F - var15;
        var16 = var16 * var16 * var16;
        var16 = var16 * var16 * var16;
        var16 = var16 * var16 * var16;
        var16 -= 0.05F;
        float var17 = 1.0F - var16;
        GlStateManager.translate(0.0F, MathHelper.abs(MathHelper.cos(var14 / 4F * (float) Math.PI) * 0.11F)
                * (var15 > 0.2D ? 1 : 0), 0.0F);
        GlStateManager.translate(var17 * 0.6F, -var17 * 0.5F, 0.0F);
        GlStateManager.rotate(var17 * 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(var17 * 10.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(var17 * 30.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(0, -0.0F, 0.06F);
        GlStateManager.rotate(-4F, 1, 0, 0);
    }


    public void oldBlocking() {
        GlStateManager.scale(0.83F, 0.88F, 0.85F);
        GlStateManager.translate(-0.3F, 0.1F, 0.0F);
    }

    public static void tick() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer != null && mc.thePlayer.capabilities.allowEdit && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && mc.thePlayer != null && mc.gameSettings.keyBindAttack.isKeyDown() && mc.gameSettings.keyBindUseItem.isKeyDown() && mc.thePlayer.getItemInUseCount() > 0) {
//            if ((!mc.thePlayer.isSwingInProgress || mc.thePlayer.swingProgressInt >= mc.thePlayer.getArmSwingAnimationEnd() / 2 || mc.thePlayer.swingProgressInt < 0)) {
                mc.thePlayer.swingProgressInt = -1;
                mc.thePlayer.isSwingInProgress = true;
//            }

            mc.effectRenderer.addBlockHitEffects(mc.objectMouseOver.getBlockPos(), mc.objectMouseOver.sideHit);
        }
    }

    public void transformItem(Item item) {
        if (Minecraft.getMinecraft().thePlayer.isUsingItem() && item instanceof ItemBow) {
            GlStateManager.translate(-0.01f, 0.05f, -0.06f);
        } else if ((item instanceof ItemFishingRod)) {
            GlStateManager.translate(0.08f, -0.027f, -0.33f);
            GlStateManager.scale(0.93f, 1.0f, 1.0f);
        }
    }
}
