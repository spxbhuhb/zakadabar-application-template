/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
@file:Suppress("unused")

import kotlinx.browser.document
import zakadabar.stack.frontend.FrontendContext
import zakadabar.stack.frontend.builtin.desktop.Desktop
import zakadabar.stack.frontend.builtin.desktop.messages.GlobalNavigationRequest
import zakadabar.stack.frontend.elements.SimpleElement
import zakadabar.stack.frontend.util.launch
import zakadabar.template.frontend.Module

fun main() {

    launch {

        // add KClass names as data attributes to DOM elements, useful for debugging, not meant for production
        // See: https://github.com/spxbhuhb/zakadabar-stack/blob/master/doc/misc/Productivity.md#simpleelement-addkclass

        SimpleElement.addKClass = true

        // Initialize the frontend. This method needs a running backend because it
        // fetches the account of the user who runs the frontend.
        // See: https://github.com/spxbhuhb/zakadabar-stack/blob/master/doc/cookbook/common/Accounts.md

        FrontendContext.init()

        // Add modules to the frontend

        FrontendContext += zakadabar.stack.frontend.Module
        FrontendContext += Module

        // Create an instance of the default desktop, initialize it and append it to the body

        document.body?.appendChild(Desktop().init().element)

        // Kick off the navigation, this just goes to the top of the entity tree.

        FrontendContext.dispatcher.postSync { GlobalNavigationRequest(null) }
    }

}