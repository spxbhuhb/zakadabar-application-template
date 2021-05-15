/*
 * @copyright@
 */
package zakadabar.template.data

import kotlinx.serialization.Serializable
import zakadabar.stack.data.record.RecordDto
import zakadabar.stack.data.record.RecordDtoCompanion
import zakadabar.stack.data.record.RecordId
import zakadabar.stack.data.schema.DtoSchema

@Serializable
data class ExampleRecordDto(

    override var id: RecordId<ExampleRecordDto>,
    var name: String

) : RecordDto<ExampleRecordDto> {

    companion object : RecordDtoCompanion<ExampleRecordDto>("template")

    override fun getDtoNamespace() = dtoNamespace
    override fun comm() = comm

    override fun schema() = DtoSchema {
        + ::id
        + ::name min 1 max 100
    }

}