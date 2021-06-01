/*
 * @copyright@
 */
package zakadabar.template.frontend.pages

import zakadabar.stack.frontend.application.target
import zakadabar.stack.frontend.application.translate
import zakadabar.stack.frontend.builtin.crud.ZkCrudTarget
import zakadabar.stack.frontend.builtin.form.ZkForm
import zakadabar.stack.frontend.builtin.table.ZkTable
import zakadabar.template.data.ExampleEntityBo


/**
 * CRUD target for [ExampleEntityBo].
 *
 * Generated with Bender at 2021-06-01T09:28:42.141Z.
 */
class ExampleEntityCrud : ZkCrudTarget<ExampleEntityBo>() {
    init {
        companion = ExampleEntityBo.Companion
        boClass = ExampleEntityBo::class
        pageClass = ExampleEntityForm::class
        tableClass = ExampleEntityTable::class
    }
}

/**
 * Form for [ExampleEntityBo].
 *
 * Generated with Bender at 2021-06-01T09:28:42.141Z.
 */
class ExampleEntityForm : ZkForm<ExampleEntityBo>() {
    override fun onCreate() {
        super.onCreate()

        build(translate<ExampleEntityForm>()) {
            + section {
                + bo::name
            }
        }
    }
}

/**
 * Table for [ExampleEntityBo].
 *
 * Generated with Bender at 2021-06-01T09:28:42.141Z.
 */
class ExampleEntityTable : ZkTable<ExampleEntityBo>() {

    override fun onConfigure() {

        crud = target<ExampleEntityCrud>()

        titleText = translate<ExampleEntityTable>()

        add = true
        search = true
        export = true

        + ExampleEntityBo::name

        + actions()
    }
}