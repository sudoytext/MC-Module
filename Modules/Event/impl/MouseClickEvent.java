package ytext.custom.client.event.impl;

import ytext.custom.client.event.types.Event;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;

@Getter
public class MouseClickEvent extends Event {

    public MouseClickEvent(Minecraft minecraft) {
        super(minecraft);
    }

    public int getY(){
        return Mouse.getEventY();
    }
    public int getX(){
        return Mouse.getEventX();
    }

    public boolean isLeftClick(){
        return Mouse.getEventButton() == 0 && Mouse.getEventButtonState();
    }

    public boolean isRightClick(){
        return Mouse.getEventButton() == 1;
    }

    public boolean isMiddleClick(){
        return Mouse.getEventButton() == 2  ;
    }
}
