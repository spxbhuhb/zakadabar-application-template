/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.frontend

import zakadabar.stack.frontend.resources.ZkTheme
import zakadabar.stack.frontend.resources.css.ZkCssStyleSheet

object AppStyles : ZkCssStyleSheet<ZkTheme>() {

    val page by cssClass {
        overflowY = "scroll"
        padding = 20
    }

    init {
        attach()
    }
}