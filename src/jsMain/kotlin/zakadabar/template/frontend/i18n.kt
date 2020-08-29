/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.template.frontend

import zakadabar.stack.frontend.FrontendContext.t
import zakadabar.stack.frontend.util.Dictionary
import zakadabar.template.Template
import zakadabar.template.dto.TemplateEntityDto

fun tTemplate(text: String) = t(text, Template.uuid)

val translations: Dictionary = mutableMapOf(
    "hu" to mutableMapOf(
        TemplateEntityDto.type to "Template",
    ),
    "en" to mutableMapOf(
        TemplateEntityDto.type to "Template",
    )
)
