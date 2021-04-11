/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.backend.templaterecord

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ResultRow
import zakadabar.template.data.TemplateRecordDto


object TemplateRecordTable : LongIdTable("templaterecords") {

    val name = varchar("name", 100)

    fun toDto(row: ResultRow) = TemplateRecordDto(
        id = row[id].value,
        name = row[name]
    )

}