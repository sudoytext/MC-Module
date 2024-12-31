package ytext.custom.client.modules.impl.misc.visual.holdlook;

import ytext.custom.client.UrCL;
import ytext.custom.client.event.impl.HoldLookEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;


@ModuleInfo(name = "HoldLook", description = "No Idea..", type = ModuleCategory.VISUAL)
public class HoldLook {

    public static HoldLookKeys HoldLookKeys = new HoldLookKeys();

    @EventHandler
    public void preInit() {
        HoldLookKeys.init();
        UrCL.INSTANCE.getEventManager().register(new HoldLookEvent());
    }

}
