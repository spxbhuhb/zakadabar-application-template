/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
@file:Suppress("unused") // main is called by webpack

import zakadabar.stack.frontend.application.ZkApplication
import zakadabar.stack.frontend.application.application
import zakadabar.stack.frontend.builtin.theme.ZkBuiltinDarkTheme
import zakadabar.stack.frontend.builtin.theme.ZkBuiltinLightTheme
import zakadabar.stack.frontend.builtin.theme.ZkGreenBlueTheme
import zakadabar.stack.frontend.resources.initTheme
import zakadabar.stack.frontend.util.io
import zakadabar.template.frontend.Routing
import zakadabar.template.resources.strings

fun main() {

    application = ZkApplication()

    zakadabar.lib.accounts.frontend.install(application)
    zakadabar.lib.i18n.frontend.install(application)

    io {

        with(application) {

            initSession()

            initTheme(ZkGreenBlueTheme(), ZkBuiltinLightTheme(), ZkBuiltinDarkTheme())

            initLocale(strings)

            initRouting(Routing())

            run()

        }

    }

}