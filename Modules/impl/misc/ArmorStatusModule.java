package ytext.custom.client.modules.impl.misc;

import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;

import java.awt.*;

@ModuleInfo(name = "Armor Status", description = "Displays the status of your equipped armors", type = ModuleCategory.MISC)
public class ArmorStatusModule extends Module {

    public int x = -1;
    public Color color = Color.WHITE;

    @EventHandler
    public void onRender2D(Render2DEvent e) {
        if (e.getMinecraft().thePlayer == null || e.getMinecraft().thePlayer.inventory == null) return;

        EntityPlayerSP player = e.getMinecraft().thePlayer;
        int x = this.x == -1 ? e.getScaledResolution().getScaledWidth() - 18 : this.x;
        int y = e.getScaledResolution().getScaledHeight() - 18;

        RenderItem render = e.getMinecraft().getRenderItem();


        ItemStack[] armorInventory = player.inventory.armorInventory;
        for (int i = armorInventory.length - 1; i >= 0; i--) {
            ItemStack item = armorInventory[i];
            if (item != null) {
                drawIcon(item, x, y, e);
                y -= 18; // Move up for the next item
            }
        }


        ItemStack heldItem = player.inventory.getCurrentItem();
        if (heldItem != null) {
            drawIcon(heldItem, x, y, e);
        }
    }

    private void drawIcon(ItemStack item, int x, int y, Render2DEvent e) {
        RenderItem render = e.getMinecraft().getRenderItem();


        String durability = "";
        if (item.getMaxDamage() > 0) {
            int currentDurability = item.getMaxDamage() - item.getItemDamage();
            durability = String.valueOf(currentDurability);
        }


        render.renderItemIntoGUI(item, x - 18, y);


        int width = e.getMinecraft().fontRendererObj.getStringWidth(durability);
        int textX = (x - 20) - width;


        e.getMinecraft().fontRendererObj.drawString(durability, textX, y + e.getMinecraft().fontRendererObj.FONT_HEIGHT / 2, color.getRGB(), true);
    }
}
