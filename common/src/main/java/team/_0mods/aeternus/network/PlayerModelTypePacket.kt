package team._0mods.aeternus.network

import kotlinx.serialization.Serializable
import net.minecraft.world.entity.player.Player
import ru.hollowhorizon.hc.common.network.HollowPacketV2
import ru.hollowhorizon.hc.common.network.HollowPacketV3
import team._0mods.aeternus.api.client.PlayerModelType

@Serializable
@HollowPacketV2(HollowPacketV2.Direction.TO_SERVER)
class PlayerModelTypePacket(val modelType: PlayerModelType): HollowPacketV3<PlayerModelTypePacket> {
    companion object {
        @JvmField
        val models = mutableMapOf<Player, PlayerModelType>()
    }

    override fun handle(player: Player) {
        models[player] = modelType
    }
}