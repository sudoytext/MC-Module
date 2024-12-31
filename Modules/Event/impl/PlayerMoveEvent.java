package ytext.custom.client.event.impl;

import ytext.custom.client.event.types.Event;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
@Getter @Setter
public class PlayerMoveEvent extends Event {
    private float prevYaw;
    private float prevPitch;
    private double prevX;
    private double prevY;
    private double prevZ;

    public PlayerMoveEvent(Minecraft mc, double prevX, double prevY, double prevZ, float prevYaw, float prevPitch) {
        super(mc);
        this.prevYaw = prevYaw;
        this.prevPitch = prevPitch;
        this.prevX = prevX;
        this.prevY = prevY;
        this.prevZ = prevZ;
    }

}
