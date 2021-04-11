/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.frontend.pages.exampleRecord

import zakadabar.stack.frontend.builtin.form.ZkForm
import zakadabar.template.data.ExampleRecordDto
import zakadabar.template.resources.Strings

class Form : ZkForm<ExampleRecordDto>() {

    override fun onCreate() {

        build(dto.name, Strings.exampleRecords) {
            + section(Strings.basics) {
                + dto::id
                + dto::name
            }
        }
    }

}