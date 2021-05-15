/*
 * @copyright@
 */
package zakadabar.template.backend.exampleRecord

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ResultRow
import zakadabar.stack.backend.data.recordId
import zakadabar.template.data.ExampleRecordDto


object ExampleRecordTable : LongIdTable("example_records") {

    val name = varchar("name", 100)

    fun toDto(row: ResultRow) = ExampleRecordDto(
        id = row[id].recordId(),
        name = row[name]
    )

}