/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
@file:Suppress("UNUSED_PARAMETER", "unused")

package zakadabar.template.backend.templaterecord

import io.ktor.routing.*
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import zakadabar.stack.backend.authorize
import zakadabar.stack.backend.data.record.RecordBackend
import zakadabar.stack.util.Executor
import zakadabar.template.data.TemplateRecordDto

object TemplateRecordBackend : RecordBackend<TemplateRecordDto>() {

    override val dtoClass = TemplateRecordDto::class

    override fun onModuleLoad() {
        + TemplateRecordTable
    }

    override fun onInstallRoutes(route: Route) {
        route.crud()
    }

    override fun all(executor: Executor) = transaction {

        authorize(true)

        TemplateRecordTable
            .selectAll()
            .map(TemplateRecordTable::toDto)
    }

    override fun create(executor: Executor, dto: TemplateRecordDto) = transaction {

        authorize(true)

        TemplateRecordDao.new {
            name = dto.name
        }.toDto()
    }

    override fun read(executor: Executor, recordId: Long) = transaction {

        authorize(true)

        TemplateRecordDao[recordId].toDto()
    }

    override fun update(executor: Executor, dto: TemplateRecordDto) = transaction {

        authorize(true)

        val dao = TemplateRecordDao[dto.id]
        dao.name = dto.name
        dao.toDto()
    }

    override fun delete(executor: Executor, recordId: Long) = transaction {

        authorize(true)

        TemplateRecordDao[recordId].delete()
    }
}