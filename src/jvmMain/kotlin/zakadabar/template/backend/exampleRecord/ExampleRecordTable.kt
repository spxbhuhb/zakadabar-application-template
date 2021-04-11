/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.backend.exampleRecord

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ResultRow
import zakadabar.template.data.ExampleRecordDto


object ExampleRecordTable : LongIdTable("example_records") {

    val name = varchar("name", 100)

    fun toDto(row: ResultRow) = ExampleRecordDto(
        id = row[id].value,
        name = row[name]
    )

}