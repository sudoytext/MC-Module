package ytext.custom.client.modules.impl.misc.visual;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import org.lwjgl.input.Mouse;

public class FreelookModule {
    private final Minecraft mc = Minecraft.getMinecraft();
    private boolean freeLookEnabled = false;

    public void onTick() {
        if (freeLookEnabled) {
            adjustCamera();
        }
    }

    public void onEnable() {
        freeLookEnabled = true;
    }

    public void onDisable() {
        freeLookEnabled = false;
    }

    private void adjustCamera() {
        if (Mouse.isButtonDown(1)) {
            float mouseX = Mouse.getDX() * 0.1F;
            float mouseY = Mouse.getDY() * 0.1F;

            EntityPlayerSP player = mc.thePlayer;
            if (player != null) {
                player.rotationYaw += mouseX;
                player.rotationPitch -= mouseY;


                if (player.rotationPitch > 90.0F) player.rotationPitch = 90.0F;
                if (player.rotationPitch < -90.0F) player.rotationPitch = -90.0F;
            }
        }
    }
}
