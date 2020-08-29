/*
 * Copyright © 2020, Simplexion, Hungary. All rights reserved.
 *
 * This source code contains proprietary information; it is provided under a license agreement
 * containing restrictions on use and distribution and are also protected by copyright, patent, and 
 * other intellectual and industrial property laws.
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
                    entityDto = null,
                    name = it[EntityTable.name],
                    templateField1 = it[TemplateEntityTable.templateField1],
                    templateField2 = it[TemplateEntityTable.templateField2],
                )
            }
    }

    override fun create(executor: Executor, dto: TemplateEntityDto) = transaction {

        val entityDto = dto.entityDto?.requireType(TemplateEntityDto.type) ?: throw IllegalArgumentException()
        val entityDao = EntityDao.create(executor, entityDto) // performs authorization

        val dao = TemplateEntityDao.new(entityDao.id.value) {
            templateField1 = dto.templateField1
            templateField2 = dto.templateField2
        }

        dao.toDto(entityDao)
    }

    override fun update(executor: Executor, dto: TemplateEntityDto) = transaction {

        val entityDto = dto.entityDto?.requireId(dto.id) ?: throw IllegalArgumentException()
        val entityDao = EntityDao.update(executor, entityDto) // performs authorization

        val dao = TemplateEntityDao[dto.id]

        dao.templateField1 = dto.templateField1
        dao.templateField2 = dto.templateField2

        dao.toDto(entityDao)
    }

}