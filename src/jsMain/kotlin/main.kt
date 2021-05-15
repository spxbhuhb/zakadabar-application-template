/*
 * @copyright@
 */
@file:Suppress("unused") // main is called by webpack

import zakadabar.stack.frontend.application.ZkApplication
import zakadabar.stack.frontend.application.application
import zakadabar.stack.frontend.builtin.ZkElement
import zakadabar.stack.frontend.builtin.theme.ZkBuiltinLightTheme
import zakadabar.stack.frontend.resources.css.ZkCssStyleSheet
import zakadabar.stack.frontend.resources.initTheme
import zakadabar.stack.frontend.util.io
import zakadabar.template.frontend.Routing
import zakadabar.template.resources.AppStrings

fun main() {

    ZkElement.addKClass = false
    ZkCssStyleSheet.shortNames = true

    application = ZkApplication()

    io {

        with(application) {

            initSession()

            initTheme(ZkBuiltinLightTheme(), ZkBuiltinLightTheme())

            initLocale(AppStrings())

            initRouting(Routing())

            run()

        }

    }

}