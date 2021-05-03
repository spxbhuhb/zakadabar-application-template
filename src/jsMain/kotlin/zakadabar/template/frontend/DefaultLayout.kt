/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.frontend

import zakadabar.stack.frontend.builtin.layout.ZkDefaultLayout
import zakadabar.stack.frontend.builtin.theme.ZkBuiltinDarkTheme
import zakadabar.stack.frontend.builtin.titlebar.ZkAppHandle
import zakadabar.stack.frontend.builtin.titlebar.ZkAppTitleBar
import zakadabar.stack.frontend.builtin.titlebar.actions.DarkLightMode
import zakadabar.template.frontend.pages.Home
import zakadabar.template.resources.Strings

object DefaultLayout : ZkDefaultLayout() {

    override fun onCreate() {
        super.onCreate()

        appHandle = ZkAppHandle(zke { + Strings.applicationName }, onIconClick = ::onToggleSideBar, onTextClick = { Home.open() })
        sideBar = SideBar
        titleBar = ZkAppTitleBar(::onToggleSideBar)

        titleBar.globalElements += DarkLightMode(ZkBuiltinDarkTheme.NAME, ZkBuiltinDarkTheme.NAME)

    }

}