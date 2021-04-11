/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.data

import kotlinx.serialization.Serializable
import zakadabar.stack.data.record.RecordDto
import zakadabar.stack.data.record.RecordDtoCompanion
import zakadabar.stack.data.record.RecordId
import zakadabar.stack.data.schema.DtoSchema

@Serializable
data class TemplateRecordDto(

    override var id: RecordId<TemplateRecordDto>,
    var name: String

) : RecordDto<TemplateRecordDto> {

    companion object : RecordDtoCompanion<TemplateRecordDto>({
        recordType = "template"
    })

    override fun getRecordType() = recordType
    override fun comm() = comm

    override fun schema() = DtoSchema {
        + ::id
        + ::name min 1 max 100
    }

}