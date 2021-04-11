/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.frontend.pages.exampleRecord

import zakadabar.stack.frontend.builtin.pages.ZkCrudTarget
import zakadabar.template.data.ExampleRecordDto

object ExampleRecords : ZkCrudTarget<ExampleRecordDto>() {
    init {
        companion = ExampleRecordDto.Companion
        dtoClass = ExampleRecordDto::class
        pageClass = Form::class
        tableClass = Table::class
    }
}