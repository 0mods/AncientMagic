package team._0mods.aeternus.common.init.event

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.entity.player.PlayerRenderer
import ru.hollowhorizon.hc.client.render.shaders.RegisterHollowShadersEvent
import ru.hollowhorizon.hc.common.events.SubscribeEvent
import ru.hollowhorizon.hc.common.events.registry.RegisterKeyBindingsEvent
import ru.hollowhorizon.hc.common.events.tick.TickEvent
import team._0mods.aeternus.api.client.PlayerModelType
import team._0mods.aeternus.client.keys.AeternusKeys
import team._0mods.aeternus.client.keys.registries
import team._0mods.aeternus.client.screen.configScreen
import team._0mods.aeternus.mixin.accessor.PlayerModelAccessor
import team._0mods.aeternus.network.PlayerModelTypePacket

object AeternusClientEventsInit {
    @JvmStatic
    @SubscribeEvent(clientSideOnly = true)
    fun onButtonClick(e: TickEvent.Client) {
        while (AeternusKeys.configGUIOpen.consumeClick()) {
            Minecraft.getInstance().setScreen(configScreen())
        }
    }

    @JvmStatic
    @SubscribeEvent(clientSideOnly = true)
    fun onKeyRegister(e: RegisterKeyBindingsEvent) {
        registries.forEach(e::registerKeyMapping)
    }

    @JvmStatic
    @SubscribeEvent(clientSideOnly = true)
    fun onShaderRegister(e: RegisterHollowShadersEvent) {}

    @JvmStatic
    @SubscribeEvent(clientSideOnly = true)
    fun onClientTick(e: TickEvent.Client) {
        val mc = e.minecraft
        val player = mc.player ?: return

        val playerRender = mc.entityRenderDispatcher.getRenderer(player) as PlayerRenderer
        val playerModel = playerRender.model as PlayerModelAccessor

        if (playerModel.slim()) PlayerModelTypePacket(PlayerModelType.SLIM).send()
        else PlayerModelTypePacket(PlayerModelType.CLASSIC).send()
    }
}
