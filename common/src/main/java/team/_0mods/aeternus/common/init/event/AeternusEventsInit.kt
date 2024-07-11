/*
 * All Rights Received
 * Copyright (c) 2024 0mods.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package team._0mods.aeternus.common.init.event

import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.FireBlock
import ru.hollowhorizon.hc.common.events.AnnotationProcessorEvent
import ru.hollowhorizon.hc.common.events.SubscribeEvent
import ru.hollowhorizon.hc.common.events.entity.EntityHurtEvent
import ru.hollowhorizon.hc.common.events.entity.player.PlayerInteractEvent
import ru.hollowhorizon.hc.common.events.registry.RegisterReloadListenersEvent
import ru.hollowhorizon.hc.common.events.tick.TickEvent
import team._0mods.aeternus.api.impl.registry.ResearchRegistryImpl
import team._0mods.aeternus.api.impl.registry.ResearchTriggerRegistryImpl
import team._0mods.aeternus.api.impl.registry.SpellRegistryImpl
import team._0mods.aeternus.api.magic.research.ResearchReloadListener
import team._0mods.aeternus.api.magic.research.ResearchRequired
import team._0mods.aeternus.api.plugin.AeternusPlugin
import team._0mods.aeternus.api.plugin.AeternusPluginRegistry
import team._0mods.aeternus.common.init.AeternusCorePlugin
import team._0mods.aeternus.common.init.registry.AeternusRegistry
import team._0mods.aeternus.common.item.KnowledgeBook
import team._0mods.aeternus.service.EtheriumHelper
import team._0mods.aeternus.service.ResearchHelper
import kotlin.random.Random
import kotlin.random.nextInt

object AeternusEventsInit {
    internal val itemBurn = mutableMapOf<ItemStack, ItemStack>()

    @JvmStatic
    @SubscribeEvent
    fun onPlayerInteract(e: PlayerInteractEvent.ItemInteract) {
        val hand = e.hand
        val player = e.player
        val level = player.level()

        if (hand == InteractionHand.MAIN_HAND) {
            val stack = player.getItemInHand(hand)

            if (stack.`is`(Items.BOOK)) {
                val stackOfKNBook = ItemStack(AeternusRegistry.knowledgeBook.get())
                val stackAsItem = stackOfKNBook.item as KnowledgeBook
                if (!player.inventory.contains(stackOfKNBook)) {
                    val etheriumCount = EtheriumHelper.getCountForPlayer(player)
                    if (etheriumCount >= 35) {
                        player.getItemInHand(hand).shrink(1)
                        stackAsItem.playerUUID = player.uuid
                        val droppedItem = ItemEntity(level, player.x, player.y, player.z, stackOfKNBook)
                        droppedItem.setNoPickUpDelay()
                        level.addFreshEntity(droppedItem)
                        EtheriumHelper.consume(player, Random.nextInt(1..35))
                    }
                }
            }
        }
    }

    @JvmStatic
    @SubscribeEvent
    fun onItemHurt(e: EntityHurtEvent) {
        val entity = e.entity
        val level = entity.level()
        if (level.isClientSide) return

        if (entity is ItemEntity) {
            if (!entity.isAlive && entity.type == EntityType.ITEM && entity.isOnFire) {
                if (itemBurn.isEmpty()) return

                itemBurn.entries.forEach {
                    val ingredient = it.key
                    val result = it.value

                    if (ingredient.`is`(entity.item.item)) {
                        val ent = ItemEntity(level, entity.x, entity.y + 1, entity.z, result).apply {
                            isInvulnerable = true
                            setNoPickUpDelay()
                        }

                        level.addFreshEntity(ent)
                    }

                    val block = level.getBlockState(entity.blockPosition()).block
                    if (block is FireBlock || block == Blocks.LAVA) {
                        level.setBlock(entity.blockPosition(), Blocks.AIR.defaultBlockState(), 1)
                    }
                }
            }
        }
    }

    @JvmStatic
    @SubscribeEvent
    fun onPlayerResearchCheck(e: TickEvent.Entity) {
        val player = e.entity
        if (player is Player) {
            val stack = player.getItemInHand(InteractionHand.MAIN_HAND)
            val item = stack.item

            if (item is ResearchRequired) {
                val req = item.requirements

                if (!ResearchHelper.hasResearches(player, *req.toTypedArray()))
                    item.lockItem = true
            }
        }
    }

    @JvmStatic
    @SubscribeEvent
    fun onRegisterReloadListener(e: RegisterReloadListenersEvent.Server) {
        e.register(ResearchReloadListener(AeternusCorePlugin.researchRegistry))
    }

    @JvmStatic
    @SubscribeEvent
    fun onAnnotationProcess(e: AnnotationProcessorEvent) {
        e.registerClassHandler<AeternusPluginRegistry> { clazz, reg ->
            if (AeternusPlugin::class.java.isAssignableFrom(clazz)) {
                val modId = reg.modId
                val plugin = clazz.getDeclaredConstructor().newInstance() as AeternusPlugin

                plugin.registerResearch(ResearchRegistryImpl(modId))
                plugin.registerResearchTriggers(ResearchTriggerRegistryImpl(modId))
                plugin.registerSpells(SpellRegistryImpl(modId))
            }
        }
    }
}
