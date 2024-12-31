package ytext.custom.client.event.impl;

import ytext.custom.client.event.types.Event;
import ytext.custom.client.utils.Helper2D;
import lombok.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;

/**
 * Event that is being called when UI is being rendered.
 */
@Getter
public class Render2DEvent extends Event {
    private final ScaledResolution scaledResolution;
    /**
     * current y that item can have
     */
    private int y = 2;
    /**
     * current x that item can have
     */
    private int x = 2;
    /**
     * default color of texts in the event
     */
    private final Color color = Color.CYAN;

    public Render2DEvent(Minecraft minecraft, ScaledResolution sc) {
        super(minecraft);
        this.scaledResolution = sc;
    }

    public int drawItem(String text) {
        Minecraft mc = getMinecraft();
        FontRenderer fr = mc.getFontRendererObj();
        Helper2D.INSTANCE.drawRect(x, x + fr.getStringWidth(text) + 4, y, y + fr.FONT_HEIGHT + 10, new Color(0, 0, 0, 0.30F));
        fr.drawString(text, x + 2, 5 + y, color.getRGB());
        y += fr.FONT_HEIGHT + 12;
        return fr.getStringWidth(text) + x;
    }
}
