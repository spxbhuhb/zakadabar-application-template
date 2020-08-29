/*
 * Copyright © 2020, Simplexion, Hungary. All rights reserved.
 *
 * This source code contains proprietary information; it is provided under a license agreement
 * containing restrictions on use and distribution and are also protected by copyright, patent, and
 * other intellectual and industrial property laws.
 */

package zakadabar.template.backend.templaterecord

import org.jetbrains.exposed.dao.id.LongIdTable
import zakadabar.template.Template

object TemplateRecordTable : LongIdTable("t_${Template.shid}_records") {

    val templateField1 = varchar("template_field_1", length = 100)
    val templateField2 = varchar("template_field_2", length = 50)

}