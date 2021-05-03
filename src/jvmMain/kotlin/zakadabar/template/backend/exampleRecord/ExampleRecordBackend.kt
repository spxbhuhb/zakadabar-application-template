/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
@file:Suppress("UNUSED_PARAMETER", "unused")

package zakadabar.template.backend.exampleRecord

import io.ktor.routing.*
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import zakadabar.stack.backend.authorize
import zakadabar.stack.backend.data.get
import zakadabar.stack.backend.data.record.RecordBackend
import zakadabar.stack.data.record.RecordId
import zakadabar.stack.util.Executor
import zakadabar.template.data.ExampleRecordDto

object ExampleRecordBackend : RecordBackend<ExampleRecordDto>() {

    override val dtoClass = ExampleRecordDto::class

    override fun onModuleLoad() {
        + ExampleRecordTable
    }

    override fun onInstallRoutes(route: Route) {
        route.crud()
    }

    override fun all(executor: Executor) = transaction {

        authorize(true)

        ExampleRecordTable
            .selectAll()
            .map(ExampleRecordTable::toDto)
    }

    override fun create(executor: Executor, dto: ExampleRecordDto) = transaction {

        authorize(true)

        ExampleRecordDao.new {
            name = dto.name
        }.toDto()
    }

    override fun read(executor: Executor, recordId: RecordId<ExampleRecordDto>) = transaction {

        authorize(true)

        ExampleRecordDao[recordId].toDto()
    }

    override fun update(executor: Executor, dto: ExampleRecordDto) = transaction {

        authorize(true)

        val dao = ExampleRecordDao[dto.id]
        dao.name = dto.name
        dao.toDto()
    }

    override fun delete(executor: Executor, recordId: RecordId<ExampleRecordDto>) = transaction {

        authorize(true)

        ExampleRecordDao[recordId].delete()
    }
}