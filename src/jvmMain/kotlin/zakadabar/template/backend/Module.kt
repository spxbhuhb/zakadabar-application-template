/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package zakadabar.template.backend

import zakadabar.stack.backend.BackendContext
import zakadabar.stack.backend.BackendModule
import zakadabar.stack.util.PublicApi
import zakadabar.template.backend.templaterecord.TemplateRecordBackend

@PublicApi
object Module : BackendModule {

    override fun init() {
        BackendContext += TemplateRecordBackend
    }

}