/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.frontend.browser

import zakadabar.softui.browser.layout.SuiDefaultLayout
import zakadabar.template.frontend.browser.components.AppHeader

object DefaultLayout : SuiDefaultLayout() {
    override fun onCreate() {
        super.onCreate()
        header = AppHeader()
        sideBar = SideBar()
    }
}
