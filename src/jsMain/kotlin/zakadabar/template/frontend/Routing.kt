/*
 * @copyright@
 */

package zakadabar.template.frontend

import zakadabar.stack.frontend.application.ZkAppRouting
import zakadabar.template.frontend.pages.Home
import zakadabar.template.frontend.pages.exampleRecord.ExampleRecords

class Routing : ZkAppRouting(DefaultLayout, Home) {

    init {
        + Home
        + ExampleRecords
    }

}