package ytext.custom.client.modules.types

/**
 * Flags the module information to the Module
 * @see Module , ModuleCategory
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ModuleInfo(
    /**
     * The module name.
     * @return name of the module
     */
    val name: String,
    /**
     * The module description. (Optional)
     * @return description of the module.
     */
    val description: String = "",
    /**
     * The original author of the module. (Optional)
     * @return the authors name or MonixTeam by default
     */
    val author: String = "customTeam",
    /**
     * The current version of module. (Optional)
     * @return the modules version or 1.0 by default
     */
    val version: String = "1.0",
    /**
     * The type/category of the module through the ModuleType class.
     * @return category of the module or misc by default
     * @see ModuleCategory
     */
    val type: ModuleCategory = ModuleCategory.MISC
)
