package team._0mods.aeternus.common.item

import team._0mods.aeternus.common.item.base.IngotItem

enum class AeternusIngots(
    val customId: String = "",
    val generateBlock: Boolean = true,
    val autoModel: Boolean = true,
    var item: () -> IngotItem = { IngotItem() } // changes on registry
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