package ytext.custom.client.event.impl;

import ytext.custom.client.event.types.Event;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;

@Getter
public class Render3DEvent extends Event {
    private final EntityRenderer renderer;
    private final float partialTicks;

    public Render3DEvent(Minecraft minecraft, EntityRenderer renderer, float partialTicks) {
        super(minecraft);
        this.renderer = renderer;
        this.partialTicks = partialTicks;
    }

}
