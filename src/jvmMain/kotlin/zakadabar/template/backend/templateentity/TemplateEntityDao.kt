/*
 * Copyright © 2020, Simplexion, Hungary. All rights reserved.
 *
 * This source code contains proprietary information; it is provided under a license agreement
 * containing restrictions on use and distribution and are also protected by copyright, patent, and
 * other intellectual and industrial property laws.
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
        entityDto = null, // do not include entity by default
        name = entityDao.name,
        templateField1 = templateField1,
        templateField2 = templateField2
    )

}