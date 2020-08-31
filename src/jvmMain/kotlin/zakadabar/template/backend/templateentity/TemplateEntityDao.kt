/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package zakadabar.template.backend.templateentity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import zakadabar.stack.backend.builtin.entities.data.EntityDao
import zakadabar.template.dto.TemplateEntityDto

class TemplateEntityDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TemplateEntityDao>(TemplateEntityTable)

    var templateField1 by TemplateEntityTable.templateField1
    var templateField2 by TemplateEntityTable.templateField2

    fun toDto(entityDao: EntityDao) = TemplateEntityDto(
        id = id.value,
        entityRecord = null, // do not include entity by default
        name = entityDao.name,
        templateField1 = templateField1,
        templateField2 = templateField2
    )

}