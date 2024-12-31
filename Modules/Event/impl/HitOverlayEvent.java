package ytext.custom.client.event.impl;

import ytext.custom.client.UrCL;
import ytext.custom.client.event.types.Event;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;


public class HitOverlayEvent extends Event {

    private float red;
    private float green;
    private float blue;
    private float alpha;

    public HitOverlayEvent(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public HitOverlayEvent(Minecraft minecraft) {
        super(minecraft);
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red;
    }

    public float getGreen() {
        return green;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
}
