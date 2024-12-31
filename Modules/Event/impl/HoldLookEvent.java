package ytext.custom.client.event.impl;

import ytext.custom.client.event.types.Event;
import ytext.custom.client.modules.impl.misc.visual.holdlook.HoldLookKeys;
import net.minecraft.client.Minecraft;


public class HoldLookEvent extends Event{

    public static boolean rearCamHasChecked = false;
    public static boolean frontCamHasChecked = false;


    public void onClientTick() {
        Minecraft mc = Minecraft.getMinecraft();

        if (HoldLookKeys.keyFrontcam.isKeyDown()) {
            frontCamHasChecked = false;
            mc.gameSettings.thirdPersonView = 2;
        } else if (!frontCamHasChecked) {
            frontCamHasChecked = true;
            mc.gameSettings.thirdPersonView = 0;
        }

        if (HoldLookKeys.keyRearcam.isKeyDown()) {
            rearCamHasChecked = false;
            mc.gameSettings.thirdPersonView = 1;
        } else if (!rearCamHasChecked) {
            rearCamHasChecked = true;
            mc.gameSettings.thirdPersonView = 0;
        }

        if (HoldLookKeys.keyFrontToggle.isPressed()) {
            if (mc.gameSettings.thirdPersonView == 0 || mc.gameSettings.thirdPersonView == 1) {
                mc.gameSettings.thirdPersonView = 2;
            } else {
                mc.gameSettings.thirdPersonView = 0;
            }
        }

        if (HoldLookKeys.keyRearToggle.isPressed()) {
            if (mc.gameSettings.thirdPersonView == 0 || mc.gameSettings.thirdPersonView == 2) {
                mc.gameSettings.thirdPersonView = 1;
            } else {
                mc.gameSettings.thirdPersonView = 0;
            }
        }
    }
}