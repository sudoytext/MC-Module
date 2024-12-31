package ytext.custom.client.event.impl;

import ytext.custom.client.crosshair.Crosshair;
import ytext.custom.client.event.types.CancellableEvent;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
@Getter @Setter
public class CrosshairRenderEvent extends CancellableEvent {
    public final GuiIngame inGame;
    private Crosshair crosshair;

    public CrosshairRenderEvent(Minecraft mc, GuiIngame inGame, Crosshair crosshair) {
        super(mc);
        this.inGame = inGame;
        this.crosshair = crosshair;
    }


    public Crosshair getCrosshair() {
        return crosshair;
    }


    public void setCrosshair(Crosshair crosshair) {
        this.crosshair = crosshair;
    }
}

