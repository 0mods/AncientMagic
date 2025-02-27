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

import net.minecraft.locale.Language
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.Item
import team._0mods.aeternus.api.text.TranslationBuilder

val mcEmpty: Component = Component.empty()

val String.mcText
    get() = Component.literal(this)

val String.imguiTranslate
    get() = Language.getInstance().getOrDefault(this)

fun ArmorItem.Type.generateArmorTranslateByParent(item: Item): Component {
    val itemName = item.descriptionId
    return when(this) {
        ArmorItem.Type.HELMET -> TranslationBuilder.builder(itemName, "helmet".armorPrefix, TranslationBuilder.RussianAncestralType.MALE).build
        ArmorItem.Type.CHESTPLATE -> TranslationBuilder.builder(itemName, "chest".armorPrefix, TranslationBuilder.RussianAncestralType.MALE).build
        ArmorItem.Type.LEGGINGS -> TranslationBuilder.builder(itemName, "legs".armorPrefix, TranslationBuilder.RussianAncestralType.PLURAL).build
        ArmorItem.Type.BOOTS -> TranslationBuilder.builder(itemName, "feet".armorPrefix, TranslationBuilder.RussianAncestralType.PLURAL).build
        ArmorItem.Type.BODY -> TranslationBuilder.builder(itemName, "body".armorPrefix, TranslationBuilder.RussianAncestralType.MEDIUM).build
    }
}

val String.armorPrefix: String
    get() = "armor.aeternus.$this"
