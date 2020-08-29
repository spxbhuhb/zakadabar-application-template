/*
 * Copyright © 2020, Simplexion, Hungary. All rights reserved.
 *
 * This source code contains proprietary information; it is provided under a license agreement
 * containing restrictions on use and distribution and are also protected by copyright, patent, and
 * other intellectual and industrial property laws.
 */

package zakadabar.template.backend

import io.ktor.routing.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import zakadabar.stack.backend.extend.BackendModule
import zakadabar.stack.backend.extend.entityRestApi
import zakadabar.stack.backend.extend.recordRestApi
import zakadabar.stack.util.PublicApi
import zakadabar.template.Template
import zakadabar.template.backend.templateentity.TemplateEntityBackend
import zakadabar.template.backend.templateentity.TemplateEntityTable
import zakadabar.template.backend.templaterecord.TemplateRecordBackend
import zakadabar.template.backend.templaterecord.TemplateRecordTable
import zakadabar.template.dto.TemplateEntityDto
import zakadabar.template.dto.TemplateRecordDto

@PublicApi
object Module : BackendModule() {

    override val uuid = Template.uuid

    override fun install(route: Route) {
        entityRestApi(route, TemplateEntityBackend, TemplateEntityDto::class, TemplateEntityDto.type)
        recordRestApi(route, TemplateRecordBackend, TemplateRecordDto::class, TemplateRecordDto.type)
    }

    override fun init() {
        transaction {
            SchemaUtils.createMissingTablesAndColumns(
                TemplateEntityTable,
                TemplateRecordTable
            )
        }
    }
}