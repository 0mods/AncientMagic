package team._0mods.aeternus.api.tab

import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack

lateinit var _tabCallback: (CreativeModeTab.Builder.() -> Unit) -> CreativeModeTab

object AeternusTabCreator {
    @JvmStatic
    fun create(title: Component, icon: () -> ItemStack) = create {
        this.title(title)
        this.icon(icon)
    }

    @JvmStatic
    fun create(callback: CreativeModeTab.Builder.() -> Unit): CreativeModeTab =
        _tabCallback(callback)
}
