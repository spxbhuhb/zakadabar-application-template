/*
 * @copyright@
 */
package zakadabar.template.frontend.resources

import zakadabar.stack.frontend.resources.css.ZkCssStyleSheet
import zakadabar.stack.frontend.resources.css.cssStyleSheet

val appStyles by cssStyleSheet(AppStyles())

class AppStyles : ZkCssStyleSheet() {

    // -------------------------------------------------------------------------
    // CSS styles the application
    // -------------------------------------------------------------------------

    val home by cssClass {
        overflowY = "scroll"
        padding = 20
    }

}