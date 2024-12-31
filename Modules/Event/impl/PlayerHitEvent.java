package ytext.custom.client.event.impl;

import ytext.custom.client.event.types.Event;
import net.minecraft.client.Minecraft;
import net.minecraft.util.DamageSource;

public class PlayerHitEvent extends Event {
    public DamageSource source;

    public PlayerHitEvent(DamageSource attacker, Minecraft mc) {
        super(mc);
        this.source = attacker;
    }

}
