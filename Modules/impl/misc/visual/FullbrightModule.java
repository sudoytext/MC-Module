package ytext.custom.client.modules.impl.misc.visual;

import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import lombok.Getter;

@Getter
@ModuleInfo(name = "Fullbright", description = "Changes the Gamma of the game to a given value.", type = ModuleCategory.VISUAL)
public class FullbrightModule extends Module {

    private float oldGamma;
    @Getter
    private float brightness = 1.0f;


    @Override
    public void onEnable() {
        super.onEnable();
        oldGamma = getMinecraft().gameSettings.gammaSetting;
        getMinecraft().gameSettings.gammaSetting = brightness;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getMinecraft().gameSettings.gammaSetting = oldGamma;
    }

    @EventHandler
    public void onRender2D(Render2DEvent e) {
        getMinecraft().gameSettings.gammaSetting = brightness;
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
        getMinecraft().gameSettings.gammaSetting = brightness;
    }
}

