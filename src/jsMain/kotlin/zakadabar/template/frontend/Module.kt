/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package zakadabar.template.frontend

import zakadabar.stack.frontend.FrontendContext
import zakadabar.stack.frontend.comm.rest.FrontendComm
import zakadabar.stack.frontend.extend.FrontendModule
import zakadabar.stack.util.PublicApi
import zakadabar.template.Template
import zakadabar.template.dto.TemplateRecordDto

@PublicApi
object Module : FrontendModule() {

    override val uuid = Template.uuid

    override fun init() {

        FrontendContext += uuid to translations

        TemplateRecordDto.comm = FrontendComm(TemplateRecordDto.type, TemplateRecordDto.serializer())

    }

}

