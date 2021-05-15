/*
 * @copyright@
 */
package zakadabar.template.frontend.pages.exampleRecord

import zakadabar.stack.frontend.builtin.table.ZkTable
import zakadabar.template.data.ExampleRecordDto
import zakadabar.template.resources.strings

class Table : ZkTable<ExampleRecordDto>() {

    override fun onConfigure() {
        titleText = strings.exampleRecords
        crud = ExampleRecords

        add = true
        search = true
        export = true

        + ExampleRecordDto::id
        + ExampleRecordDto::name

        + actions()
    }

}