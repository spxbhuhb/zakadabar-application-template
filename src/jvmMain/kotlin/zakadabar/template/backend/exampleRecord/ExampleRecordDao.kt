/*
 * @copyright@
 */
package zakadabar.template.backend.exampleRecord

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import zakadabar.stack.backend.data.recordId
import zakadabar.template.data.ExampleRecordDto

class ExampleRecordDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ExampleRecordDao>(ExampleRecordTable)

    var name by ExampleRecordTable.name

    fun toDto() = ExampleRecordDto(
        id = id.recordId(),
        name = name
    )
}