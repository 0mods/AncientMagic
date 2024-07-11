/*
 * All Rights Received
 * Copyright (c) 2024 0mods.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package team._0mods.aeternus

import net.minecraft.world.item.CreativeModeTab
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import team._0mods.aeternus.api.tab._tabCallback
import team._0mods.aeternus.common.ModId
import team._0mods.aeternus.common.clientInit
import team._0mods.aeternus.common.commonInit

@Mod(ModId)
class AeternusNeo(bus: IEventBus) {
    init {
        commonInit()
        val creativeTab = CreativeModeTab.builder()
        _tabCallback = {
            creativeTab.apply(it)
            creativeTab.build()
        }
        bus.addListener(this::initClient)
    }

    private fun initClient(e: FMLClientSetupEvent) {
        clientInit()
    }
}
