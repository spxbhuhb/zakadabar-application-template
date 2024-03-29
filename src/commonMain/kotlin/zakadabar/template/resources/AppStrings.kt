/*
 * @copyright@
 */
@file:Suppress("unused") // auto binding makes this inspection useless

package zakadabar.template.resources

import zakadabar.core.resource.ZkBuiltinStrings

// This pattern makes it possible to switch the strings easily. Demo can work as
// a standalone application, but it is possible to use it as a component library.
// In that case - or when you write an actual component library - you want to your
// strings to be customizable.

internal var strings = AppStrings()

class AppStrings : ZkBuiltinStrings() {
    override val applicationName by "template"
    val exampleEntityCrud by "Example Entities"

    // these will most likely change a bit with the first release of July 2021

    override val accountName by "Account Name"
    override val email by "E-mail"
    override val phone by "Phone"
    override val locked by "Locked"
    override val lastLoginSuccess by "Last Login Success"
    override val lastLoginFail by "Last Login Fail"
    val description by "Description"
    override val loginFailCount by "Login Fail Count"
}