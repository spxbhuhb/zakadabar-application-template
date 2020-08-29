/*
 * Copyright © 2020, Simplexion, Hungary. All rights reserved.
 *
 * This source code contains proprietary information; it is provided under a license agreement
 * containing restrictions on use and distribution and are also protected by copyright, patent, and 
 * other intellectual and industrial property laws.
 */

package zakadabar.template.backend.templaterecord

import org.jetbrains.exposed.sql.transactions.transaction
import zakadabar.stack.backend.extend.RecordRestBackend
import zakadabar.stack.util.Executor
import zakadabar.template.dto.TemplateRecordDto

object TemplateRecordBackend : RecordRestBackend<TemplateRecordDto> {

    override fun query(executor: Executor, id: Long?): List<TemplateRecordDto> = transaction {

        if (id == null) {
            TemplateRecordDao.find { TemplateRecordTable.id eq id }
        } else {
            TemplateRecordDao.all()
        }
            .filter { false /* TODO Authorization */ }
            .map { it.toDto() }

    }

    override fun create(executor: Executor, dto: TemplateRecordDto) = transaction {

        // TODO authorization

        val dao = TemplateRecordDao.new {
            templateField1 = dto.templateField1
            templateField2 = dto.templateField2
        }

        dao.toDto()
    }

    override fun update(executor: Executor, dto: TemplateRecordDto) = transaction {

        // TODO authorization

        val dao = TemplateRecordDao[dto.id]

        dao.templateField1 = dto.templateField1
        dao.templateField2 = dto.templateField2

        dao.toDto()
    }

}