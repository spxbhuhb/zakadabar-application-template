/*
 * @copyright@
 */
package zakadabar.template.backend.exampleEntity

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import zakadabar.stack.backend.exposed.ExposedPaBase
import zakadabar.stack.backend.exposed.ExposedPaTable
import zakadabar.stack.backend.exposed.entityId
import zakadabar.template.data.ExampleEntityBo


/**
 * Exposed based Persistence API for ExampleEntityBo.
 *
 * Generated with Bender at 2021-06-01T09:28:42.142Z.
 *
 * **IMPORTANT** Please do not modify this class manually, see extending patterns below.
 *
 * - If you need other fields, add them to the business object and then re-generate.
 * - If you need other functions, please extend with `Gen` removed from the name.
 */
open class ExampleEntityExposedPaGen : ExposedPaBase<ExampleEntityBo, ExampleEntityExposedTableGen>(
    table = ExampleEntityExposedTableGen
) {
    override fun ResultRow.toBo() = ExampleEntityBo(
        id = this[table.id].entityId(),
        name = this[table.name]
    )

    override fun UpdateBuilder<*>.fromBo(bo: ExampleEntityBo) {
        this[table.name] = bo.name
    }
}

/**
 * Exposed based SQL table for ExampleEntityBo.
 *
 * Generated with Bender at 2021-06-01T09:28:42.142Z.
 *
 * **IMPORTANT** Please do not modify this class manually.
 *
 * If you need other fields, add them to the business object and then re-generate.
 */
object ExampleEntityExposedTableGen : ExposedPaTable<ExampleEntityBo>(
    tableName = "example_entity"
) {

    internal val name = varchar("name", 100)

}