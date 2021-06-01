/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.frontend.components

import zakadabar.stack.frontend.builtin.ZkElement
import zakadabar.stack.frontend.builtin.theme.ZkBuiltinDarkTheme
import zakadabar.stack.frontend.builtin.theme.ZkBuiltinLightTheme
import zakadabar.stack.frontend.builtin.theme.ZkGreenBlueTheme
import zakadabar.stack.frontend.builtin.theme.ZkThemeRotate
import zakadabar.stack.frontend.resources.ZkIcons

class HeaderActions : ZkElement() {

    override fun onCreate() {
        + ZkThemeRotate(
            ZkIcons.darkMode to ZkBuiltinDarkTheme(),
            ZkIcons.lightMode to ZkBuiltinLightTheme(),
            ZkIcons.leaf to ZkGreenBlueTheme()
        )
    }

}