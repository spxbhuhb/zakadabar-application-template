/*
 * @copyright@
 */

package zakadabar.template.backend

import io.ktor.http.content.*
import io.ktor.routing.*
import zakadabar.core.authorize.AppRolesBase
import zakadabar.core.authorize.SimpleRoleAuthorizerProvider
import zakadabar.core.module.modules
import zakadabar.core.route.RoutedModule
import zakadabar.core.util.PublicApi
import zakadabar.template.backend.business.ExampleEntityBl

@PublicApi
object Module : RoutedModule {

    override fun onInstallStatic(route: Any) {
        (route as? Route)?.preCompressed {
            files("precompressed")
        }
    }

    override fun onModuleLoad() {
        zakadabar.lib.accounts.install(MyRoles)
        zakadabar.lib.i18n.install()

        modules += SimpleRoleAuthorizerProvider {
            all = LOGGED_IN
            delete = MyRoles.myRole
        }

        modules += ExampleEntityBl()
    }

}

object MyRoles : AppRolesBase() {
    val myRole by "my-role"
    val siteMember by "site-member"
}