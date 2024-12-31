package ytext.custom.client.event.impl;

import ytext.custom.client.event.types.Event;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class KeyPressEvent extends Event {
    public KeyPressEvent(Minecraft minecraft) {
        super(minecraft);
    }

    public int getKey(){
        return Keyboard.getEventKey();
    }

    public char getKeyChar(){
        return Keyboard.getEventCharacter();
    }
    public boolean isDown(char key){
        return getKeyChar() == key && Keyboard.getEventKeyState();
    }
}
