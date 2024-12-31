package ytext.custom.client.event.impl;

import ytext.custom.client.event.types.CancellableEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;

public class RenderWorldEvent extends CancellableEvent {
    private final float partialTicks;
    private final EntityRenderer renderer;

    /**
     * Constructor for the RenderWorldEvent.
     *
     * @param partialTicks The partial ticks used for smooth rendering between frames.
     * @param renderer The EntityRenderer instance handling the rendering.
     */
    public RenderWorldEvent(float partialTicks, EntityRenderer renderer) {
        super(Minecraft.getMinecraft());
        this.partialTicks = partialTicks;
        this.renderer = renderer;
    }

    /**
     * Get the partial ticks for the current frame rendering.
     *
     * @return The partial ticks value.
     */
    public float getPartialTicks() {
        return partialTicks;
    }

    /**
     * Get the EntityRenderer handling the world rendering.
     *
     * @return The EntityRenderer instance.
     */
    public EntityRenderer getRenderer() {
        return renderer;
    }
}
