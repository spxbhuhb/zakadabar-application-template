/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
@file:Suppress("unused") // main is called by webpack

import kotlinx.browser.window
import zakadabar.stack.data.builtin.resources.TranslationsByLocale
import zakadabar.stack.frontend.application.ZkApplication
import zakadabar.stack.frontend.builtin.ZkElement
import zakadabar.stack.frontend.builtin.theme.ZkBuiltinDarkTheme
import zakadabar.stack.frontend.builtin.theme.ZkBuiltinLightTheme
import zakadabar.stack.frontend.util.io
import zakadabar.template.frontend.Routing
import zakadabar.template.resources.Strings

fun main() {

    io {

        ZkElement.addKClass = true

        with(ZkApplication) {

            sessionManager.init()

            themes += ZkBuiltinLightTheme()
            themes += ZkBuiltinDarkTheme()

            theme = initTheme()

            val locale = executor.account.locale ?: window.navigator.language

            strings = Strings.merge(TranslationsByLocale(locale).execute())

            routing = Routing

            init()
        }

    }

}