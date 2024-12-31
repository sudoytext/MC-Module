package ytext.custom.client.modules.types

import net.minecraft.util.ResourceLocation

data class ModuleSetting<T>(
    val name: String,
    val icon: ResourceLocation,
    val value: T
)
