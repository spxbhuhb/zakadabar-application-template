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
import zakadabar.stack.frontend.elements.ComplexElement
import zakadabar.stack.frontend.extend.ScopedViewContract
import zakadabar.stack.frontend.util.launch
import zakadabar.stack.util.PublicApi
import zakadabar.stack.util.UUID
import zakadabar.template.dto.TemplateEntityDto

@PublicApi
class TemplateEntityReadView(private val dto: EntityDto) : ComplexElement() {

    companion object : ScopedViewContract() {

        override val uuid = UUID("98d90262-fd3d-4cd8-9858-e65bd82cd11e")

        override fun newInstance(scope: Any?) = TemplateEntityReadView(scope as EntityDto)

    }

    private lateinit var templateEntityDto: TemplateEntityDto

    override fun init(): ComplexElement {
        super.init()

        launch {
            templateEntityDto = TemplateEntityDto.comm.get(dto.id)
            this.innerHTML = templateEntityDto.templateField1 + " " + templateEntityDto.templateField2
        }

        return this
    }

}