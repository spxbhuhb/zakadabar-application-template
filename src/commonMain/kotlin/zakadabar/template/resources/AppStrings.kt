/*
 * @copyright@
 */
@file:Suppress("unused") // auto binding makes this inspection useless

package zakadabar.template.resources

import zakadabar.stack.resources.ZkBuiltinStrings

// This pattern makes it possible to switch the strings easily. Demo can work as
// a standalone application, but it is possible to use it as a component library.
// In that case - or when you write an actual component library - you want to your
// strings to be customizable.

internal var strings = AppStrings()

class AppStrings : ZkBuiltinStrings() {
    override val applicationName by "template"
    val exampleEntityCrud by "Example Entities"
}