package ytext.custom.client.event.impl;

import ytext.custom.client.event.types.CancellableEvent;
import lombok.Getter;
import net.minecraft.client.Minecraft;

@Getter
public class CameraRotateEvent extends CancellableEvent {
    private Reason reason;

    public CameraRotateEvent(Reason reason, Minecraft mc) {
        super(mc);
        this.reason = reason;
    }

    public CameraRotateEvent(Minecraft mc) {
        super(mc);
        this.reason = Reason.PLAYER_CONTROL;
    }

    public enum Reason {
        DAMAGE,
        PLAYER_CONTROL
    }
}
