package team._0mods.aeternus.api.magic.block

import net.minecraft.world.level.block.Block

interface CursedBlock {
    val drops: List<Block>

    val worksWith: List<Block>

    val condition: CursedBlockCondition
}
