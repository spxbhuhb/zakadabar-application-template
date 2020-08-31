/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */


package zakadabar.template.frontend.templateentity

import zakadabar.stack.data.entity.EntityRecordDto
import zakadabar.stack.frontend.elements.ComplexElement
import zakadabar.stack.frontend.extend.ScopedViewContract
import zakadabar.stack.frontend.util.launch
import zakadabar.stack.util.PublicApi
import zakadabar.stack.util.UUID
import zakadabar.template.dto.TemplateEntityDto

@PublicApi
class TemplateEntityReadView(private val dto: EntityRecordDto) : ComplexElement() {

    companion object : ScopedViewContract() {

        override val uuid = UUID("98d90262-fd3d-4cd8-9858-e65bd82cd11e")

        override fun newInstance(scope: Any?) = TemplateEntityReadView(scope as EntityRecordDto)

    }

    private lateinit var templateEntityDto: TemplateEntityDto

    override fun init(): ComplexElement {
        super.init()

        launch {
            templateEntityDto = TemplateEntityDto.comm.get(dto.id)
            this.innerHTML = templateEntityDto.templateField1 + " " + templateEntityDto.templateField2
        }

        return this
    }

}