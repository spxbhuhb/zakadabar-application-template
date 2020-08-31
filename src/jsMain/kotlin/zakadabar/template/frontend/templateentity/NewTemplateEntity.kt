/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */


package zakadabar.template.frontend.templateentity

import zakadabar.stack.data.entity.EntityRecordDto
import zakadabar.stack.frontend.builtin.icon.Icons
import zakadabar.stack.frontend.builtin.navigator.EntityNavigator
import zakadabar.stack.frontend.builtin.navigator.NewEntity
import zakadabar.stack.frontend.builtin.navigator.NewEntityItemWithName
import zakadabar.stack.frontend.extend.NewEntityContract
import zakadabar.stack.frontend.util.launch
import zakadabar.stack.util.UUID
import zakadabar.template.dto.TemplateEntityDto
import zakadabar.template.frontend.tTemplate

class NewTemplateEntity(newEntity: NewEntity) : NewEntityItemWithName(newEntity) {

    companion object : NewEntityContract() {

        override val uuid = UUID("048d2408-7d91-430b-a59f-7a794669dc85")

        override val name by lazy { tTemplate(TemplateEntityDto.type) }

        override val target = EntityNavigator.newEntity

        override val icon = Icons.folder.simple18

        override fun newInstance(scope: Any?) = NewTemplateEntity(scope as NewEntity)

    }

    override suspend fun create(parentDto: EntityRecordDto?, name: String) {

        val parentId = parentDto?.id

        val entityDto = TemplateEntityDto(
            id = 0,
            entityRecord = EntityRecordDto.new(parentId, TemplateEntityDto.type, name),
            name = "name",
            templateField1 = "value1",
            templateField2 = "value2"
        )

        launch {
            entityDto.create()
        }

    }

}
