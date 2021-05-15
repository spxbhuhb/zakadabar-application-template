/*
 * @copyright@
 */

package zakadabar.template.frontend

import hu.simplexion.rf.leltar.frontend.pages.roles.Roles
import zakadabar.stack.frontend.application.ZkAppRouting
import zakadabar.stack.frontend.builtin.pages.account.accounts.Accounts
import zakadabar.stack.frontend.builtin.pages.account.login.Login
import zakadabar.stack.frontend.builtin.pages.resources.locales.Locales
import zakadabar.stack.frontend.builtin.pages.resources.settings.Settings
import zakadabar.stack.frontend.builtin.pages.resources.translations.Translations
import zakadabar.template.frontend.pages.Home
import zakadabar.template.frontend.pages.exampleRecord.ExampleRecords

class Routing : ZkAppRouting(DefaultLayout, Home) {

    init {
        + Home
        + ExampleRecords

        + Locales
        + Translations
        + Accounts
        + Roles
        + Settings
        + Login
    }

}