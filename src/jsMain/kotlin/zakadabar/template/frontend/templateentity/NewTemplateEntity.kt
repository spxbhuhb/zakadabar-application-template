/*
 * Copyright © 2020, Simplexion, Hungary
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */


package zakadabar.template.frontend.templateentity

import zakadabar.stack.data.entity.EntityDto
import zakadabar.stack.frontend.FrontendContext
import zakadabar.stack.frontend.builtin.desktop.messages.EntityChildrenLoaded
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

    override suspend fun create(parentDto: EntityDto?, name: String) {

        val parentId = parentDto?.id

        val entityDto = TemplateEntityDto(
            id = 0,
            entityDto = EntityDto.new(parentId, TemplateEntityDto.type, name),
            name = "name",
            templateField1 = "value1",
            templateField2 = "value2"
        )

        launch {
            entityDto.create()
            FrontendContext.dispatcher.postSync { EntityChildrenLoaded(parentId) }
        }

    }

}
