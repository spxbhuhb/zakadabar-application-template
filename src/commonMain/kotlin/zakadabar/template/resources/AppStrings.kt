/*
 * @copyright@
 */
@file:Suppress("unused") // auto binding makes this inspection useless

package zakadabar.template.resources

import zakadabar.stack.resources.ZkBuiltinStrings

internal var strings = AppStrings()

class AppStrings : ZkBuiltinStrings() {
    override val applicationName by "template"
    val exampleRecords by "exampleRecords"
}