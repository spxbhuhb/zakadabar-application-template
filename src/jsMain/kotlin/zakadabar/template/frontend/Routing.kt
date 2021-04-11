/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package zakadabar.template.frontend

import zakadabar.stack.frontend.application.ZkAppRouting
import zakadabar.template.frontend.pages.Home
import zakadabar.template.frontend.pages.exampleRecord.ExampleRecords

object Routing : ZkAppRouting(DefaultLayout, Home) {

    init {
        + Home
        + ExampleRecords
    }

}