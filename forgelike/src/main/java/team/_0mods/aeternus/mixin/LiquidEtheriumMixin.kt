/*
 * All Rights Received
 * Copyright (c) 2024 0mods.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package team._0mods.aeternus.mixin

import net.minecraft.world.item.Rarity
import net.minecraft.world.level.pathfinder.PathType
import net.minecraftforge.common.extensions.IForgeFluid
import net.minecraftforge.fluids.FluidType
import org.spongepowered.asm.mixin.Mixin
import team._0mods.aeternus.common.fluid.LiquidEtherium

@Mixin(LiquidEtherium::class)
class LiquidEtheriumMixin: IForgeFluid {
    override fun getFluidType(): FluidType = FluidType(
        FluidType.Properties.create()
            .canSwim(true)
            .lightLevel(1)
            .rarity(Rarity.EPIC)
            .canHydrate(true)
            .canConvertToSource(false)
            .pathType(PathType.WATER)
            .lightLevel(8)
            .canPushEntity(true)
            .canDrown(true)
            .adjacentPathType(PathType.WATER)
            .density(10)
            .canExtinguish(true)
    )
}
