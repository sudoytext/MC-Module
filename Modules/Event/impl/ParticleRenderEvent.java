package ytext.custom.client.event.impl;

import ytext.custom.client.event.types.CancellableEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.EnumParticleTypes;

public class ParticleRenderEvent extends CancellableEvent {
    public RenderGlobal renderer;
    public EnumParticleTypes type;

    public ParticleRenderEvent(Minecraft mc, RenderGlobal renderer, EnumParticleTypes type) {
        super(mc);
        this.renderer = renderer;
        this.type = type;
    }
}
