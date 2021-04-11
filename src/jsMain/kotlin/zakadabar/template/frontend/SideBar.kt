/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.frontend

import zakadabar.stack.frontend.builtin.sidebar.ZkSideBar
import zakadabar.template.frontend.pages.Home
import zakadabar.template.frontend.pages.exampleRecord.ExampleRecords
import zakadabar.template.resources.Strings

object SideBar : ZkSideBar() {

    init {
        style {
            height = "100%"
        }
    }

    override fun onCreate() {
        super.onCreate()

        + title(Strings.applicationName, SideBar::hideMenu) { Home.open() }

        + item(Strings.exampleRecords) { ExampleRecords.openAll() }

    }

    private fun hideMenu() {

    }

}



