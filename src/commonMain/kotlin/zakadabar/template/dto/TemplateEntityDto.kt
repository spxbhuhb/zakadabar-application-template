/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.dto

import kotlinx.serialization.Serializable
import zakadabar.stack.data.entity.DtoWithEntityCompanion
import zakadabar.stack.data.entity.EntityDto
import zakadabar.stack.data.schema.DtoSchema
import zakadabar.stack.extend.DtoWithEntityContract
import zakadabar.template.Template

@Serializable
data class TemplateEntityDto(

    override val id: Long,
    override val entityDto: EntityDto?,

    val name: String,
    val templateField1: String,
    val templateField2: String,

    ) : DtoWithEntityContract<TemplateEntityDto> {

    companion object : DtoWithEntityCompanion<TemplateEntityDto>() {
        override val type = "${Template.shid}/template-entity"
    }

    override fun schema() = DtoSchema.build {
        + ::name min 1 max 100 // this comes from stack entity name limit
        + ::templateField1 min 1 max 100
        + ::templateField2 min 2 max 50
    }

    override fun getType() = type

    override fun comm() = comm

}