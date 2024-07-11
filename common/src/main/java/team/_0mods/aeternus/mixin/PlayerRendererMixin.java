package team._0mods.aeternus.mixin;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team._0mods.aeternus.api.client.PlayerModelType;
import team._0mods.aeternus.network.PlayerModelTypePacket;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    void handleRenderer(EntityRendererProvider.Context context, boolean useSlimModel, CallbackInfo ci) {
        var packet = new PlayerModelTypePacket(useSlimModel ? PlayerModelType.SLIM : PlayerModelType.CLASSIC);
        packet.send();
    }
}
