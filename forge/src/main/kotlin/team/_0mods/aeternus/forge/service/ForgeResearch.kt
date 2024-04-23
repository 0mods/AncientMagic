/*
 * All Rights Received
 * Copyright (c) 2024.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package team._0mods.aeternus.forge.service

import net.minecraft.world.entity.player.Player
import team._0mods.aeternus.api.magic.research.Research
import team._0mods.aeternus.api.magic.research.player.PlayerResearch
import team._0mods.aeternus.forge.init.capability.AFCapabilities
import team._0mods.aeternus.service.core.ResearchHelper

class ForgeResearch: ResearchHelper {
    private val Player.researchCap: PlayerResearch get() =
        this.getCapability(AFCapabilities.playerResearch).orElseThrow(::NullPointerException)

    override fun getResearches(player: Player): List<Research> =
        player.researchCap.researches

    override fun hasResearch(player: Player, research: Research): Boolean =
        player.researchCap.hasResearch(research)

    override fun addResearch(player: Player, vararg research: Research) =
        player.researchCap.addResearch(*research)
}
