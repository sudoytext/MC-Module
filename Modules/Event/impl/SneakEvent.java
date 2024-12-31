package ytext.custom.client.event.impl;

import ytext.custom.client.event.types.CancellableEvent;
import ytext.custom.client.event.types.KeyMode;
import net.minecraft.client.Minecraft;

public class SneakEvent extends CancellableEvent {
    public KeyMode mode = KeyMode.DOWN;

    public SneakEvent(Minecraft mc) {
        super(mc);
    }

    public SneakEvent(KeyMode mode, Minecraft mc) {
        super(mc);
        this.mode = mode;
    }
}
