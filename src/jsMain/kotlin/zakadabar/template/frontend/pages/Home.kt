/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.frontend.pages

import zakadabar.stack.frontend.builtin.pages.ZkPage
import zakadabar.stack.frontend.util.plusAssign
import zakadabar.template.frontend.TemplateStyles

object Home : ZkPage() {

    override fun onCreate() {
        classList += TemplateStyles.page

        + "This is the home page of your application."
    }

}