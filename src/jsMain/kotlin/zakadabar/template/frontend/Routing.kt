/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package zakadabar.template.frontend

import zakadabar.stack.frontend.application.ZkAppRouting
import zakadabar.template.frontend.pages.ExampleEntityCrud
import zakadabar.template.frontend.pages.Home

class Routing : ZkAppRouting(DefaultLayout, Home) {

    init {
        + Home
        + ExampleEntityCrud()
    }

}