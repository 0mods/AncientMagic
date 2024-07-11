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
import ru.hollowhorizon.hc.client.utils.get

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
        val cap = this[AnimatedEntityCapability::class.java].apply {

        }

        return cap
    }
