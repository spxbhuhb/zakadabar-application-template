/*
 * @copyright@
 */
package zakadabar.template.frontend.browser.resources

import zakadabar.softui.browser.theme.SuiDarkTheme

class AppDarkTheme : SuiDarkTheme() {

    companion object {
        const val NAME = "app-dark"
    }

    override val name = NAME

    // -------------------------------------------------------------------------
    // Customize theme variables
    // -------------------------------------------------------------------------

//    override var primaryColor = "green"

    // -------------------------------------------------------------------------
    // Customize style variables
    // -------------------------------------------------------------------------

//    override fun onResume() {
//        super.onResume()
//
//        with(zkTitleBarStyles) {
//            appHandleBackground = "green"
//        }
//    }
}