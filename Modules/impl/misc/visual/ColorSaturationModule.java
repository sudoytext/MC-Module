package ytext.custom.client.modules.impl.misc.visual;

import ytext.custom.client.event.impl.RenderWorldEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import ytext.custom.client.modules.settings.Setting;
import ytext.custom.client.modules.types.ModuleSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Field;
import java.util.List;

@ModuleInfo(name = "Color Saturation", description = "Adjusts the color saturation of the game", type = ModuleCategory.VISUAL)
public class ColorSaturationModule extends Module {

    @Setting
    public ModuleSetting<Float> saturationLevel = new ModuleSetting<>("Saturation Level", new ResourceLocation(""), 1.0f);

    private final Minecraft mc = Minecraft.getMinecraft();
    private ShaderGroup shaderGroup;
    private Framebuffer framebuffer;
    private boolean shadersInitialized = false;

    @EventHandler
    public void onRenderWorld(RenderWorldEvent e) {
        if (mc.theWorld != null && mc.getFramebuffer() != null) {
            if (!shadersInitialized) {
                initShaders();
            }
            applyColorSaturation();
        }
    }

    private void initShaders() {
        try {
            shaderGroup = new ShaderGroup(mc.getTextureManager(), mc.getResourceManager(), mc.getFramebuffer(), new ResourceLocation("shaders/post/saturation.json"));
            shaderGroup.createBindFramebuffers(mc.displayWidth, mc.displayHeight);
            framebuffer = shaderGroup.getFramebufferRaw("final");
            shadersInitialized = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void applyColorSaturation() {
        try {
            if (shaderGroup != null) {
                List<Shader> shaders = getShadersList(shaderGroup);
                for (Shader shader : shaders) {
                    shader.getShaderManager().getShaderUniform("Saturation").set(saturationLevel.getValue());
                }
                framebuffer.framebufferClear();
                shaderGroup.loadShaderGroup(mc.getTimer().renderPartialTicks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (shadersInitialized && shaderGroup != null) {
            shaderGroup.deleteShaderGroup();
            shadersInitialized = false;
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
        shadersInitialized = false;
    }

    @SuppressWarnings("unchecked")
    private List<Shader> getShadersList(ShaderGroup shaderGroup) {
        try {
            Field listShadersField = ShaderGroup.class.getDeclaredField("listShaders");
            listShadersField.setAccessible(true);
            return (List<Shader>) listShadersField.get(shaderGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
