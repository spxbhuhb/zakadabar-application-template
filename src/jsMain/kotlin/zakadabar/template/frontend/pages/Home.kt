/*
 * @copyright@
 */
package zakadabar.template.frontend.pages

import zakadabar.stack.frontend.builtin.pages.ZkPage
import zakadabar.stack.frontend.util.plusAssign
import zakadabar.template.frontend.resources.appStyles

object Home : ZkPage() {

    override fun onCreate() {
        classList += appStyles.home

        + "This is the home page of your application."
    }

}