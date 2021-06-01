/*
 * @copyright@
 */

package zakadabar.template.backend

import zakadabar.stack.backend.BackendModule
import zakadabar.stack.backend.server
import zakadabar.stack.util.PublicApi
import zakadabar.template.backend.exampleEntity.ExampleEntityBl

@PublicApi
object Module : BackendModule {

    override fun onModuleLoad() {
        zakadabar.lib.accounts.backend.install()
        zakadabar.lib.i18n.backend.install()

        server += ExampleEntityBl()
    }

}