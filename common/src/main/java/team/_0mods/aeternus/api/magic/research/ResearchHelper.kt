package team._0mods.aeternus.api.magic.research

import net.minecraft.world.entity.player.Player
import team._0mods.aeternus.service.ResearchHelper as RedirectHelper

object ResearchHelper {
    @JvmStatic
    val Player.researches: List<Research>
        get() = RedirectHelper.getResearches(this)

    @JvmStatic
    fun Player.hasResearch(research: Research) = RedirectHelper.hasResearch(this, research)

    @JvmStatic
    fun Player.hasResearches(vararg researches: Research) = RedirectHelper.hasResearches(this, *researches)

    @JvmStatic
    fun Player.addResearch(vararg researches: Research) = RedirectHelper.addResearch(this, *researches)

    fun Player.canOpen(research: Research) = RedirectHelper.canOpen(this, research)
}
