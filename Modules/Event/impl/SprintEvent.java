package ytext.custom.client.event.impl;

import ytext.custom.client.event.impl.KeyPressEvent;
import ytext.custom.client.event.EventManager;
import ytext.custom.client.event.types.CancellableEvent;
import ytext.custom.client.event.types.Event;
import ytext.custom.client.event.types.EventHandler;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;

@Getter @Setter
public class SprintEvent extends Event {
    private boolean toggle;

    public SprintEvent(Minecraft minecraft) {
        super(minecraft);
    }

}
