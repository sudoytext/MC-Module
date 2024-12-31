package ytext.custom.client.event.impl;

import ytext.custom.client.event.types.CancellableEvent;
import ytext.custom.client.event.types.Event;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;

@Getter
@Setter
public class PlayerAnimationEvent extends CancellableEvent {
    private AnimationType type;

    public PlayerAnimationEvent(Minecraft minecraft, AnimationType type) {
        super(minecraft);
        this.type = type;
        minecraft.gameSettings.keyBindSprint.isPressed();
    }   

    public static enum AnimationType {
        DRINK,
        EAT,
        EQUIP,
    }
}
