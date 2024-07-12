/*
 * All Rights Received
 * Copyright (c) 2024 0mods.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package team._0mods.aeternus.api.util

import net.minecraft.world.entity.player.Player
import ru.hollowhorizon.hc.client.models.gltf.manager.AnimatedEntityCapability
import ru.hollowhorizon.hc.client.utils.SkinDownloader
import ru.hollowhorizon.hc.client.utils.get
import team._0mods.aeternus.api.client.PlayerModelType
import team._0mods.aeternus.network.PlayerModelTypePacket

val Player.isMoving: Boolean
    get() = this.deltaMovement.x != 0.0 || this.deltaMovement.z != 0.0

val Player.isJumping: Boolean
    get() = this.deltaMovement.y > 0.0

val Player.isFalling: Boolean
    get() = this.deltaMovement.y < 0.0

val Player.resetModel: Unit
    get() {
        this[AnimatedEntityCapability::class.java].model = "%NO_MODEL%"
    }

val Player.aeternusModel: AnimatedEntityCapability
    get() {
        val currentModel = PlayerModelTypePacket.models[this] ?: throw NullPointerException("Player's (${this.displayName}) model type is not loaded or packet was forged.")

        val cap = this[AnimatedEntityCapability::class.java].apply {
            model = if (currentModel == PlayerModelType.SLIM) "models/entity/player_slim.gltf".aeternus
            else "models/entity/player_fat.gltf".aeternus
            textures["player_texture"] = SkinDownloader.downloadSkin(this@aeternusModel.displayName!!.string).path
        }

        return cap
    }
