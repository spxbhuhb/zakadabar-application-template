/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package zakadabar.template.backend.templaterecord

import io.ktor.features.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import zakadabar.stack.backend.data.DtoBackend
import zakadabar.stack.util.Executor
import zakadabar.template.dto.TemplateRecordDto

object TemplateRecordBackend : DtoBackend<TemplateRecordDto>() {

    override val dtoClass = TemplateRecordDto::class

    override fun init() {
        transaction {
            SchemaUtils.createMissingTablesAndColumns(
                TemplateRecordTable
            )
        }
    }

    override fun install(route: Route) {
        route.crud()
    }

    override fun all(executor: Executor) = transaction {
        TemplateRecordTable
            .selectAll()
            .map(TemplateRecordTable::toDto)
    }

    override fun create(executor: Executor, dto: TemplateRecordDto) = transaction {

        // TODO authorization

        val dao = TemplateRecordDao.new {
            templateField1 = dto.templateField1
            templateField2 = dto.templateField2
        }

        dao.toDto()
    }


    override fun read(executor: Executor, id: Long): TemplateRecordDto = transaction {

        // TODO Authorization

        TemplateRecordDao.find { TemplateRecordTable.id eq id }.firstOrNull()?.toDto() ?: throw NotFoundException()

    }

    override fun update(executor: Executor, dto: TemplateRecordDto) = transaction {

        // TODO authorization

        val dao = TemplateRecordDao[dto.id]

        dao.templateField1 = dto.templateField1
        dao.templateField2 = dto.templateField2

        dao.toDto()
    }

}