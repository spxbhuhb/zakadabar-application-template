/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package zakadabar.template.backend.templaterecord

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ResultRow
import zakadabar.template.Template
import zakadabar.template.dto.TemplateRecordDto

object TemplateRecordTable : LongIdTable("t_${Template.shid}_records") {

    val templateField1 = varchar("template_field_1", length = 100)
    val templateField2 = varchar("template_field_2", length = 50)

    fun toDto(row: ResultRow) = TemplateRecordDto(
        id = row[id].value,
        templateField1 = row[templateField1],
        templateField2 = row[templateField2]
    )

}