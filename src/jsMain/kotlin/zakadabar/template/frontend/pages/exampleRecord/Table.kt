/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.frontend.pages.exampleRecord

import zakadabar.stack.frontend.builtin.table.ZkTable
import zakadabar.template.data.ExampleRecordDto
import zakadabar.template.resources.Strings

class Table : ZkTable<ExampleRecordDto>() {

    override fun onConfigure() {
        titleText = Strings.exampleRecords
        crud = ExampleRecords

        add = true
        search = true
        export = true

        + ExampleRecordDto::id
        + ExampleRecordDto::name

        + actions()
    }

}