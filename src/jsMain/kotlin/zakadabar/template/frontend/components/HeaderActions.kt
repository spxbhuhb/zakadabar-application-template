/*
 * @copyright@
 */
package zakadabar.template.frontend.components

import zakadabar.stack.frontend.builtin.ZkElement
import zakadabar.stack.frontend.builtin.theme.ZkThemeRotate
import zakadabar.stack.frontend.resources.ZkIcons
import zakadabar.template.frontend.resources.AppDarkTheme
import zakadabar.template.frontend.resources.AppLightTheme

class HeaderActions : ZkElement() {

    override fun onCreate() {
        + ZkThemeRotate(
            ZkIcons.darkMode to AppDarkTheme(),
            ZkIcons.lightMode to AppLightTheme()
        )
    }

}