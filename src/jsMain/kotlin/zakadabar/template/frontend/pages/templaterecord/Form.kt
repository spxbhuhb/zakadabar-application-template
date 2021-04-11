/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.frontend.pages.templaterecord

import zakadabar.stack.frontend.builtin.form.ZkForm
import zakadabar.template.data.TemplateRecordDto
import zakadabar.template.resources.Strings

class Form : ZkForm<TemplateRecordDto>() {

    override fun onCreate() {

        build(dto.name, Strings.templateRecords) {
            + section(Strings.basics) {
                + dto::id
                + dto::name
            }
        }
    }

}