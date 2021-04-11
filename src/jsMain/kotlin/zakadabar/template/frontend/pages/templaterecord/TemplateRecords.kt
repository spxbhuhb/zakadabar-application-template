/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.frontend.pages.templaterecord

import zakadabar.stack.frontend.builtin.pages.ZkCrudTarget
import zakadabar.template.data.TemplateRecordDto

object TemplateRecords : ZkCrudTarget<TemplateRecordDto>() {
    init {
        companion = TemplateRecordDto.Companion
        dtoClass = TemplateRecordDto::class
        pageClass = Form::class
        tableClass = Table::class
    }
}