/*
 * All Rights Received
 * Copyright (c) 2024 0mods.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package team._0mods.aeternus.common.init

import team._0mods.aeternus.api.plugin.AeternusPlugin
import team._0mods.aeternus.api.plugin.AeternusPluginRegistry
import team._0mods.aeternus.api.registry.ResearchRegistry
import team._0mods.aeternus.api.registry.ResearchTriggerRegistry
import team._0mods.aeternus.common.LOGGER
import team._0mods.aeternus.common.ModId

@AeternusPluginRegistry(ModId)
class AeternusCorePlugin: AeternusPlugin {
    companion object {
        lateinit var researchRegistry: ResearchRegistry
        lateinit var triggerRegistry: ResearchTriggerRegistry
    }

    override fun registerResearch(reg: ResearchRegistry) {
        researchRegistry = reg
//        ReloadListenerRegistry.register(PackType.SERVER_DATA, ResearchReloadListener(reg))
        LOGGER.info("Research Registry is initialized")
    }

    override fun registerResearchTriggers(reg: ResearchTriggerRegistry) {
        triggerRegistry = reg
        LOGGER.info("Research Triggers Registry is initialized")
    }
}
