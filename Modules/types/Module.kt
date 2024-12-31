package ytext.custom.client.modules.types

import ytext.custom.client.UrCL
import net.minecraft.client.Minecraft
import java.util.*


/**
 * The base form of a Module.
 */
open class Module {

    var name = ""
    var description = ""
    var version = ""
    var author = ""
    var type = ModuleCategory.MISC
    var settings: ArrayList<ModuleSetting<*>> = ArrayList()
    var minecraft: Minecraft = Minecraft.getMinecraft()
    var client: UrCL = UrCL
    var enabled: Boolean = false

    /**
     * Calls when module is enabled
     */
    open fun onEnable() {

    }

    /**
     * Calls when module is disabled
     */
    open fun onDisable() {
    }


    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other == null || other.toString() != this.toString()) return false
        val that = other as Module
        return this.name == that.name &&
                this.description == that.description &&
                this.version == that.version &&
                this.type == that.type &&
                this.settings == that.settings &&
                this.minecraft == that.minecraft
    }

    override fun hashCode(): Int {
        return Objects.hash(name, description, version, type, settings, minecraft, client)
    }

    override fun toString(): String {

        return "Module[" +
                "name=" + name + ", " +
                "description=" + description + ", " +
                "version=" + version + ", " +
                "type=" + type + ", " +
                "settings=" + settings + ", " +
                "mc=" + minecraft + ", " +
                "client=" + client + ']'
    }

}
