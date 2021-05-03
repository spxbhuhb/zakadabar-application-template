/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.frontend

import hu.simplexion.rf.leltar.frontend.pages.roles.Roles
import kotlinx.browser.window
import zakadabar.stack.StackRoles
import zakadabar.stack.data.builtin.account.LogoutAction
import zakadabar.stack.frontend.application.ZkApplication
import zakadabar.stack.frontend.builtin.pages.account.accounts.Accounts
import zakadabar.stack.frontend.builtin.pages.account.login.Login
import zakadabar.stack.frontend.builtin.pages.resources.locales.Locales
import zakadabar.stack.frontend.builtin.pages.resources.settings.Settings
import zakadabar.stack.frontend.builtin.pages.resources.translations.Translations
import zakadabar.stack.frontend.builtin.sidebar.ZkSideBar
import zakadabar.stack.frontend.util.io
import zakadabar.template.frontend.pages.exampleRecord.ExampleRecords
import zakadabar.template.resources.Strings

object SideBar : ZkSideBar() {

    override fun onCreate() {
        super.onCreate()

        + item(Strings.exampleRecords) { ExampleRecords.openAll() }

        withOneOfRoles(StackRoles.securityOfficer, StackRoles.siteAdmin) {

            + group(Strings.administration) {

                + item(Strings.settings) { Settings.openAll() }

                withRole(StackRoles.siteAdmin) {
                    + item(Strings.locales) { Locales.openAll() }
                    + item(Strings.translations) { Translations.openAll() }
                }

                withRole(StackRoles.securityOfficer) {
                    + item(Strings.accounts) { Accounts.openAll() }
                    + item(Strings.roles) { Roles.openAll() }
                }

            }
        }

        ifAnonymous {
            + item(Strings.login) { Login.open() }
        }

        ifNotAnonymous {
            + item(Strings.account) { Accounts.openUpdate(ZkApplication.executor.account.id) }
            + item(Strings.logout) {
                io {
                    LogoutAction().execute()
                    window.location.href = "/"
                }
            }
        }
    }

}



