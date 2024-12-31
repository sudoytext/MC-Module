package ytext.custom.client.modules.impl.misc.movement;


import ytext.custom.client.event.impl.PlayerMoveEvent;
import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.event.impl.SprintEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;


@ModuleInfo(name = "ToggleSprint", description = "Toggles the sprint mode", type = ModuleCategory.MOVEMENT)
public class ToggleSprintModule extends Module {
    private boolean sprinting;

    @EventHandler
    public void onPlayerMove(SprintEvent event) {
        KeyBinding binding = getMinecraft().gameSettings.keyBindSprint;
        if (binding.isKeyDown() && binding.getPressTime() < 2) {
            sprinting = !sprinting;
        }
        event.setToggle(sprinting);
    }

    @EventHandler
    public void onRender2D(Render2DEvent event) {
        event.drawItem("Toggle Sprint");
    }


    public void onEnable() {
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getMinecraft().thePlayer.setSprinting(false);
        getMinecraft().gameSettings.keyBindSprint.isPressed();

    }

}
