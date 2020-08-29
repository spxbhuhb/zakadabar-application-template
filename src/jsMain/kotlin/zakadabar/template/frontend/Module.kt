/*
 * Copyright © 2020, Simplexion, Hungary. All rights reserved.
 *
 * This source code contains proprietary information; it is provided under a license agreement
 * containing restrictions on use and distribution and are also protected by copyright, patent, and
 * other intellectual and industrial property laws.
 */

package zakadabar.template.frontend

import zakadabar.stack.frontend.FrontendContext
import zakadabar.stack.frontend.builtin.icon.Icons
import zakadabar.stack.frontend.comm.rest.RecordRestComm
import zakadabar.stack.frontend.extend.FrontendEntitySupport
import zakadabar.stack.frontend.extend.FrontendModule
import zakadabar.stack.util.PublicApi
import zakadabar.template.Template
import zakadabar.template.dto.TemplateEntityDto
import zakadabar.template.dto.TemplateRecordDto
import zakadabar.template.frontend.templateentity.NewTemplateEntity
import zakadabar.template.frontend.templateentity.TemplateEntityReadView

@PublicApi
object Module : FrontendModule() {

    override val uuid = Template.uuid

    override fun init() {

        FrontendContext += uuid to translations

        FrontendContext += FrontendEntitySupport(
            Template.uuid,
            TemplateEntityDto,
            iconSource = Icons.folder,
            newView = NewTemplateEntity,
            readView = TemplateEntityReadView
        )

        TemplateRecordDto.comm = RecordRestComm(TemplateRecordDto.type, TemplateRecordDto.serializer())

    }

}

