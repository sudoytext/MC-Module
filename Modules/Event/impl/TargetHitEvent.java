package ytext.custom.client.event.impl;

import ytext.custom.client.event.types.Event;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class TargetHitEvent extends Event {
    public Entity entity;

    public TargetHitEvent(Entity entity, Minecraft mc) {
        super(mc);
        this.entity = entity;
    }
}
