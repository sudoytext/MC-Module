package ytext.custom.client.modules.impl.misc.visual;

import ytext.custom.client.event.impl.ParticleRenderEvent;
import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import net.minecraft.util.EnumParticleTypes;

import java.util.ArrayList;
import java.util.List;


@ModuleInfo(name = "No Particles", description = "Removes particles", type = ModuleCategory.VISUAL)
public class NoParticleModule extends Module {
    public List<EnumParticleTypes> allowedParticles = new ArrayList<>();

    @EventHandler
    public void onRender2D(Render2DEvent e) {
        e.drawItem("No/Less Particles");
    }

    @EventHandler
    public void onParticleRendering(ParticleRenderEvent e) {
        e.cancelled = !allowedParticles.contains(e.type);
    }
}
