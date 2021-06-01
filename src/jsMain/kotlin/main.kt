/*
 * @copyright@
 */
@file:Suppress("unused") // main is called by webpack

import zakadabar.stack.frontend.application.ZkApplication
import zakadabar.stack.frontend.application.application
import zakadabar.stack.frontend.resources.initTheme
import zakadabar.stack.frontend.util.io
import zakadabar.template.frontend.Routing
import zakadabar.template.frontend.resources.AppDarkTheme
import zakadabar.template.frontend.resources.AppLightTheme
import zakadabar.template.resources.strings

fun main() {

    application = ZkApplication()

    zakadabar.lib.accounts.frontend.install(application)
    zakadabar.lib.i18n.frontend.install(application)

    io {

        with(application) {

            initSession()

            initTheme(AppLightTheme(), AppDarkTheme())

            initLocale(strings)

            initRouting(Routing())

            run()

        }

    }

}