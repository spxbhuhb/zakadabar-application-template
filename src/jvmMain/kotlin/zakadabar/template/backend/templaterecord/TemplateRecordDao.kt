/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.backend.templaterecord

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import zakadabar.template.data.TemplateRecordDto

class TemplateRecordDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TemplateRecordDao>(TemplateRecordTable)

    var name by TemplateRecordTable.name

    fun toDto() = TemplateRecordDto(
        id = id.value,
        name = name
    )
}