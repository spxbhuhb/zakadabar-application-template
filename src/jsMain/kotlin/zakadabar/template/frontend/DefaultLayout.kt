/*
 * @copyright@
 */
package zakadabar.template.frontend

import zakadabar.stack.frontend.builtin.layout.ZkDefaultLayout
import zakadabar.stack.frontend.builtin.titlebar.ZkAppHandle
import zakadabar.stack.frontend.builtin.titlebar.ZkAppTitleBar
import zakadabar.template.frontend.components.HeaderActions
import zakadabar.template.frontend.pages.Home
import zakadabar.template.resources.strings

object DefaultLayout : ZkDefaultLayout() {

    override fun onCreate() {
        super.onCreate()

        appHandle = ZkAppHandle(zke { + strings.applicationName }, onIconClick = ::onToggleSideBar, target = Home)
        sideBar = SideBar()
        titleBar = ZkAppTitleBar(::onToggleSideBar)

        titleBar.globalElements += HeaderActions()

    }

}