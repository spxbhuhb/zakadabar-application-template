/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.frontend

import kotlinx.browser.window
import zakadabar.lib.accounts.data.LogoutAction
import zakadabar.lib.accounts.frontend.accounts.Account
import zakadabar.lib.accounts.frontend.accounts.Accounts
import zakadabar.lib.accounts.frontend.login.Login
import zakadabar.lib.accounts.frontend.roles.Roles
import zakadabar.lib.i18n.frontend.Locales
import zakadabar.lib.i18n.frontend.Translations
import zakadabar.stack.StackRoles
import zakadabar.stack.frontend.application.translate
import zakadabar.stack.frontend.builtin.sidebar.ZkSideBar
import zakadabar.stack.frontend.util.io
import zakadabar.template.frontend.pages.ExampleEntityCrud
import zakadabar.template.resources.strings

object SideBar : ZkSideBar() {

    override fun onCreate() {
        super.onCreate()

        + item<ExampleEntityCrud>()

        ifAnonymous {
            + item<Login>()
        }

        withRole(StackRoles.securityOfficer) {
            + group(translate<Accounts>()) {
                + item<Accounts>()
                + item<Roles>()
            }
            + group(translate<Translations>()) {
                + item<Locales>()
                + item<Translations>()
            }
        }

        ifNotAnonymous {
            + item<Account>()
            + item(strings.logout) {
                io {
                    LogoutAction().execute()
                    window.location.href = "/"
                }
            }
        }
    }

}



