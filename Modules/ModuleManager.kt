package ytext.custom.client.modules

import io.github.classgraph.ClassGraph
import io.github.classgraph.ClassInfo
import ytext.custom.client.UrCL
import ytext.custom.client.event.impl.KeyPressEvent
import ytext.custom.client.event.types.EventHandler
import ytext.custom.client.gui.ModMenu
import ytext.custom.client.modules.types.Module
import ytext.custom.client.modules.types.ModuleCategory
import ytext.custom.client.modules.types.ModuleInfo
import net.minecraft.client.Minecraft
import java.util.*
import java.util.function.Consumer
import java.util.stream.Stream

class ModuleManager(private val client: UrCL, private val mc: Minecraft) {
    val modules: MutableList<Module> = ArrayList()

    init {
        client.eventManager.register(this)
    }

    @EventHandler
    fun onKeyPress(e: KeyPressEvent) {
        if (e.isDown(MOD_MENU_KEY)) mc.displayGuiScreen(ModMenu(UrCL, 20, 20))
    }

    /**
     * Registers the specified module.
     *
     * @param module: given module to register.
     */
    fun addModule(module: Class<out Module>) {
        val info = module.getAnnotation(
            ModuleInfo::class.java
        )
        val mod = module.getConstructor().newInstance()
        mod.name = info.name
        mod.description = info.description
        mod.minecraft = mc
        mod.client = client
        mod.author = info.author
        mod.version = info.version
        mod.type = info.type
        this.addModule(mod)
    }

    fun addModule(mod: Module) {
        if (!modules.contains(mod)) modules.add(mod)
        mod.enabled = true
        client.eventManager!!.register(mod)
        mod.onEnable()
    }


    /**
     * Unregisters the specified module
     *
     * @param module: given module to unregister
     */
    fun removeModule(module: Module) {
        module.onDisable()
        client.eventManager!!.unregister(module)
        module.enabled = false
    }


    /**
     * Gets the module by name
     *
     * @param name: module name
     * @return The module if exists or null
     */
    fun getModule(name: String): Optional<Module> {
        return modules.stream().filter { m: Module -> m.name == name }.findFirst()
    }

    /**
     * returns list of modules sorted by the specified category
     *
     * @param category: the modules category
     * @return stream of modules that contain the same category
     */
    fun getModule(category: ModuleCategory): Stream<Module> {
        return modules.stream().filter { m: Module -> m.type.equals(category) }
    }


    /**
     * Loads all classes that implement Module class in the code.
     */
    fun loadInitModules() {
        ClassGraph().enableClassInfo().enableAnnotationInfo().scan().use { result ->
            result
                .getClassesWithAnnotation(ModuleInfo::class.java)
                .forEach(Consumer { clazz: ClassInfo -> this.addModule(clazz.loadClass() as Class<out Module>) })
        }
    }

    fun enableModules() {
        modules.filter { module: Module -> module.enabled }.forEach(client.eventManager!!::register)
    }

    fun disableModules() {
        modules.forEach(client.eventManager!!::unregister)
    }

    fun enableModuleSilently(mod: Module) {
        mod.enabled = true;
        mod.onEnable()
    }

    operator fun plusAssign(mod: Module) {
        this.addModule(mod)
    }

    operator fun remAssign(mod: Module) {
        this.removeModule(mod)
    }

    operator fun get(name: String): Module? {
        return modules.firstOrNull { m: Module -> m.name.equals(name, true) }
    }

    operator fun contains(name: String): Boolean {
        return modules.any { m: Module -> m.name.equals(name, true) }
    }

    companion object {
        private const val MOD_MENU_KEY = 'm'
    }
}
