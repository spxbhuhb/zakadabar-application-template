/*
 * @copyright@
 */
package zakadabar.template.backend.exampleEntity

import zakadabar.stack.backend.authorize.Authorizer
import zakadabar.stack.backend.authorize.EmptyAuthorizer
import zakadabar.stack.backend.business.EntityBusinessLogicBase
import zakadabar.template.data.ExampleEntityBo

/**
 * Business Logic for ExampleEntityBo.
 *
 * Generated with Bender at 2021-06-01T09:28:42.142Z.
 */
open class ExampleEntityBl : EntityBusinessLogicBase<ExampleEntityBo>(
    boClass = ExampleEntityBo::class
) {

    override val pa = ExampleEntityExposedPaGen()

    override val authorizer: Authorizer<ExampleEntityBo> = EmptyAuthorizer()

}