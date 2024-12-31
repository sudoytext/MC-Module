package ytext.custom.client.event.types;

import ytext.custom.client.types.Cancellable;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

/**
 * Events with the feature of being able to get cancelled at time.
 */
@Setter @Getter
public class CancellableEvent extends Event implements Cancellable {
    public boolean cancelled;

    public CancellableEvent(Minecraft minecraft) {
        super(minecraft);
    }
}
