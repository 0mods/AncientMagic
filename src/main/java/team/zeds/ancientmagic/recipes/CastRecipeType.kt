package team.zeds.ancientmagic.recipes

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeType
import team.zeds.ancientmagic.api.mod.AMConstant

@JvmRecord
data class CastRecipeType<T: Recipe<*>>(val recipeName: String) : RecipeType<T> {
    override fun toString(): String {
        return ResourceLocation(AMConstant.KEY, recipeName).toString()
    }
}