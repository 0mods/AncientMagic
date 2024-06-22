/*
 * All Rights Received
 * Copyright (c) 2024 0mods.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package team._0mods.aeternus.api.magic.research

import team._0mods.aeternus.api.magic.research.book.ResearchAlignment
import team._0mods.aeternus.api.magic.research.book.ResearchBookMetadata
import team._0mods.aeternus.api.magic.research.book.ResearchShape
import team._0mods.aeternus.api.impl.research.ResearchBookMetadataImpl
import team._0mods.aeternus.api.impl.research.ResearchImpl
import team._0mods.aeternus.api.impl.research.ResearchSettingsImpl
import team._0mods.aeternus.api.magic.research.trigger.ResearchTrigger
import team._0mods.aeternus.api.util.APIResourceLocation
import team._0mods.aeternus.api.util.Text
import team._0mods.aeternus.platformredirect.api.util.rl
import team._0mods.aeternus.platformredirect.api.util.toAPI

// Research Settings
fun ResearchSettings.Companion.of(vararg researchSettings: Pair<ResearchTrigger, Research>): ResearchSettings {
    val triggerList = mutableListOf<ResearchTrigger>()
    val researchList = mutableListOf<Research>()

    if (researchSettings.isNotEmpty()) {
        researchSettings.forEach {
            val research = it.second
            val trigger = it.first
            triggerList.add(trigger)
            researchList.add(research)
        }
    }

    return ResearchSettingsImpl(triggerList, researchList)
}

fun ResearchSettings.Companion.of(triggers: List<ResearchTrigger>, parents: List<Research>): ResearchSettings =
    ResearchSettingsImpl(triggers.toMutableList(), parents.toMutableList())

fun ResearchSettings.Companion.of(triggers: Array<ResearchTrigger>, parents: Array<Research>) = this.of(triggers.toList(), parents.toList())

fun ResearchSettings.Companion.of(triggers: List<ResearchTrigger>) = this.of(triggers, listOf())

// Research Metadata
fun ResearchBookMetadata.Companion.of(
    title: Text,
    desc: Text,
    icon: APIResourceLocation,
    pos: Pair<Int, Int>,
    align: ResearchAlignment,
    shape: ResearchShape
): ResearchBookMetadata = ResearchBookMetadataImpl(title, desc, icon, pos, align, shape)

fun ResearchBookMetadata.Companion.of(
    title: Text,
    desc: Text,
    icon: String,
    pos: Pair<Int, Int>,
    align: ResearchAlignment,
    shape: ResearchShape
): ResearchBookMetadata = this.of(title, desc, icon.rl.toAPI, pos, align, shape)

fun ResearchBookMetadata.Companion.of(
    title: Text,
    desc: Text,
    icon: APIResourceLocation,
    pos: Pair<Int, Int>,
    align: ResearchAlignment
): ResearchBookMetadata = this.of(title, desc, icon, pos, align, ResearchShape.SQUARE)

fun ResearchBookMetadata.Companion.of(
    title: Text,
    desc: Text,
    icon: String,
    pos: Pair<Int, Int>,
    align: ResearchAlignment
): ResearchBookMetadata = this.of(title, desc, icon.rl.toAPI, pos, align)

fun ResearchBookMetadata.Companion.of(
    title: Text,
    desc: Text,
    icon: APIResourceLocation,
    x: Int,
    y: Int,
    align: ResearchAlignment,
    shape: ResearchShape
): ResearchBookMetadata = this.of(title, desc, icon, x to y, align, shape)

fun ResearchBookMetadata.Companion.of(
    title: Text,
    desc: Text,
    icon: APIResourceLocation,
    x: Int,
    y: Int,
    align: ResearchAlignment
): ResearchBookMetadata = this.of(title, desc, icon, x, y, align, ResearchShape.SQUARE)

fun ResearchBookMetadata.Companion.of(
    title: Text,
    desc: Text,
    icon: String,
    x: Int,
    y: Int,
    align: ResearchAlignment
): ResearchBookMetadata = this.of(title, desc, icon.rl.toAPI, x, y, align)

fun ResearchBookMetadata.Companion.of(
    title: Text,
    desc: Text,
    icon: APIResourceLocation,
    align: ResearchAlignment,
    shape: ResearchShape
): ResearchBookMetadata = this.of(title, desc, icon, 0 to 0, align, shape)

fun ResearchBookMetadata.Companion.of(
    title: Text,
    desc: Text,
    icon: String,
    align: ResearchAlignment,
    shape: ResearchShape
): ResearchBookMetadata = this.of(title, desc, icon, 0 to 0, align, shape)

fun ResearchBookMetadata.Companion.of(
    title: Text,
    desc: Text,
    icon: APIResourceLocation,
    align: ResearchAlignment,
): ResearchBookMetadata = this.of(title, desc, icon, 0 to 0, align, ResearchShape.SQUARE)

fun ResearchBookMetadata.Companion.of(
    title: Text,
    desc: Text,
    icon: String,
    align: ResearchAlignment
): ResearchBookMetadata = this.of(title, desc, icon, 0 to 0, align, ResearchShape.SQUARE)

// Research
fun Research.Companion.create(settings: ResearchSettings, metadata: ResearchBookMetadata, etheriumNeed: Double = 0.0) =
    ResearchImpl(settings, metadata, etheriumNeed)
