/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package zakadabar.template.backend.templateentity

import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import zakadabar.stack.backend.builtin.entities.data.EntityDao
import zakadabar.stack.backend.builtin.entities.data.EntityTable
import zakadabar.stack.backend.extend.EntityRestBackend
import zakadabar.stack.util.Executor
import zakadabar.template.dto.TemplateEntityDto

object TemplateEntityBackend : EntityRestBackend<TemplateEntityDto> {

    override fun query(executor: Executor, id: Long?, parentId: Long?): List<TemplateEntityDto> = transaction {

        val condition = if (id == null) {
            EntityTable.parent eq parentId
        } else {
            TemplateEntityTable.id eq id
        }

        TemplateEntityTable
            .join(EntityTable, JoinType.INNER) {
                EntityTable.id eq TemplateEntityTable.id
            }
            .slice(
                EntityTable.id,
                EntityTable.acl,
                EntityTable.parent,
                EntityTable.name,
                EntityTable.status,
                TemplateEntityTable.templateField1,
                TemplateEntityTable.templateField2
            )
            .select(condition)
            .filter { EntityTable.readableBy(executor, it) }
            .map {
                TemplateEntityDto(
                    id = it[EntityTable.id].value,
                    entityRecord = null,
                    name = it[EntityTable.name],
                    templateField1 = it[TemplateEntityTable.templateField1],
                    templateField2 = it[TemplateEntityTable.templateField2],
                )
            }
    }

    override fun create(executor: Executor, dto: TemplateEntityDto) = transaction {

        val entityDto = dto.entityRecord?.requireType(TemplateEntityDto.type) ?: throw IllegalArgumentException()
        val entityDao = EntityDao.create(executor, entityDto) // performs authorization

        val dao = TemplateEntityDao.new(entityDao.id.value) {
            templateField1 = dto.templateField1
            templateField2 = dto.templateField2
        }

        dao.toDto(entityDao)
    }

    override fun update(executor: Executor, dto: TemplateEntityDto) = transaction {

        val entityDto = dto.entityRecord?.requireId(dto.id) ?: throw IllegalArgumentException()
        val entityDao = EntityDao.update(executor, entityDto) // performs authorization

        val dao = TemplateEntityDao[dto.id]

        dao.templateField1 = dto.templateField1
        dao.templateField2 = dto.templateField2

        dao.toDto(entityDao)
    }

}