/*
 * Copyright © 2020, Simplexion, Hungary. All rights reserved.
 *
 * This source code contains proprietary information; it is provided under a license agreement
 * containing restrictions on use and distribution and are also protected by copyright, patent, and
 * other intellectual and industrial property laws.
 */

package zakadabar.template.backend.templaterecord

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import zakadabar.template.dto.TemplateRecordDto

class TemplateRecordDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TemplateRecordDao>(TemplateRecordTable)

    var templateField1 by TemplateRecordTable.templateField1
    var templateField2 by TemplateRecordTable.templateField2

    fun toDto() = TemplateRecordDto(
        id = id.value,
        templateField1 = templateField1,
        templateField2 = templateField2
    )

}