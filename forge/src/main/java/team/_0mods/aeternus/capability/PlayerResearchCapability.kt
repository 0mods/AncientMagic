/*
 * All Rights Received
 * Copyright (c) 2024 0mods.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package team._0mods.aeternus.capability

import net.minecraft.core.Direction
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.StringTag
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.minecraftforge.common.util.LazyOptional
import ru.hollowhorizon.hc.client.utils.rl
import team._0mods.aeternus.api.emptyLazyOpt
import team._0mods.aeternus.api.lazyOptOf
import team._0mods.aeternus.api.magic.research.Research
import team._0mods.aeternus.api.magic.research.player.PlayerResearch
import team._0mods.aeternus.common.init.AeternusCorePlugin

class PlayerResearchCapability: PlayerResearch {
    private val resReg = AeternusCorePlugin.researchRegistry

    private val researchList: MutableList<Research> = mutableListOf()

    override val researches: List<Research>
        get() = this.researchList.toList() // Copy from a list

    override fun addResearch(vararg research: Research): Boolean {
        researchList.addAll(research)
        return true
    }

    fun save(): ListTag {
        val tag = ListTag()

        this.researches.forEach {
            val research = resReg.getId(it)
            tag.add(StringTag.valueOf(research.toString()))
        }

        return tag
    }

    fun load(tag: ListTag?) {
        if (tag != null) {
            for (i in 0 ..< tag.size) {
                val founded = tag.getString(i)

                if (!researches.stream().noneMatch { resReg.getId(it) == founded.rl }) {
                    val foundedResearch = resReg.getById(founded.rl) ?: continue
                    this.addResearch(foundedResearch)
                } else continue
            }
        }
    }

    class Provider: ICapabilitySerializable<ListTag> {
        private var cap: PlayerResearchCapability? = null
        private val lazy = lazyOptOf(this::createCap)

        private fun createCap(): PlayerResearchCapability {
            if (this.cap == null)
                this.cap = PlayerResearchCapability()

            return this.cap!!
        }

        override fun <T : Any?> getCapability(p0: Capability<T>, p1: Direction?): LazyOptional<T> {
            if (p0 == AFCapabilities.playerResearch)
                return lazy.cast()

            return emptyLazyOpt()
        }

        override fun serializeNBT(arg: HolderLookup.Provider?): ListTag = serializeNBT()

        override fun deserializeNBT(arg: HolderLookup.Provider?, arg2: ListTag?) {
            deserializeNBT(arg2)
        }

        fun serializeNBT(): ListTag = createCap().save()

        fun deserializeNBT(p0: ListTag?) = createCap().load(p0)
    }
}
