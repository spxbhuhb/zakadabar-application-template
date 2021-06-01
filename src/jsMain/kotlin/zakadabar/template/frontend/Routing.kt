/*
 * @copyright@
 */

package zakadabar.template.frontend

import zakadabar.stack.frontend.application.ZkAppRouting
import zakadabar.template.frontend.pages.ExampleEntityCrud
import zakadabar.template.frontend.pages.Home

class Routing : ZkAppRouting(DefaultLayout, Home) {

    init {
        + Home
        + ExampleEntityCrud()
    }

}