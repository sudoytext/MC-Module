package ytext.custom.client.event.impl;

import ytext.custom.client.event.types.CancellableEvent;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
@Getter @Setter
public class ItemRenderAnimationEvent extends CancellableEvent {
    private boolean cancelled;
    private EnumAction action;
    private ItemRenderer itemRenderer;
    private ItemStack item;
    private AbstractClientPlayer player;
    private float partialTicks;

    public ItemRenderAnimationEvent(Minecraft mc, EnumAction a, ItemRenderer renderer, ItemStack item, float partialTicks) {
        super(mc);
        this.action = a;
        this.itemRenderer = renderer;
        this.item = item;
        this.player = mc.thePlayer;
        this.partialTicks = partialTicks;
    }
}
