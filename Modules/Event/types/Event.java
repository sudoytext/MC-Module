package ytext.custom.client.event.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

/**
 * Basic form of Events that any kind of Event should implement.
 * Mostly used for not breaking the game codes.
 */
@Getter @Setter @AllArgsConstructor
public class Event {
    private Minecraft minecraft;

    public Event() {
    }
}
