/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.frontend.pages.templaterecord

import zakadabar.stack.frontend.builtin.table.ZkTable
import zakadabar.template.data.TemplateRecordDto
import zakadabar.template.resources.Strings

class Table : ZkTable<TemplateRecordDto>() {

    override fun onConfigure() {
        title = Strings.templateRecords
        crud = TemplateRecords

        add = true
        search = true
        export = true

        + TemplateRecordDto::id
        + TemplateRecordDto::name

        + actions()
    }

}