package team._0mods.aeternus.common.item

import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.component.CustomData
import team._0mods.aeternus.common.ModName
import team._0mods.aeternus.common.helper.AeternusItem
import java.util.*

class KnowledgeBook: AeternusItem({
    component(DataComponents.CUSTOM_DATA, CustomData.of(CompoundTag()))
}) {
    private val playerUUIDItem = "${ModName}PlayerCheckUUID"

    var playerUUID: UUID
        get() {
            val tag = components().get(DataComponents.CUSTOM_DATA)!!.copyTag()
            return tag.getUUID(playerUUIDItem)
        }

        set(value) {
            components().get(DataComponents.CUSTOM_DATA)!!.unsafe.putUUID(playerUUIDItem, value)
        }
}
