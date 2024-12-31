package ytext.custom.client.modules.impl.misc.visual.holdlook;

import net.minecraft.client.settings.KeyBinding;

public class HoldLookKeys // Uses LWJGL Keycodes
{
    public static KeyBinding keyRearcam = new KeyBinding("Hold Over-Sholuder View", 0, "Toggle Look");
    public static KeyBinding keyFrontcam = new KeyBinding("Hold Face View", 0, "Toggle Look"); // 56 L-Alt
    public static KeyBinding keyRearToggle = new KeyBinding("Toggle Over-Shoulder View", 0, "Toggle Look");
    public static KeyBinding keyFrontToggle = new KeyBinding("Toggle Face View", 0, "Toggle Look");

    public void init() {
//        ClientRegistry.registerKeyBinding(keyRearcam); // Over-Shoulder Hold
//        ClientRegistry.registerKeyBinding(keyFrontcam); // Face
//        ClientRegistry.registerKeyBinding(keyRearToggle); // Over-Shoulder Toggle
//        ClientRegistry.registerKeyBinding(keyFrontToggle); // Over-Shoulder Toggle
    }
}