package ytext.custom.client.event.impl;

import ytext.custom.client.event.types.Event;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class WorldTimeChangeEvent extends Event {
    public long time;
    public World world;

    public WorldTimeChangeEvent(World world, long time, Minecraft mc) {
        super(mc);
    }
}
