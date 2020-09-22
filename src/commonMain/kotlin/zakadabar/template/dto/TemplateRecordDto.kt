/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.dto

import kotlinx.serialization.Serializable
import zakadabar.stack.data.record.RecordDto
import zakadabar.stack.data.record.RecordDtoCompanion
import zakadabar.stack.data.schema.DtoSchema
import zakadabar.template.Template

@Serializable
data class TemplateRecordDto(

    override val id: Long,

    val templateField1: String,
    val templateField2: String,

    ) : RecordDto<TemplateRecordDto> {

    companion object : RecordDtoCompanion<TemplateRecordDto>() {
        override val type = "${Template.shid}/template-record"
    }

    override fun schema() = DtoSchema.build {
        + ::templateField1 min 1 max 100
        + ::templateField2 min 2 max 50
    }

    override fun comm() = comm

    override fun getType() = type

}