package ytext.custom.client.modules.impl.combat;

import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.event.impl.TargetHitEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import net.minecraft.entity.Entity;

@ModuleInfo(name = "Reach Display", description = "Displays the last hit Reach", type = ModuleCategory.COMBAT)
public class ReachDisplayModule extends Module {
    public double reach = 0;
    public int resetDelay = 5000;

    @EventHandler
    public void onRender2D(Render2DEvent e) {
        e.drawItem("Reach: " + reach);

    }

    @EventHandler
    public void onEnemyHit(TargetHitEvent e) {
        Entity entity = e.entity;
        double realDistance = entity.getDistance(getMinecraft().thePlayer.posX, entity.posY, entity.posZ);
        reach = (double) Math.round(realDistance * 10) / 10;
        resetDelay = 5000;
    }
}