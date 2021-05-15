/*
 * @copyright@
 */
package zakadabar.template.frontend

import hu.simplexion.rf.leltar.frontend.pages.roles.Roles
import kotlinx.browser.window
import zakadabar.stack.StackRoles
import zakadabar.stack.data.builtin.account.LogoutAction
import zakadabar.stack.frontend.application.executor
import zakadabar.stack.frontend.builtin.pages.account.accounts.Accounts
import zakadabar.stack.frontend.builtin.pages.account.login.Login
import zakadabar.stack.frontend.builtin.pages.resources.locales.Locales
import zakadabar.stack.frontend.builtin.pages.resources.settings.Settings
import zakadabar.stack.frontend.builtin.pages.resources.translations.Translations
import zakadabar.stack.frontend.builtin.sidebar.ZkSideBar
import zakadabar.stack.frontend.util.io
import zakadabar.template.frontend.pages.exampleRecord.ExampleRecords
import zakadabar.template.resources.strings

object SideBar : ZkSideBar() {

    override fun onCreate() {
        super.onCreate()

        + item(ExampleRecords)

        withOneOfRoles(StackRoles.securityOfficer, StackRoles.siteAdmin) {

            + group(strings.administration) {

                + item(Settings)

                withRole(StackRoles.siteAdmin) {
                    + item(Locales)
                    + item(Translations)
                }

                withRole(StackRoles.securityOfficer) {
                    + item(Accounts)
                    + item(Roles)
                }

            }
        }

        ifAnonymous {
            + item(Login)
        }

        ifNotAnonymous {
            + item(strings.account) { Accounts.openUpdate(executor.account.id) }
            + item(strings.logout) {
                io {
                    LogoutAction().execute()
                    window.location.href = "/"
                }
            }
        }
    }

}



