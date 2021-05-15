/*
 * @copyright@
 */
package zakadabar.template.frontend.pages.exampleRecord

import zakadabar.stack.frontend.builtin.form.ZkForm
import zakadabar.template.data.ExampleRecordDto
import zakadabar.template.resources.strings

class Form : ZkForm<ExampleRecordDto>() {

    override fun onCreate() {

        build(dto.name, strings.exampleRecords) {
            + section(strings.basics) {
                + dto::id
                + dto::name
            }
        }
    }

}