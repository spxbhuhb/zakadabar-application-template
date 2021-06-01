/*
 * @copyright@
 */

package zakadabar.template.frontend

import zakadabar.stack.frontend.application.ZkAppRouting
import zakadabar.template.frontend.pages.ExampleEntityCrud
import zakadabar.template.frontend.pages.Home

class Routing : ZkAppRouting(DefaultLayout, Home) {

    init {
        zakadabar.lib.accounts.frontend.install(this)
        zakadabar.lib.i18n.frontend.install(this)
        + Home
        + ExampleEntityCrud()
   }

}