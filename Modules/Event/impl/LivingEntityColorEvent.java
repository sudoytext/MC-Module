package ytext.custom.client.event.impl;

import ytext.custom.client.event.types.Event;
import net.minecraft.client.Minecraft;
import java.awt.*;

public class LivingEntityColorEvent extends Event {
    public final boolean hurt;
    public Color color;

    public LivingEntityColorEvent(boolean hurt, Color color, Minecraft mc) {
        super(mc);
        this.color = color;
        this.hurt = hurt;
    }
}
