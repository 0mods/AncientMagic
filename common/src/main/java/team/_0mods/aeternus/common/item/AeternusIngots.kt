package team._0mods.aeternus.common.item

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour
import team._0mods.aeternus.common.item.base.IngotItem

enum class AeternusIngots(
    val customId: String = "",
    val autoModel: Boolean = true,
    var block: (() -> Block)? = { Block(BlockBehaviour.Properties.of()) }, // changed by registry
    var item: () -> IngotItem = { IngotItem() } // changed by registry
) {
    ADAMANTINE,
    DAEMONITE,
    DESTRYCT,
    LIMONITE,
    MITHRIL,
    VIBRATIONIS,
    ETHERIUM,
    DILITHIO;

    companion object {
        @JvmStatic
        val AeternusIngots.convertName: String get() {
            if (this.customId.isNotEmpty()) return this.customId
            var id = this.name.lowercase()
            if (!id.endsWith("_ingot", true)) id += "_ingot"
            return id
        }

        val AeternusIngots.convertBlockName: String get() {
            if (this.customId.isNotEmpty()) return this.customId
            return this.name.lowercase()
        }
    }
}